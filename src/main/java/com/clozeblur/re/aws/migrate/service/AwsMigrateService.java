package com.clozeblur.re.aws.migrate.service;

import com.clozeblur.re.aws.migrate.client.AudioUploadClient;
import com.clozeblur.re.aws.migrate.client.AwsUploadClient;
import com.clozeblur.re.aws.migrate.configuration.AwsProperties;
import com.clozeblur.re.aws.migrate.configuration.StatusType;
import com.clozeblur.re.aws.migrate.constants.S3Config;
import com.clozeblur.re.aws.migrate.mapper.AwsMigrateMapper;
import com.clozeblur.re.aws.migrate.model.AwsMigrate;
import com.clozeblur.re.aws.migrate.queue.AwsMigrateQueue;
import com.clozeblur.re.aws.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuanjx
 * on 2017/12/3
 */
@Service
public class AwsMigrateService {

    private static final Logger logger = LoggerFactory.getLogger(AwsMigrateService.class);

    @Autowired
    private AwsMigrateMapper awsMigrateMapper;

    @Autowired
    private FsFileWriterService fsFileWriterService;

    @Lazy
    @Autowired
    private AwsMigrateService awsMigrateService;

    public AwsMigrate getAwsMigrateById(Integer id) {
        return awsMigrateMapper.getAwsMigrateById(id);
    }

    public Integer getCount(AwsMigrate migrate) {
        return awsMigrateMapper.getCount(migrate);
    }

    public List<AwsMigrate> getList(AwsMigrate awsMigrate) {
        return awsMigrateMapper.getAwsMigrateList(awsMigrate);
    }

    public void updateStatus(Integer from, Integer to, Integer type) {
        awsMigrateMapper.updateStatusByStatus(from, to, type);
    }

    public AwsMigrate getByPath(String path) {
        return awsMigrateMapper.getAwsMigrateByPath(path);
    }

    //============================================download分割线============================================//

    public void download() {
        AwsMigrate migrate = new AwsMigrate();
        migrate.setStatus(StatusType.未处理.getValue());
        migrate.setBatchNum(AwsProperties.getDownloadBatchNum());
        List<AwsMigrate> list = awsMigrateMapper.getAwsMigrateList(migrate);
        executeDownload(list);
    }

    private void executeDownload(List<AwsMigrate> list) {
        if (list == null || list.size() == 0) return;
        logger.info("开始执行download[" + list.size() + "]...");
        Date on = new Date();
        List<Thread> threads = new ArrayList<>();
        int numPerThread = list.size() / AwsProperties.getDownloadThreadNum();
        if (list.size() % AwsProperties.getDownloadThreadNum() > 0) numPerThread++;

        for (int i = 0;i < AwsProperties.getDownloadThreadNum(); i++) {
            //开启子线程
            int end = (i + 1) * numPerThread;
            if (end > list.size()) end = list.size();
            if (i * numPerThread >= end) {
                continue;
            }
            Thread thread = downloadThread(list.subList(i * numPerThread, end));
            thread.start();
            threads.add(thread);
        }
        for (Thread th : threads) {
            try {
                th.join();
            } catch (Exception ignored) {
            }
        }
        Date off = new Date();
        logger.info("当前下载定时任务执行结束, 此定时任务开始于:" + on.toString() + ", 结束于:" + off.toString());
    }

    private Thread downloadThread(List<AwsMigrate> list) {
        return new Thread(() -> {
            if (list == null || list.size() == 0) return;
            for (AwsMigrate migrate : list) {
                String fileName = fsFileWriterService.writeFile(migrate.getId(), migrate.getPath(), migrate.getType());
                if (StringUtils.isEmpty(fileName)) {
                    downloadFail(migrate);
                } else {
                    downloadSuccess(migrate);
                    AwsMigrateQueue.putAws(fileName);
                }
            }
        });
    }

    /**
     * 下载成功,状态置为1,并将changedPath设为文件后缀名暂存
     * @param migrate migrate
     */
    private void downloadSuccess(AwsMigrate migrate) {
        migrate.setStatus(StatusType.下载成功.getValue());
        awsMigrateMapper.updateStatus(migrate);
    }

    /**
     * 下载失败,状态置为2
     * @param migrate migrate
     */
    private void downloadFail(AwsMigrate migrate) {
        migrate.setStatus(StatusType.下载失败.getValue());
        awsMigrateMapper.updateStatus(migrate);
        logger.error("下载失败, id: {}, path: {}", migrate.getId(), migrate.getPath());
    }



    //============================================upload分割线============================================//

    public void upload() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < AwsProperties.getUploadThreadNum();i++) {
            Thread tr = new Thread(() -> {
                String name = AwsMigrateQueue.getAws();
                if (StringUtils.isEmpty(name)) return;
                Integer id = Integer.valueOf(name.substring(0, name.lastIndexOf(".")));
                if (id <= 0) return;
                AwsMigrate migrate = awsMigrateMapper.getAwsMigrateById(id);
                if (migrate != null) {
                    uploadWork(migrate);
                }
            });
            tr.start();
            threads.add(tr);
        }
        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException ignore) {
            }
        }
    }

    public void uploadWork(AwsMigrate migrate) {
        String virtualPath = migrate.getPath();
        virtualPath = virtualPath.startsWith("/") ? virtualPath.substring(1) : virtualPath;
        String ext = FileUtils.getEndWith(virtualPath);
        ext = StringUtils.isEmpty(ext) ? "" : ext;
        String path = S3Config.downloadDirPath + "/" + migrate.getId() + ext;
        File file = new File(path);
        String content = "";
        String category = FileUtils.getFileCategory(migrate.getType().toString());
        if (!file.exists()) {
            uploadFail(migrate);
            return;
        }
        if (StringUtils.isEmpty(category)) {
            uploadFail(migrate);
            awsMigrateService.delete(file);
            return;
        }
        if (category.equals("audio")) {
            try {
                content = AudioUploadClient.uploadToS3(virtualPath, file);
            } catch (Exception e) {
                uploadFail(migrate);
                awsMigrateService.delete(file);
                return;
            }
        } else {
            String bucket = category + "-bucket";
            try {
                content = AwsUploadClient.uploadToS3(bucket, file,
                        virtualPath, S3Config.STORAGE_ENDPOINT);
            } catch (IOException e) {
                uploadFail(migrate);
                awsMigrateService.delete(file);
                return;
            }
        }

        if (StringUtils.isEmpty(content)) {
            uploadFail(migrate);
            awsMigrateService.delete(file);
            return;
        }
        uploadSuccess(migrate);

        logger.info("上传成功: {}", migrate.getPath());
        //异步删除
        awsMigrateService.delete(file);
    }

    /**
     * 上传成功,状态置为3,并将changedPath更新为正确path
     * @param migrate migrate
     */
    private void uploadSuccess(AwsMigrate migrate) {
        migrate.setStatus(StatusType.上传成功.getValue());
        awsMigrateMapper.updateChangedPath(migrate);
    }

    /**
     * 上传失败,状态置为4
     * @param migrate migrate
     */
    private void uploadFail(AwsMigrate migrate) {
        migrate.setStatus(StatusType.上传失败.getValue());
        awsMigrateMapper.updateStatus(migrate);
        logger.error("上传失败: id: {}, path: {}, changedPath: {}",
                migrate.getId(), migrate.getPath(), migrate.getChangedPath());
    }

    @Async
    public void delete(File file) {
        file.delete();
    }
}

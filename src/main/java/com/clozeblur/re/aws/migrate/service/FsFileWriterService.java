package com.clozeblur.re.aws.migrate.service;

import com.alibaba.fastjson.JSONObject;
import com.clozeblur.re.aws.fdfs.configuration.CommonConsts;
import com.clozeblur.re.aws.fdfs.mapper.*;
import com.clozeblur.re.aws.fdfs.module.entity.FileEntity;
import com.clozeblur.re.aws.fdfs.module.entity.FileStorageEntity;
import com.clozeblur.re.aws.fdfs.module.query.*;
import com.clozeblur.re.aws.fdfs.storage.FastdfsStorageService;
import com.clozeblur.re.aws.util.FileUtils;
import com.clozeblur.re.aws.util.HttpClientUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by yuanjx
 * on 2017/12/3
 */
@Service
public class FsFileWriterService {

    private static final Logger logger = LoggerFactory.getLogger(FsFileWriterService.class);

    @Autowired
    private AudioFileMapper audioFileMapper;

    @Autowired
    private AudioFileStorageMapper audioFileStorageMapper;

    @Autowired
    private ImageFileMapper imageFileMapper;

    @Autowired
    private ImageStorageMapper imageStorageMapper;

    @Autowired
    private OtherFileMapper otherFileMapper;

    @Autowired
    private OtherFileStorageMapper otherFileStorageMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private FastdfsStorageService fastdfsStorageService;

    public String writeFile(Integer id, String path, Integer type) {
        String category = FileUtils.getFileCategory(type.toString());
        if (StringUtils.isEmpty(category)) return null;
        String ext = FileUtils.getEndWith(path);
        String virtualPath = StringUtils.stripFilenameExtension(path);

        if (StringUtils.isEmpty(virtualPath)) {
            logger.error("文件virtualPath为空, 文件id为: {}", id);
            return null;
        }

        if (virtualPath.startsWith("/")) {
            virtualPath = virtualPath.substring(1);
        }

        //获取存储库id等信息
        FileEntity fileEntity = getFileEntity(category, ext, virtualPath);
        if (fileEntity == null) {
            logger.error("没有查询到file表相关记录, 文件id为: {}", id);
            if (category.equals("audio")) {
                if (path.startsWith("/")) {
                    path = path.substring(1);
                }
                byte[] bytes = HttpClientUtils.download("文件系统未上传的磁盘录音重定向地址url" + path);
                if (ArrayUtils.isEmpty(bytes)) {
                    logger.error("下载失败, path为: {}", path);
                    return null;
                }
                return FileUtils.write(id, bytes, ext);
            }
            return null;
        }
        //获取物理存储地址
        FileStorageEntity storageEntity = getFileStorageEntity(category, fileEntity);
        if (storageEntity == null) {
            logger.error("没有查询到storage表相关记录");
            return null;
        }
        //获取字节流
        try {
            byte[] bytes = fastdfsStorageService.fetch(new FastdfsStorageQuery(storageEntity.getGroup(), storageEntity.getPath()));
            return FileUtils.write(id, bytes, ext);
        } catch (Exception e) {
            logger.error("storage获取文件字节流失败");
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private FileStorageEntity getFileStorageEntity(String category, FileEntity fileEntity) {
        String storageKey = CommonConsts.generateStorageRedisKey(fileEntity.getId(), category);
        Object storageObj = redisTemplate.opsForValue().get(storageKey);
        FileStorageEntity storageEntity = null;
        if (storageObj == null) {
            if (category.equals("audio")) {
                AudioFileStorageQuery query = new AudioFileStorageQuery();
                query.setFileId(fileEntity.getId());
                query.setVirtualPath(fileEntity.getVirtualPath());
                storageEntity = audioFileStorageMapper.getAudioFileStorage(query);
            } else if (category.equals("image")) {
                ImageStorageQuery query = new ImageStorageQuery();
                query.setFileId(fileEntity.getId());
                query.setVirtualPath(fileEntity.getVirtualPath());
                query.setGenerateKey(0L);
                storageEntity = imageStorageMapper.getImageStorage(query);
            } else if (category.equals("other")) {
                OtherFileStorageQuery query = new OtherFileStorageQuery();
                query.setFileId(fileEntity.getId());
                query.setVirtualPath(fileEntity.getVirtualPath());
                storageEntity = otherFileStorageMapper.getOtherFileStorage(query);
            }
        } else {
            storageEntity = CommonConsts.loadMap2StorageEntity((Map<String, Object>) JSONObject.parse(storageObj.toString()), category);
        }
        return storageEntity;
    }

    @SuppressWarnings("unchecked")
    private FileEntity getFileEntity(String category, String ext, String virtualPath) {
        String fileKey = CommonConsts.generateFileRedisKey(virtualPath, category);
        Object fileObj = redisTemplate.opsForValue().get(fileKey);
        FileEntity fileEntity = null;
        if (fileObj == null) {
            if (category.equals("audio")) {
                AudioFileQuery query = new AudioFileQuery(virtualPath);
                fileEntity = audioFileMapper.getAudioFile(query);
            } else if (category.equals("image")) {
                ImgReqQuery query = null;
                try {
                    query = new ImgReqQuery(virtualPath, "0x0", ext);
                    fileEntity = imageFileMapper.getImageFile(query);
                } catch (Exception e) {
                    logger.error("文件参数不对: {}", virtualPath);
                }
            } else if (category.equals("other")) {
                OtherFileQuery query = new OtherFileQuery(virtualPath);
                fileEntity = otherFileMapper.getOtherFile(query);
            }
        } else {
            fileEntity = CommonConsts.loadMap2Entity((Map<String, Object>) JSONObject.parse(fileObj.toString()), category);
        }
        return fileEntity;
    }
}

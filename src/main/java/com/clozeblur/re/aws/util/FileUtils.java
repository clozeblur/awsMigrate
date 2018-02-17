package com.clozeblur.re.aws.util;

import com.clozeblur.re.aws.migrate.configuration.AwsProperties;
import com.clozeblur.re.aws.migrate.constants.S3Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
public class FileUtils {

    private FileUtils() {}

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    static {
        File mainDir = new File(S3Config.tempDirPath);
        if (!mainDir.exists()) {
            mainDir.mkdirs();
        }
        File downloadDir = new File(S3Config.downloadDirPath);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
    }

    public static String write(Integer id, byte[] bytes, String ext) {
        String dirPath = S3Config.downloadDirPath;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        ext = StringUtils.isEmpty(ext) ? "" : ext;
        String absolutePath = dir.getAbsolutePath() + "/" + id + ext;
        File file = new File(absolutePath);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            logger.error("IO流转换FileOutputStream对象失败: 文件[" + file.getAbsolutePath() + "]不存在");
            return null;
        }
        try {
            out.write(bytes);
        } catch (IOException e) {
            logger.error("IO流写入文件失败: [" + file.getAbsolutePath() + "]");
            if (file.exists()) {
                file.delete();
            }
            return null;
        }
        try {
            out.close();
        } catch (IOException e) {
            logger.error("IO流关闭异常");
        }
        return file.getName();
    }

    public static boolean limitFileMaxNums(String dirPath, int num) {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            return files != null && files.length < num;
        }
        return true;
    }

    public static boolean limitFileMinNums(String dirPath, int num) {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            return files != null && files.length > num;
        }
        return false;
    }

    public static String getEndWith(String filename) {
        if (filename == null || !filename.contains("."))
            return null;
        return filename.substring(filename.lastIndexOf("."));
    }

    public static void deleteDir(String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    f.delete();
                }
            }
        }
    }

    public static String getFileCategory(String type) {
        if (AwsProperties.getAudioTypeList().contains(type)) {
            return "audio";
        } else if (AwsProperties.getImageTypeList().contains(type)) {
            return "image";
        } else if (AwsProperties.getOtherTypeList().contains(type)) {
            return "other";
        }
        return null;
    }
}

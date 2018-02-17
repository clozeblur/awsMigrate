package com.clozeblur.re.aws.fdfs.configuration;

import com.clozeblur.re.aws.fdfs.module.entity.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用常量
 * Created by yuanjx on 2017/12/8.
 */
public class CommonConsts {

    /**
     * 数据库中状态字段, 表示正向意义.
     * 例如: delete_status 为该值,则表示该记录标记为删除.
     * status 为该值, 则表示该记录是标记为有效
     */
    public static final int STATUS_ACTIVE = 1;

    /**
     * 数据库中状态字段, 表示负向意义.
     * 例如: delete_status 为该值,则表示该记录标记为未删除.
     * status 为该值, 则表示该记录是标记为无效
     */
    public static final int STATUS_INACTIVE = 0;

    private static final String FILE_TABLE_PREFIX = "file";

    private static final String STORAGE_TABLE_PREFIX = "storage";

    private static final String CON = "##fsfetch##";

    public static String generateFileRedisKey(String virtualPath, String category) {
        return category + "-" + FILE_TABLE_PREFIX + CON + virtualPath;
    }

    public static String generateStorageRedisKey(Object fileId, String category) {
        return category + "-" + STORAGE_TABLE_PREFIX + CON + fileId.toString();
    }

    public static Map<String, Object> generateFileMap(FileEntity entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("domain", entity.getDomain());
        map.put("fileName", entity.getFileName());
        map.put("ext", entity.getExt());
        map.put("fileId", entity.getId());
        map.put("virtualPath", entity.getVirtualPath());
        return map;
    }

    public static Map<String, Object> generateStorageMap(FileStorageEntity entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("group", entity.getGroup());
        map.put("path", entity.getPath());
        return map;
    }

    public static FileEntity loadMap2Entity(Map<String, Object> map, String category) {
        FileEntity entity;
        switch (category) {
            case "image":
                entity = new ImageFileEntity();
                break;
            case "audio":
                entity = new AudioFileEntity();
                break;
            case "other":
                entity = new OtherFileEntity();
                break;
            default:
                return null;
        }
        entity.setDomain(String.valueOf(map.get("domain")));
        entity.setFileName(String.valueOf(map.get("fileName")));
        entity.setExt(String.valueOf(map.get("ext")));
        entity.setId(Long.valueOf(String.valueOf(map.get("fileId"))));
        entity.setVirtualPath(String.valueOf(map.get("virtualPath")));
        return entity;
    }

    public static FileStorageEntity loadMap2StorageEntity(Map<String, Object> map, String category) {
        FileStorageEntity entity;
        switch (category) {
            case "image":
                entity = new ImageStorageEntity();
                break;
            case "audio":
                entity = new AudioFileStorageEntity();
                break;
            case "other":
                entity = new OtherFileStorageEntity();
                break;
            default:
                return null;
        }
        entity.setGroup(String.valueOf(map.get("group")));
        entity.setPath(String.valueOf(map.get("path")));
        return entity;
    }
}

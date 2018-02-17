package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.ImageStorageEntity;
import com.clozeblur.re.aws.fdfs.module.query.ImageStorageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * ImageStorage 信息获取
 * Created by yuanjx on 2017/12/13.
 */
@Mapper
public interface ImageStorageMapper {

    /**
     * 通过file id 与 generate key 获取存储信息
     * @param imageStorageQuery 查询参数
     * @return  图片存储信息
     */
    ImageStorageEntity getImageStorage(ImageStorageQuery imageStorageQuery);

    /**
     * 保存图片存储信息
     * @param imageStorage  图片存储信息
     */
    void saveImageStorage(ImageStorageEntity imageStorage);

}

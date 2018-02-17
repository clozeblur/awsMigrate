package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.ImageMetaEntity;
import com.clozeblur.re.aws.fdfs.module.query.ImageMetadataQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片metadata
 * Created by yuanjx on 2017/12/15.
 */
@Mapper
public interface ImageMetadataMapper {

    ImageMetaEntity getImageMeta(ImageMetadataQuery imageMetadataQuery);

    void saveImageMeta(ImageMetaEntity imageMeta);
}

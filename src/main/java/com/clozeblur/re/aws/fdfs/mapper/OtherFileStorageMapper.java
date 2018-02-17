package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.OtherFileStorageEntity;
import com.clozeblur.re.aws.fdfs.module.query.OtherFileStorageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * OtherFileStorageMapper
 * Created by yuanjx on 2017/12/14.
 */
@Mapper
public interface OtherFileStorageMapper {

    /**
     * 通过file id获取文件存储信息
     */
    OtherFileStorageEntity getOtherFileStorage(OtherFileStorageQuery query);
}

package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.OtherFileEntity;
import com.clozeblur.re.aws.fdfs.module.query.OtherFileQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 获取文件
 * Created by yuanjx on 2017/12/14.
 */
@Mapper
public interface OtherFileMapper {

    /**
     * 根据virtualPath查找文件信息
     */
    OtherFileEntity getOtherFile(OtherFileQuery query);

    List<OtherFileEntity> getLatestOtherFile(@Param("tableIdx") Integer tableIdx, @Param("num") Integer num);

}

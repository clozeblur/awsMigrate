package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.ImageFileEntity;
import com.clozeblur.re.aws.fdfs.module.query.ImgReqQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 原图数据获取
 * Created by yuanjx on 2017/12/13.
 */
@Mapper
public interface ImageFileMapper {

    /**
     * 获取原图信息
     * 确保 virtualPath 不为空
     * @param imgReqQuery 查询参数
     * @return  原图信息
     */
    ImageFileEntity getImageFile(ImgReqQuery imgReqQuery);

    List<ImageFileEntity> getLatestImageFile(@Param("tableIdx") Integer tableIdx, @Param("num") Integer num);
}

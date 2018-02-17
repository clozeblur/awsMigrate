package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.GenImageFileEntity;
import com.clozeblur.re.aws.fdfs.module.query.GenImgQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 缩放图片信息获取
 * Created by yuanjx on 2017/12/13.
 */
@Mapper
public interface GenImageFileMapper {

    /**
     * 根据file id 和缩放信息查找缩放图片
     * @param genImgQuery   file id 与 缩放信息
     * @return  缩放图片列表
     */
    List<GenImageFileEntity> getGenImageFile(GenImgQuery genImgQuery);

    /**
     * 保存新的缩略图主数据
     * @param genImageFile 缩略图主数据
     */
    void saveGenImageFile(GenImageFileEntity genImageFile);
}

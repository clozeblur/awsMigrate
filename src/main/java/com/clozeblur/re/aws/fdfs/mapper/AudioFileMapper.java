package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.AudioFileEntity;
import com.clozeblur.re.aws.fdfs.module.query.AudioFileQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AudioFileMapper {

    /**
     * 根据virtual path获取对应数据
     */
    AudioFileEntity getAudioFile(AudioFileQuery query);

    List<AudioFileEntity> getLatestAudioFile(@Param("tableIdx") Integer tableIdx, @Param("num") Integer num);

}

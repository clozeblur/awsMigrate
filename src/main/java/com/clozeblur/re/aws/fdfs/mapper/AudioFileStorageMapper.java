package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.AudioFileStorageEntity;
import com.clozeblur.re.aws.fdfs.module.query.AudioFileStorageQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AudioFileStorageMapper {

    /**
     * 通过file id获取音频存储信息
     */
    AudioFileStorageEntity getAudioFileStorage(AudioFileStorageQuery query);
}

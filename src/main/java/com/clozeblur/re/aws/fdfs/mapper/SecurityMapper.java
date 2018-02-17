package com.clozeblur.re.aws.fdfs.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 安全验证相关
 * Created by yuanjx on 2017/12/17.
 */
@Mapper
public interface SecurityMapper {

    int accessIpRecordCount(List<String> ipList);
}

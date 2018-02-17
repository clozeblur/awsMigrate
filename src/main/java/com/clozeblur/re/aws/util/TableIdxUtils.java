package com.clozeblur.re.aws.util;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 存储表序列生成
 * Created by yuanjx on 2017/6/8.
 */
public class TableIdxUtils {

    public static int tableIdx(String virtualPath) {
        Assert.isTrue(!StringUtils.isEmpty(virtualPath), "virtualPath 为 null");

        int hashCode = virtualPath.hashCode();
        hashCode = hashCode % 10;
        if (hashCode < 0) {
            hashCode = 0 - hashCode;
        }
        return hashCode + 10;
    }
}

package com.clozeblur.re.aws.fdfs.module.query;

import com.clozeblur.re.aws.util.TableIdxUtils;

/**
 * 对于文件的查询需要分表
 * 该类用于生成表的序号
 * Created by yuanjx on 2017/12/3.
 */
public abstract class AbstractQuery {

    public abstract String getVirtualPath();

    public Integer getTableIdx() {
        return TableIdxUtils.tableIdx(getVirtualPath());
    }
}

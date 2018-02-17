package com.clozeblur.re.aws.fdfs.mapper;

import com.clozeblur.re.aws.fdfs.module.entity.DomainEntity;
import com.clozeblur.re.aws.fdfs.module.entity.DomainPrototypeEntity;
import com.clozeblur.re.aws.fdfs.module.entity.PrototypeEntity;
import com.clozeblur.re.aws.fdfs.module.query.DomainPrototypeQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 获取缩放参数
 * Created by yuanjx on 2017/12/13.
 */
@Mapper
public interface DomainPrototypeMapper {

    /**
     * 通过 domain 与 matcher 查找缩放参数
     * @return 匹配的缩放参数列表
     */
    List<DomainPrototypeEntity> listDomainPrototype(DomainPrototypeQuery domainPrototypeQuery);

    /**
     * domain开头模糊匹配 与 matcher 查找缩放参数
     */
    List<PrototypeEntity> fuzzyDomainPrototype(DomainPrototypeQuery query);

    /**
     * 根据名字获取domain信息
     * @param name domain
     */
    List<DomainEntity> getDomainByName(String name);

    /**
     * 新增加一个 domain 与 matcher 关系
     */
    void addDomainPrototype(DomainPrototypeEntity entity);
}

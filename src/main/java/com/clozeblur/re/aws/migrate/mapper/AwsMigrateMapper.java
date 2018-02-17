package com.clozeblur.re.aws.migrate.mapper;

import com.clozeblur.re.aws.migrate.model.AwsMigrate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
@Mapper
public interface AwsMigrateMapper {

    Integer getCount(@Param("migrate") AwsMigrate migrate);

    List<AwsMigrate> getAwsMigrateList(@Param("migrate") AwsMigrate migrate);

    AwsMigrate getAwsMigrateById(@Param("id") Integer id);

    AwsMigrate getAwsMigrateByPath(@Param("path") String path);

    void updateChangedPath(@Param("migrate") AwsMigrate migrate);

    void updateStatus(@Param("migrate") AwsMigrate migrate);

    void updateStatusByStatus(@Param("fromStatus") Integer fromStatus,
                              @Param("toStatus") Integer toStatus,
                              @Param("type") Integer type);
}

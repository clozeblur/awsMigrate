package com.clozeblur.re.aws.migrate.schedule;

import com.clozeblur.re.aws.migrate.configuration.AwsProperties;
import com.clozeblur.re.aws.migrate.constants.S3Config;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanjx
 * on 2017/12/3
 */
@Component
public class AwsMigrateSchedule {

    @Autowired
    private ConfigInjector configInjector; // 配置中心

    @PostConstruct
    public void init() {
        initTypeLists();
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateAwsMigrateTypeMapTask() {
        initTypeLists();
    }

    private void initTypeLists() {
        Map<String, String> map = configInjector.getConfigMap("awsMigrateTypeList", "jiagou");
        List<String> audioList = new ArrayList<>();
        List<String> imageList = new ArrayList<>();
        List<String> otherList = new ArrayList<>();
        for (String key : map.keySet()) {
            String type = key.replace(S3Config.awsMigrateMap_prefix, "");
            String values = map.get(key);
            if (StringUtils.isNotBlank(values)) {
                List<String> list = Arrays.asList(values.split("\\,"));
                switch (type) {
                    case "audio":
                        audioList = list;
                        break;
                    case "image":
                        imageList = list;
                        break;
                    case "other":
                        otherList = list;
                        break;
                }
            }
        }
        AwsProperties.setAudioTypeList(audioList);
        AwsProperties.setImageTypeList(imageList);
        AwsProperties.setOtherTypeList(otherList);
    }
}

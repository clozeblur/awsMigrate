package com.clozeblur.re.aws.fdfs.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import third.rewrite.fastdfs.FdfsConfig;
import third.rewrite.fastdfs.service.impl.StorageClientService;
import third.rewrite.fastdfs.service.impl.TrackerClientService;
import third.rewrite.fastdfs.socket.FdfsSocketPoolService;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yuanjx
 * on 2017/9/12
 */
@Configuration
public class FdfsConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(FdfsConfiguration.class);

    @Bean("fdfsConfig")
    public FdfsConfig fdfsConfig() {
        String env = "ConfigPersist.getEnv() // 配置中心功能";
        String path;
        switch (env) {
            case "test":
                path = "test";
                break;
            case "integration":
                path = "integration";
                break;
            case "production":
                path = "production";
                break;
            default:
                path = "dev";
        }
        try {
            URL url = new URL(path);
            return new FdfsConfig(url);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException: {}", e.getMessage());
        }
        return null;
    }

    @Bean(destroyMethod = "destroy")
    @ConditionalOnMissingBean
    public FdfsSocketPoolService fdfsSocketPoolService(FdfsConfig fdfsConfig){
        FdfsSocketPoolService fdfsSocketPoolService = new FdfsSocketPoolService();
        fdfsSocketPoolService.setConfig(fdfsConfig);
        fdfsSocketPoolService.init();
        return fdfsSocketPoolService;
    }

    @Bean
    @ConditionalOnMissingBean
    public StorageClientService storageClientService(FdfsSocketPoolService fdfsSocketPoolService) {
        StorageClientService storageClientService = new StorageClientService();
        storageClientService.setFdfsSocketPoolService(fdfsSocketPoolService);
        return storageClientService;
    }

    @Bean(destroyMethod = "destroy")
    @ConditionalOnMissingBean
    public TrackerClientService trackerClientService(FdfsConfig fdfsConfig,
                                                     FdfsSocketPoolService fdfsSocketPoolService) {
        TrackerClientService trackerClientService = new TrackerClientService();
        trackerClientService.setFdfsConfig(fdfsConfig);
        trackerClientService.setFdfsSocketPoolService(fdfsSocketPoolService);
        trackerClientService.init();
        return trackerClientService;
    }
}

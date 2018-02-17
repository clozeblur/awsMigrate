package com.clozeblur.re.aws.migrate.service;

import com.clozeblur.re.aws.migrate.configuration.AwsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuanjx
 * on 2017/12/6
 */
@Service
public class AwsTaskService {

    @Autowired
    private AwsMigrateService awsMigrateService;

    public void executeTask() {
        if (AwsProperties.getIsExecuting()) {
            return;
        }
        AwsProperties.setIsExecuting(true);
        Thread dth = downloadWorkForQueue();
        Thread uth = uploadWorkForQueue();
        dth.start();
        uth.start();
        try {
            dth.join();
            uth.join();
        } catch (InterruptedException ignore) {
        }
    }

    private Thread downloadWorkForQueue() {
        return new Thread(() -> {
            while(!AwsProperties.getEmergencyStop()) {
                awsMigrateService.download();
            }
        });
    }

    private Thread uploadWorkForQueue() {
        return new Thread(() -> {
            while(!AwsProperties.getEmergencyStop()) {
                awsMigrateService.upload();
            }
        });
    }
}

package com.clozeblur.re.aws.migrate.controller;

import com.clozeblur.re.aws.migrate.configuration.AwsProperties;
import com.clozeblur.re.aws.migrate.model.AwsMigrate;
import com.clozeblur.re.aws.migrate.service.AwsMigrateService;
import com.clozeblur.re.aws.migrate.service.AwsTaskService;
import com.clozeblur.re.aws.migrate.service.FsFileWriterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
@Controller
@RequestMapping("/awsMigrate")
public class AwsMigrateController {

    @Autowired
    private AwsMigrateService awsMigrateService;

    @Autowired
    private AwsTaskService awsTaskService;

    @Autowired
    private FsFileWriterService fsFileWriterService;

    @GetMapping("/startTask")
    public void startTask() {
        awsTaskService.executeTask();
    }

    @GetMapping("/jobType")
    @ResponseBody
    public String changeJobType(Integer type) {
        if (type != null && type > -1) {
            AwsProperties.setType(type);
            return "ok! current type is:" + AwsProperties.getType();
        }
        return "type is illegal";
    }

    @RequestMapping(value = "/downloadThreadNum", method = RequestMethod.GET)
    @ResponseBody
    public String changeDownloadThreadNum(Integer num) {
        if (num != null && num > 0) {
            AwsProperties.setDownloadThreadNum(num);
            return "ok! current downloadThreadNum is:" + AwsProperties.getDownloadThreadNum();
        }
        return "num is illegal";
    }

    @RequestMapping(value = "/uploadThreadNum", method = RequestMethod.GET)
    @ResponseBody
    public String changeUploadThreadNum(Integer num) {
        if (num != null && num > 0) {
            AwsProperties.setUploadThreadNum(num);
            return "ok! current uploadThreadNum is:" + AwsProperties.getUploadThreadNum();
        }
        return "num is illegal";
    }

    @RequestMapping(value = "/downloadBatchNum", method = RequestMethod.GET)
    @ResponseBody
    public String downloadBatchNum(Integer num) {
        if (num != null && num > 0) {
            AwsProperties.setDownloadBatchNum(num);
            return  "ok! current downloadBatchNum is:" + AwsProperties.getDownloadBatchNum();
        }
        return "num is illegal";
    }

    @RequestMapping(value = "/emergencyStop", method = RequestMethod.GET)
    @ResponseBody
    public String emergencyStop() {
        AwsProperties.setEmergencyStop(Boolean.TRUE);
        return "stop successfully";
    }

    @RequestMapping(value = "/scheduleStart", method = RequestMethod.GET)
    @ResponseBody
    public String scheduleStart() {
        AwsProperties.setEmergencyStop(Boolean.FALSE);
        return "start successfully";
    }

    @RequestMapping(value = "/executingStatus", method = RequestMethod.GET)
    @ResponseBody
    public String removeExecutingStatus(@RequestParam(name = "order", required = false) String order) {
        if (order == null) {
            return "当前允许执行或重启的状态为:" + AwsProperties.getIsExecuting().toString();
        }
        if (order.equals("true")) {
            AwsProperties.setIsExecuting(true);

        } else {
            AwsProperties.setIsExecuting(false);
        }
        return "当前允许执行或重启的状态为:" + AwsProperties.getIsExecuting().toString();
    }

    @GetMapping("/getAwsMigrateById")
    @ResponseBody
    public AwsMigrate getById(Integer id) {
        if (id == null) {
            id = 1;
        }
        return awsMigrateService.getAwsMigrateById(id);
    }

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public List<AwsMigrate> getListData(Integer status, Integer num, Integer type) {
        if (num == null || num > 20) {
            num = 20;
        }
        if (status == null || status > 6) {
            status = 0;
        }
        if (type == null) {
            type = 0;
        }
        AwsMigrate migrate = new AwsMigrate();
        migrate.setStatus(status);
        migrate.setBatchNum(num);
        migrate.setType(type);
        return awsMigrateService.getList(migrate);
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.GET)
    @ResponseBody
    public String getCount(@RequestParam(name = "status") Integer status,
                           @RequestParam(name = "type", required = false) Integer type) {
        AwsMigrate migrate = new AwsMigrate();
        migrate.setStatus(status);
        migrate.setType(type);
        return awsMigrateService.getCount(migrate).toString();
    }

    @GetMapping("/changeStatus")
    @ResponseBody
    public String changeDataStatus(Integer from, Integer to, Integer type) {
        awsMigrateService.updateStatus(from, to, type);
        return "已将所有type为" + type +"且status为" + from + "的文件状态重置为" + to;
    }

    @GetMapping("/getByPath")
    @ResponseBody
    public String getDataByPath(String path) {
        if (StringUtils.isBlank(path)) return "path非法";
        return awsMigrateService.getByPath(path).toString();
    }

    @GetMapping("/awsPropertiesDetail")
    @ResponseBody
    public String awsPropertiesDetail() {
        return AwsProperties.detail();
    }

    @GetMapping("/testDownload")
    @ResponseBody
    public String testDownload(Integer id) {
        AwsMigrate migrate = awsMigrateService.getAwsMigrateById(id);
        return fsFileWriterService.writeFile(migrate.getId(), migrate.getPath(), migrate.getType());
    }

    @GetMapping("/testUpload")
    @ResponseBody
    public String testUpload(Integer id) {
        AwsMigrate migrate = awsMigrateService.getAwsMigrateById(id);
        awsMigrateService.uploadWork(migrate);
        return "上传结束";
    }
}

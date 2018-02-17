package com.clozeblur.re.aws.migrate.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
public class AwsProperties {

    private AwsProperties() {}

    private static Boolean emergencyStop = false;

    private static Boolean isExecuting = false;

    private volatile static Integer downloadThreadNum = 10;

    private volatile static Integer uploadThreadNum = 10;

    private volatile static Integer downloadBatchNum = 100;

    private volatile static Integer type = -1;

    private static List<String> audioTypeList = new ArrayList<>();

    private static List<String> imageTypeList = new ArrayList<>();

    private static List<String> otherTypeList = new ArrayList<>();

    public static Boolean getEmergencyStop() {
        return emergencyStop;
    }

    public static void setEmergencyStop(Boolean emergencyStop) {
        AwsProperties.emergencyStop = emergencyStop;
    }

    public static Boolean getIsExecuting() {
        return isExecuting;
    }

    public static void setIsExecuting(Boolean isExecuting) {
        AwsProperties.isExecuting = isExecuting;
    }

    public static Integer getDownloadThreadNum() {
        return downloadThreadNum;
    }

    public static void setDownloadThreadNum(Integer downloadThreadNum) {
        AwsProperties.downloadThreadNum = downloadThreadNum;
    }

    public static Integer getUploadThreadNum() {
        return uploadThreadNum;
    }

    public static void setUploadThreadNum(Integer uploadThreadNum) {
        AwsProperties.uploadThreadNum = uploadThreadNum;
    }

    public static Integer getDownloadBatchNum() {
        return downloadBatchNum;
    }

    public static void setDownloadBatchNum(Integer downloadBatchNum) {
        AwsProperties.downloadBatchNum = downloadBatchNum;
    }

    public static Integer getType() {
        return type;
    }

    public static void setType(Integer type) {
        AwsProperties.type = type;
    }

    public static List<String> getAudioTypeList() {
        return audioTypeList;
    }

    public static void setAudioTypeList(List<String> audioTypeList) {
        AwsProperties.audioTypeList = audioTypeList;
    }

    public static List<String> getImageTypeList() {
        return imageTypeList;
    }

    public static void setImageTypeList(List<String> imageTypeList) {
        AwsProperties.imageTypeList = imageTypeList;
    }

    public static List<String> getOtherTypeList() {
        return otherTypeList;
    }

    public static void setOtherTypeList(List<String> otherTypeList) {
        AwsProperties.otherTypeList = otherTypeList;
    }

    public static String detail() {
        return "AwsProperties = "
                + "\n { downloadThreadNum : " + downloadThreadNum
                + ",\n uploadThreadNum : " + uploadThreadNum
                + ",\n downloadBatchNum : " + downloadBatchNum
                + ",\n audioTypeList : " + audioTypeList.toString()
                + ",\n imageTypeList : " + imageTypeList.toString()
                + ",\n otherTypeList : " + otherTypeList.toString()
                + "\n }";
    }
}

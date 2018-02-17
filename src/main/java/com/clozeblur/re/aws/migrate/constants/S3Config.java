package com.clozeblur.re.aws.migrate.constants;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
public class S3Config {

    public static final String AWS_ACCESS_KEY = "ak";

    public static final String AWS_SECRET_KEY = "sk";

    public static final String TEST_BUCKET = "test";

    public static final String AUDIO_BUCKET = "audio-bucket";

    public static final String AUDIO_ENDPOINT = "audio.endpoint";

    public static final String IMAGE_BUCKET = "image-bucket";

    public static final String IMAGE_ENDPOINT = "image.endpoint";

    public static final String OTHER_BUCKET = "other-bucket";

    public static final String OTHER_ENDPOINT = "other.endpoint";

    public static final String STORAGE_ENDPOINT = "storage.endpoint";

    public static final String awsMigrateMap_prefix = "aws.migrate.type.";

    public static final String tempDirPath = "/home/tomcat/tmp/syncAws/";

    public static final String abnormalDirPath = tempDirPath + "abnormal";

    public static final String downloadDirPath = tempDirPath + "download";
}

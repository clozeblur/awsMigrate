package com.clozeblur.re.aws.migrate.client;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.clozeblur.re.aws.migrate.constants.S3Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
public class AwsUploadClient {

    private static Logger logger = LoggerFactory.getLogger(AwsUploadClient.class);
    private static TransferManager tx;
    private static AmazonS3 s3;
    private static String accessKey = S3Config.AWS_ACCESS_KEY;
    private static String secretKey = S3Config.AWS_SECRET_KEY;

    static {
        ClientConfiguration configuration = new ClientConfiguration();
        configuration.setProtocol(Protocol.HTTPS);
        S3ClientOptions options = S3ClientOptions.builder().setPathStyleAccess(true).setPayloadSigningEnabled(true).disableChunkedEncoding().build();
        s3 = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
        s3.setS3ClientOptions(options);
    }

    /**
     * @Title: uploadToS3
     * @Description: 将文件上传至S3上并且返回url
     * @param tempFile 目标文件
     * @param remoteFileName 文件名
     * @throws IOException    设定文件
     * @return String    返回类型
     */
    public static String uploadToS3(String bucketName, File tempFile, String remoteFileName, String endpoint) throws IOException {
        try {
            s3.setEndpoint(endpoint);
            //上传文件
            s3.putObject(bucketName, remoteFileName, tempFile);
            //获取一个request
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                    bucketName, remoteFileName);
            //生成公用的url
            URL url = s3.generatePresignedUrl(urlRequest);
            return url.toString();
        } catch (AmazonClientException ace) {
            logger.error("AmazonClientException,uploadToS3异常:" +
                    "{bucketName:" + bucketName
                    + ", tempFile:" + tempFile.getAbsolutePath()
                    + ", remoteFileName:" + remoteFileName + "}");
        }
        return null;
    }

    public static String uploadToS3(String bucketName, InputStream stream, String remoteFileName) throws IOException {
        try {
            //上传文件
            s3.putObject(bucketName, remoteFileName, stream, new ObjectMetadata());
            //获取一个request
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                    bucketName, remoteFileName);
            //生成公用的url
            URL url = s3.generatePresignedUrl(urlRequest);
            return url.toString();
        } catch (AmazonClientException ace) {
            //ignore
        }
        return null;
    }

    /**
     * @Title: getContentFromS3
     * @Description: 获取文件2进制流
     * @param remoteFileName 文件名
     * @throws IOException    设定文件
     * @return S3ObjectInputStream    返回类型  数据流
     */
    public static S3ObjectInputStream getContentFromS3(String bucketName, String remoteFileName) throws IOException {
        try {
            GetObjectRequest request  = new GetObjectRequest(bucketName,remoteFileName);
            S3Object object = s3.getObject(request);
            return object.getObjectContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: downFromS3
     * @Description: 将文件下载到本地路径
     * @param remoteFileName 文件名
     * @param path 下载的路径
     * @throws IOException    设定文件
     */
    public static void downFromS3(String bucketName, String remoteFileName,String path) throws IOException {
        try {
            GetObjectRequest request  = new GetObjectRequest(bucketName,remoteFileName);
            ObjectMetadata metadata = s3.getObject(request,new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: getUrlFromS3
     * @Description: 获取文件的url
     * @param remoteFileName 文件名
     * @throws IOException    设定文件
     * @return String    返回类型
     */
    public static String getUrlFromS3(String bucketName, String remoteFileName) throws IOException {
        try {
            GeneratePresignedUrlRequest httpRequest=new GeneratePresignedUrlRequest(bucketName, remoteFileName);
            return s3.generatePresignedUrl(httpRequest).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证s3上是否存在名称为bucketName的Bucket
     * @param bucketName bucketName
     * @return boolean
     */
    public static boolean checkBucketExists(AmazonS3 s3, String bucketName) {
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket bucket : buckets) {
            if (Objects.equals(bucket.getName(), bucketName)) {
                return true;
            }
        }
        return false;
    }

    public static void delFromS3(String bucketName, String remoteFileName) throws IOException {
        try {
            s3.deleteObject(bucketName, remoteFileName);
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
    }
}

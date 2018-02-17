package com.clozeblur.re.aws.fdfs.storage;

import com.clozeblur.re.aws.fdfs.module.query.FastdfsStorageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import third.rewrite.fastdfs.FdfsException;
import third.rewrite.fastdfs.StorageClient;
import third.rewrite.fastdfs.service.IStorageClientService;
import third.rewrite.fastdfs.service.ITrackerClientService;
import third.rewrite.fastdfs.service.impl.ByteArrayFdfsFileInputStreamHandler;

/**
 * 与fastdfs storage操作
 * Created by yuanjx on 2017/12/14.
 */
@Service
public class FastdfsStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FastdfsStorageService.class);

    @Autowired
    private ITrackerClientService trackerClientService;

    @Autowired
    private IStorageClientService storageClientService;

    private ByteArrayFdfsFileInputStreamHandler byteArrayFdfsFileInputStreamHandler =
            new ByteArrayFdfsFileInputStreamHandler();
    /**
     * 通过fastdfs获取数据
     * group 与 path 非空
     *
     * @param fastdfsStorageQuery fastdfs 查询参数
     * @return  获取的数据字节流
     */
    public byte[] fetch(FastdfsStorageQuery fastdfsStorageQuery) throws Exception {
        byte[] bytes;

        try {
            StorageClient storageClient = trackerClientService
                    .getFetchStorage(fastdfsStorageQuery.getGroup(),
                            fastdfsStorageQuery.getPath());

            bytes = storageClientService.downloadFile(storageClient,
                    fastdfsStorageQuery.getGroup(),
                    fastdfsStorageQuery.getPath(),
                    byteArrayFdfsFileInputStreamHandler);

        } catch (FdfsException e) {
            logger.error("从Fdfs中获取文件读取失败. fastdfsStorageQuery: {}, detail: {}", fastdfsStorageQuery, e.getMessage());
            throw new Exception("从Fdfs中获取文件读取失败.");
        }

        return bytes;

    }
}

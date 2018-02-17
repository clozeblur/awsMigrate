package com.clozeblur.re.aws.migrate.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
public class SSLItem {

    private static final Logger logger = LoggerFactory.getLogger(SSLItem.class);

    private static SSLContext sslContext;
    private static X509TrustManager trustManager;

    static {
        sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSLv3");
        } catch (NoSuchAlgorithmException e) {
            logger.error("ssl创建实例失败");
        }

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        if (sslContext != null) {
            try {
                sslContext.init(null, new TrustManager[] { trustManager }, null);
            } catch (KeyManagementException e) {
                logger.error("ssl初始化信任证书失败");
            }
        }
    }

    public static SSLContext getSslContext() {
        return sslContext;
    }
}

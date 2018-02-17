package com.clozeblur.re.aws.util;

import com.clozeblur.re.aws.migrate.constants.SSLItem;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    private static CloseableHttpClient httpClient;

    static {
        HttpClientBuilder builder = HttpClients.custom();
        builder.setConnectionTimeToLive(30L, TimeUnit.SECONDS);
        builder.setMaxConnTotal(2000);
        builder.setMaxConnPerRoute(2000);

        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));

        // 保持长连接配置，需要在头添加Keep-Alive
        builder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
        headers.add(new BasicHeader("Connection", "keep-alive"));

        builder.setDefaultHeaders(headers);

        try {
            builder.setSSLContext(SSLItem.getSslContext());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        builder.setDefaultRequestConfig(requestConfig);

        httpClient = builder.build();
    }

    /**
     * 简单的get请求
     *
     * @param url
     * @return
     */
    public static HttpResult doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                LOGGER.debug("response status: {}", response.getStatusLine());
                Integer statusCode = response.getStatusLine().getStatusCode();
                String content = EntityUtils.toString(response.getEntity());
                return new HttpResult(statusCode, content);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            httpGet.releaseConnection();
        }
        return null;
    }

    public static HttpResult doPut(String url, File file, Map<String, String> param) {
        HttpPut httpPut = new HttpPut(url);
        for (String head : param.keySet()) {
            httpPut.setHeader(head, param.get(head));
        }
        HttpEntity entity = null;
        try {
            entity = EntityBuilder.create().setContentType(ContentType.APPLICATION_OCTET_STREAM).setFile(file).build();
//            entity = MultipartEntityBuilder
//                    .create()
//                    .setContentType(ContentType.APPLICATION_OCTET_STREAM)
//                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
//                    .addPart("uploadFile", body)
//                    .setCharset(CharsetUtils.get("UTF-8"))
//                    .build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        httpPut.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
            Integer statusCode = response.getStatusLine().getStatusCode();
            String content = getResult(response);
            return new HttpResult(statusCode, content);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            httpPut.releaseConnection();
        }
        return null;
    }

    public static HttpResult doPutByStream(String url, byte[] bytes, Map<String, String> param) {
        ByteArrayEntity entity = new ByteArrayEntity(bytes, ContentType.APPLICATION_OCTET_STREAM);
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
            if (response != null) {
                Integer statusCode = response.getStatusLine().getStatusCode();
                String content = getResult(response);
                return new HttpResult(statusCode, content);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            httpPut.releaseConnection();
        }
        return null;
    }

    public static void testDoGet() throws Exception {
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                HttpResult result = HttpClientUtils.doGet("http://www.baidu.com?test=" + System.currentTimeMillis());
                System.out.println(result);
            }).start();
        }

        TimeUnit.SECONDS.sleep(2000);
    }

    private static String getResult(CloseableHttpResponse httpResponse) throws ParseException, IOException {
        String result = null;
        if (httpResponse == null) {
            return null;
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity == null) {
            return null;
        }
        result = EntityUtils.toString(entity, Charset.defaultCharset());
        EntityUtils.consume(entity);// 关闭应该关闭的资源，适当的释放资源 ;也可以把底层的流给关闭了
        return result;
    }

    public static byte[] download(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (Objects.equals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK)) {
                    HttpEntity entity = response.getEntity();
                    if (entity.isStreaming()) {
                        return FileCopyUtils.copyToByteArray(entity.getContent());
                    }
                } else {
                    LOGGER.error("download 请求失败:\n {}", EntityUtils.toString(response.getEntity()));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            httpGet.releaseConnection();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            testDoGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class HttpResult implements Serializable {

        private Integer statusCode;
        private String content;

        public HttpResult(Integer statusCode, String content) {
            this.statusCode = statusCode;
            this.content = content;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "HttpResult{" +
                    "statusCode=" + statusCode +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

}

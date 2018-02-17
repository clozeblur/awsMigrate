package com.clozeblur.re.aws.migrate.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.clozeblur.re.aws.util.HttpClientUtils;
import com.clozeblur.re.aws.util.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
public class AudioUploadClient {

    private static final Logger logger = LoggerFactory.getLogger(AudioUploadClient.class);

    private static final String host = "话务平台host";

    private static final String api = "话务平台api";

    private static final Integer appId = 0;

    private static final String appKey = "???";

    private static final Map<String, String> header;

    static {
        header = new HashMap<>();
        header.put("Host", "s3.cn-north-1.amazonaws.com.cn");
        header.put("Content-Type", "audio/mpeg");
    }

    public static String generatePreSign(String url) throws IOException, URISyntaxException {
        URI uri = generateURIForPreSign(url);
        HttpClientUtils.HttpResult result = HttpClientUtils.doGet(uri.toString());
        String body = "";
        if (result != null && result.getStatusCode() != null && !StringUtils.isBlank(result.getContent())) {
            body = result.getContent().replace("\\/", "/");
        }
        JSONObject json = JSON.parseObject(body);
        return json.getString("data");
    }

    public static URI generateURIForPreSign(String url) throws URISyntaxException {
        URI uri = null;
        Date now = new Date();
        Long ts = now.getTime() / 1000;
        Map<String, String> param = new HashMap<>();
        param.put("app_id", appId.toString());
        param.put("key", url);
        param.put("ts", ts.toString());
        String sign = createTpSign(param);
        uri = new URIBuilder()
                .setScheme("http")
                .setHost(host)
                .setPath(api)
                .setParameter("app_id", appId.toString())
                .setParameter("ts", ts.toString())
                .setParameter("key", url)
                .setParameter("sign", sign)
                .build();
        return uri;
    }

    public static String createTpSign(Map<String, String> param) {
        Set<String> keys = param.keySet();
        String[] keyArr = keys.toArray(new String[]{});
        Arrays.sort(keyArr);
        StringBuilder sb = new StringBuilder();
        for (String key : keyArr) {
            sb.append(key).append("=").append(param.get(key)).append("&");
        }
        sb.append("app_key=").append(appKey);
        return MD5Utils.md5(sb.toString());
    }

    public static String uploadToS3(String url, byte[] bytes) {
        try {
            String preSign = generatePreSign(url);
            HttpClientUtils.HttpResult result = HttpClientUtils.doPutByStream(preSign, bytes, header);
            if (result != null && result.getStatusCode() != null && StringUtils.isNotBlank(result.getContent())) {
                return result.getContent();
            }
        } catch (IOException | URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static String uploadToS3(String url, File file) throws Exception {
        String preSign = generatePreSign(url);
        preSign = preSign.replace("https", "http");
        HttpClientUtils.HttpResult result = HttpClientUtils.doPut(preSign, file, header);
        if (result != null && result.getStatusCode() != null
                && (result.getStatusCode().equals(HttpStatus.OK.value())
                || result.getStatusCode().equals(HttpStatus.FOUND.value()))) {
            return result.getStatusCode().toString();
        }
        return null;
    }
}

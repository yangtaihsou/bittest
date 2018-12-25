package com.bittest.platform.pg.common;

import com.bittest.platform.bg.domain.po.HttpRes;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 2018-08-28.
 * http请求util
 */
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpRes.class);

    /**
     * pst方式提交
     *
     * @param url 请求地址
     * @param map 请求内容
     * @return 请求结果
     */
    public static String sendByPost(String url, Map<String, String> map) throws Exception {

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
        HttpResponse httpResponse = null;
        httpResponse = httpclient.execute(httpost);
        HttpEntity responseEntity = httpResponse.getEntity();
        String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
        httpost.releaseConnection();
        return result;
    }

    /**
     * get方式请求
     *
     * @param url   请求地址
     * @param heads 信息头
     * @return 请求结果
     */
    public static String sendByGet(String url, Map<String, String> heads) throws Exception {
        HttpRes httpRes = new HttpRes();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        //添加信息头
        if (null != heads) {
            for (Map.Entry<String, String> head : heads.entrySet()) {
                httpGet.setHeader(head.getKey(), head.getValue());
            }
        }
        HttpResponse httpResponse = null;
        httpResponse = httpclient.execute(httpGet);
        HttpEntity responseEntity = httpResponse.getEntity();
        String result = EntityUtils.toString(responseEntity);
        httpGet.releaseConnection();
        return result;
    }

}

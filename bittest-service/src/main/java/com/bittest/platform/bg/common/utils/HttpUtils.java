package com.bittest.platform.bg.common.utils;

import com.bittest.platform.bg.domain.po.HttpRes;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
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
     * @param url   请求地址
     * @param body  请求参数
     * @param heads 信息头
     * @return 请求结果
     */
    public static HttpRes sendByPost(String url, String body, Map<String, String> heads) {
        HttpRes httpRes = new HttpRes();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 4000);//连接时间  //TODO 后期可以给接口属性添加时间
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);//数据传输时间
        HttpPost httpost = new HttpPost(url);
        //添加信息头
        HttpResponse httpResponse = null;
        try {
            if (null != heads) {
                for (Map.Entry<String, String> head : heads.entrySet()) {
                    httpost.setHeader(head.getKey(), head.getValue());
                }
            }
            StringEntity entity = new StringEntity(body, Charset.forName("UTF-8"));
            httpost.setEntity(entity);
            httpResponse = httpclient.execute(httpost);
            HttpEntity responseEntity = httpResponse.getEntity();
            httpRes.setStatus(httpResponse.getStatusLine().getStatusCode());
            httpRes.setResBody(EntityUtils.toString(responseEntity, Charset.forName("UTF-8")));
        } catch (Exception e) {
            httpRes.setResBody(""+e.toString());
            httpRes.setStatus(1000);
        } finally {
            httpost.releaseConnection();
            return httpRes;
        }


    }

    /**
     * get方式请求
     *
     * @param url   请求地址
     * @param heads 信息头
     * @return 请求结果
     */
    public static HttpRes sendByGet(String url, Map<String, String> heads) {
        HttpRes httpRes = new HttpRes();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 4000);//连接时间
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);//数据传输时间
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = null;
        try {
            //添加信息头
            if (null != heads) {
                for (Map.Entry<String, String> head : heads.entrySet()) {
                    httpGet.setHeader(head.getKey(), head.getValue());
                }
            }
            httpResponse = httpclient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            httpRes.setStatus(httpResponse.getStatusLine().getStatusCode());
            httpRes.setResBody(EntityUtils.toString(responseEntity));
        } catch (IOException e) {
            httpRes.setStatus(httpResponse.getStatusLine().getStatusCode());
            httpRes.setResBody(e.toString());
        } finally {
            httpGet.releaseConnection();
            return httpRes;
        }
    }

}

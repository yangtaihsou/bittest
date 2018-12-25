package com.bittest.platform.bg.common.utils;



import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * User: yangkuan@jd.com
 * Date: 15-1-13
 * Time: 下午4:34
 */
public class VelocityParaContext {
    /*
     * 更具模板生成文件的内容
     */
    public static String renderVelocityTemplate(Map<String, Object> params, String velocityName) {
        BufferedReader reader = null;
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(velocityName);
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            return evaluate(reader, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        }
        return null;
    }

    /**
     * 更具模板，将参数带入生成综合的文件
     * @param params
     * @param velocityName
     * @return
     */
    public static String renderComplexVelocityTemplate(List<Map<String, Object>> params, String velocityName) {
        BufferedReader reader = null;
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(velocityName);
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("param", params);
            StringWriter writer = new StringWriter();
            Velocity.evaluate(velocityContext, writer, "", reader);
            return  writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        return null;
    }

    public static String evaluate(Reader reader, Map<String, Object> variables) {
        // VelocityEngine velocityEngine = new VelocityEngine();
        // velocityEngine.init();
        VelocityContext velocityContext = new VelocityContext();
        if (variables != null) {
            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key != null && key.trim().length() > 1) {
                    velocityContext.put(key, value);
                }
            }
        }
        StringWriter writer = new StringWriter();
        try {
            Velocity.evaluate(velocityContext, writer, "", reader);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return writer.toString();
    }

    public static void main(String[] args){
        String contextString = VelocityParaContext.renderVelocityTemplate(null, "vm/testTaskEmail.vm");
        System.out.print(contextString);
    }
}

package com.bittest.platform.bg.common.utils;

import com.bittest.platform.bg.common.context.CaseParaApplicationContext;
import io.netty.util.internal.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数化替换共用类
 * 2018-08-27.
 */
public class ParameterReplaceUtils {

    private static final Logger log = LoggerFactory.getLogger(ParameterReplaceUtils.class);

    /**
     * 替换请求参数化信息
     *
     * @param reqParam  用户请求信息
     * @param caseParam 用户参数集合
     * @return 替换所有参数化信息（UUID,time,Random,userParam）
     */
    public static String replaceParam(String reqParam, Map<String, String> caseParam, Map<String, String> taskParam) {
        String reqInfo = "";
        if (!StringUtils.isEmpty(reqParam)) {
            //uuid替换
            reqInfo = uuidReplace(reqParam, caseParam);
            //time替换
            reqInfo = timeReplace(reqInfo, caseParam);
            //random替换
            reqInfo = ramdomReplace(reqInfo, caseParam);
            reqInfo = userParamReplaceFromCaseResContext(reqInfo);
            //用户参数化替换(使用全局的)
            reqInfo = userParamReplace(reqInfo, caseParam, taskParam);
        }
        return reqInfo;
    }

    /**
     * 替换UUID参数化
     *
     * @param reqParam  用户请求信息
     * @param caseParam 用例公共参数
     * @return 替换UUID后的用户请求信息
     */
    public static String uuidReplace(String reqParam, Map<String, String> caseParam) {
        // uuid匹配
        String pattern = "\\$\\{\\s*(\\s*__uuid\\s*)\\s*,*\\s*(\\s*\\w*\\s*)\\s*}";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(reqParam);
        String variable = "";
        while (m.find()) {
            try {
                //调用UUID服务 生成UUID
                UUID uuid = UUID.randomUUID();
                //替换UUID信息
                reqParam = reqParam.replace(m.group(0), uuid.toString());
                variable = m.group(2);
                if (!StringUtils.isEmpty(variable)) {
                    if (null != caseParam) {
                        //保存用户变量
                        caseParam.put(variable.trim(), uuid.toString());
                    }
                }
            } catch (Exception e) {
                log.info("UUID提取异常:{}", e.toString());
            }
        }
        return reqParam;
    }

    /**
     * 替换time参数化
     *
     * @param reqParam  用户请求信息
     * @param caseParam 用例公共参数
     * @return 替换生成time后的用户请求信息
     */
    public static String timeReplace(String reqParam, Map<String, String> caseParam) {
        // time匹配
//        String pattern = "\\$\\{\\s*__time\\s*\\(\\s*(\\s*.+?\\s*),?(\\s*\\w*\\s*)\\s*\\)\\s*}";
        String pattern = "\\$\\{\\s*__time\\s*\\(\\s*(.+?)\\s*\\)\\s*}";
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(reqParam);
        String variable = "";
        String value;
        String[] temp;
        //遍历匹配对象
        while (m.find()) {
            String time = "";
            log.info("提取内容为：{}", m.group(1).trim());
            try {
                value = m.group(1);
                if (!StringUtils.isEmpty(value) && (temp = value.split(",")).length <= 2) {
                    SimpleDateFormat sdf = new SimpleDateFormat(temp[0].trim());
                    time = sdf.format(new Date());
                    if (temp.length == 2) {
                        variable = temp[1].trim();
                    }
                    //替换time信息
                    reqParam = reqParam.replace(m.group(0), time);
                    if (!StringUtils.isEmpty(variable)) {
                        if (null != caseParam) {
                            caseParam.put(variable.trim(), time);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("time提取异常：{}", e.toString());
            }
        }
        return reqParam;
    }

    /**
     * 随机数生成
     *
     * @param reqParam  用户请求信息
     * @param caseParam 用例公共参数
     * @return 替换随机数后的用户请求信息
     */
    public static String ramdomReplace(String reqParam, Map<String, String> caseParam) {
        // random匹配
        String pattern = "\\$\\{\\s*__random\\s*\\((\\s*\\d+\\s*),(\\s*\\d+\\s*),?(\\s*\\w*\\s*)\\s*\\)\\s*}";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(reqParam);
        String variable;
        //遍历匹配对象
        while (m.find()) {
            try {
                //获取最小值
                long min = Long.parseLong(m.group(1).trim());
                //获取最大值
                long max = Long.parseLong(m.group(2).trim());
                //生成随机数
                String rand = String.valueOf(ThreadLocalRandom.current().nextLong(min, max + 1));
                //替换random信息
                reqParam = reqParam.replace(m.group(0), rand);
                variable = m.group(3);
                if (!StringUtils.isEmpty(variable)) {
                    if (null != caseParam) {
                        caseParam.put(variable.trim(), rand);
                    }
                }
            } catch (NumberFormatException e) {
                log.error("随机数提取异常：{}", e.toString());
            }
        }
        return reqParam;
    }

    /**
     * 替换用户参数化信息
     *
     * @param reqParam  用户请求参数
     * @param caseParam 用例参数集合
     * @param taskParam 任务参数集合（匹配优先级 任务参数集合>用例参数集合）
     * @return 替换用户参数化后的请求信息
     */
    public static String userParamReplace(String reqParam, Map<String, String> caseParam, Map<String, String> taskParam) {
        // 普通用户变量匹配
        String pattern = "\\$\\{\\s*(\\s*\\w*\\s*)\\s*}";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(reqParam);
        String variable = "";
        while (m.find()) {
            try {
                variable = m.group(1).trim();
                if (null != caseParam && caseParam.containsKey(variable)) {
                    reqParam = reqParam.replace(m.group(0), caseParam.get(variable));
                } else if (null != caseParam && caseParam.containsKey(variable)) {
                    reqParam = reqParam.replace(m.group(0), taskParam.get(variable));
                }
            } catch (Exception e) {
                log.error("用户变量提取异常：{}", e.toString());
            }
        }
        return reqParam;
    }

    /**
     * 使用前面case的返回数据进行填充本次case请求的参数
     *
     * @return 替换用户参数化后的请求信息
     */
    public static String userParamReplaceFromCaseResContext(String reqParam) {
        // 普通用户变量匹配
        String pattern = "\\$\\{\\s*(\\s*\\w*\\s*)\\s*}";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(reqParam);
        String variable = "";
        while (m.find()) {
            try {
                variable = m.group(1).trim();
                String value = getValueFromcontext(variable);
                if(value!=null) {
                    reqParam = reqParam.replace(m.group(0), value);
                }
            } catch (Exception e) {
                log.error("用户变量提取异常：{}", e.toString());
            }
        }
        return reqParam;
    }

    public static String getValueFromcontext(String key){
        if(CaseParaApplicationContext.getContext()==null){
            return null;
        }
        Map<String, Object>  paraMap =  CaseParaApplicationContext.getContext().paraMap();
        if(paraMap==null){
            return null;
        }
        for (Object jsonString : paraMap.values()) {
           String value = getParaValue((String)jsonString,key);
            if(value!=null){
                return value;
            }
        }
        return null;
    }
    /**
     * 从json串里，提取key的value
     * 不使用正则表达式或者深度遍历
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static String getParaValue(String jsonString, String key) {
        if(jsonString==null || jsonString.equals("")){
            return null;
        }
        key = key + "\":";//因为json串都是api接口返回，所以key大部分都是双引号包围
        int index = jsonString.indexOf(key);
        if(index<0){
            return null;
        }
        int firstPoint = index + key.length();
        String afterString = jsonString.substring(firstPoint);
        int afterIndex = afterString.indexOf(",\"");//下一个key的起始
        String value = jsonString.substring(firstPoint, firstPoint + afterIndex);
        String keyValue = null;
        int valueBefore = value.indexOf("\"");
        if (valueBefore == 0) {
            int valueAfter = value.lastIndexOf("\"");//key的value末尾-双引号
            keyValue = value.substring(valueBefore + 1, valueAfter);

        } else {
            valueBefore = value.indexOf("\'");//key的value末尾-单引号
            if (valueBefore == 0) {
                int valueAfter = value.lastIndexOf("\'");
                keyValue = value.substring(valueBefore + 1, valueAfter);
            }else{
                keyValue = value;
            }
        }
        return keyValue;
    }
}

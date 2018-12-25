package com.bittest.platform.pg.tag;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.HashMap;
import java.util.Map;

public class AuthTag extends TagSupport implements DynamicAttributes {


    private static final long serialVersionUID = 1L;
    final Logger logger = LoggerFactory.getLogger(AuthTag.class);
    private String code;

    private Map<String, Object> dynamicAttributes = new HashMap<String, Object>();


    public int doStartTag() throws JspException {
        try {

            boolean isContains = authControlCheck();
            if (isContains) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }

        } catch (Exception e) {
            logger.error("权限标签处理错误,ex:{}", e);
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    /**
     * 权限检查，可以返回true，没有权限返回false
     *
     * @return
     */
    public boolean authControlCheck() {

        if (StringUtils.isEmpty(code)) {
            return true;
        }


        boolean isContains = true;
        return isContains;

    }

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        dynamicAttributes.put(localName, value);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}

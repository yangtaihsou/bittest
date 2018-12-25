package com.bittest.platform.pg.tag;

import com.bittest.platform.pg.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DatepickerTag extends TagSupport implements DynamicAttributes {

    private static final long serialVersionUID = 1L;
    public static String DEFAULT_FORMART = "yyyy-MM-dd";
    private Map<String, Object> dynamicAttributes = new HashMap<String, Object>();
    private String id;
    private String name;
    private Object value;
    private String format;
    private boolean showDefault;
    private String cssClass;

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut(); // 重要
        StringBuffer sb = new StringBuffer();
        try {
            sb.append(createHtml());
            out.print(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public String createHtml() {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_FORMART;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group date singledate");
        if (StringUtils.isNotEmpty(cssClass)) {
            sb.append(" ");
            sb.append(cssClass);
        }
        sb.append("'>");
        sb.append("<input type='text'");
        if (StringUtils.isNotEmpty(id)) {
            sb.append(" id='");
            sb.append(id);
            sb.append("'");
        }
        if (StringUtils.isNotEmpty(name)) {
            sb.append(" name='");
            sb.append(name);
            sb.append("'");
        }
        if (value != null) {
            sb.append(" value='");
            if (value instanceof Date) {
                sb.append(DateUtil.convertDateToString((Date) value, format));
            } else {
                sb.append(value);
            }
            sb.append("'");
        } else {
            if (showDefault) {
                value = new Date();
                sb.append("value='");
                if (StringUtils.isEmpty(format)) {
                    format = DEFAULT_FORMART;
                }
                sb.append(DateUtil.convertDateToString((Date) value, format));
                sb.append("'");
            }
        }
        sb.append(" format='");
        sb.append(format);
        sb.append("'");
        sb.append(" class='datepicker form-control' readonly='true'");
        //dynamicAttributes
        Iterator<Map.Entry<String, Object>> iter = this.dynamicAttributes.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> value = iter.next();
            sb.append(" ");
            sb.append(value.getKey());
            Object o = value.getValue();
            if (o != null) {
                sb.append("='");
                sb.append(o);
                sb.append("'");
            }
        }
        sb.append(">");
        //sb.append("</div>");
        sb.append("<span class='input-group-addon'><i class='glyphicon glyphicon-th'></i></span></div>");
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isShowDefault() {
        return showDefault;
    }

    public void setShowDefault(boolean showDefault) {
        this.showDefault = showDefault;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        dynamicAttributes.put(localName, value);
    }
}

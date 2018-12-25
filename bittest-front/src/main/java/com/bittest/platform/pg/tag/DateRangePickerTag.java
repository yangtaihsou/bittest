package com.bittest.platform.pg.tag;

import com.bittest.platform.pg.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Date;


public class DateRangePickerTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    public static String DEFAULT_FORMART = "yyyy-MM-dd";

    private String sid;
    private String eid;
    private String sname;
    private String ename;
    private Object svalue;
    private Object evalue;
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
        sb.append("<div class='input-group date rangedate");
        if (StringUtils.isNotEmpty(cssClass)) {
            sb.append(" ");
            sb.append(cssClass);
        }
        sb.append("'>");
        sb.append("<div class='form-control daterange input-group'");
//		sb.append(" format='");//暂时只支持 yyyy-MM-dd这种
//		sb.append(format);
//		sb.append("'");
        sb.append(" showDefault='");
        sb.append(showDefault);
        sb.append("'");
        sb.append(">");
        sb.append("<span dateoption='double'>");
        //日期值的显示
        if (svalue != null) {
            if (svalue instanceof Date) {
                sb.append(DateUtil.convertDateToString((Date) svalue, format));
            } else {
                sb.append(svalue);
            }
        }
        if (evalue != null) {
            sb.append(" - ");
            if (evalue instanceof Date) {
                sb.append(DateUtil.convertDateToString((Date) evalue, format));
            } else {
                sb.append(evalue);
            }
        }
        sb.append("</span>");
        //start date 的隐藏域
        sb.append("<input type='hidden' class='start'");
        if (StringUtils.isNotEmpty(sid)) {
            sb.append(" id='");
            sb.append(sid);
            sb.append("'");
        }
        if (StringUtils.isNotEmpty(sname)) {
            sb.append(" name='");
            sb.append(sname);
            sb.append("'");
        }
        if (svalue != null) {
            sb.append(" value='");
            if (svalue instanceof Date) {
                sb.append(DateUtil.convertDateToString((Date) svalue, format));
            } else {
                sb.append(svalue);
            }
            sb.append("'");
        } else {
            if (showDefault) {
                svalue = new Date();
                sb.append("value='");
                if (StringUtils.isEmpty(format)) {
                    format = DEFAULT_FORMART;
                }
                sb.append(DateUtil.convertDateToString((Date) svalue, format));
                sb.append("'");
            }
        }
        sb.append(" />");
        //end date 的隐藏域
        sb.append("<input type='hidden' class='end'");
        if (StringUtils.isNotEmpty(eid)) {
            sb.append(" id='");
            sb.append(eid);
            sb.append("'");
        }
        if (StringUtils.isNotEmpty(ename)) {
            sb.append(" name='");
            sb.append(ename);
            sb.append("'");
        }
        if (evalue != null) {
            sb.append(" value='");
            if (evalue instanceof Date) {
                sb.append(DateUtil.convertDateToString((Date) evalue, format));
            } else {
                sb.append(evalue);
            }
            sb.append("'");
        } else {
            if (showDefault) {
                evalue = new Date();
                sb.append("value='");
                if (StringUtils.isEmpty(format)) {
                    format = DEFAULT_FORMART;
                }
                sb.append(DateUtil.convertDateToString((Date) evalue, format));
                sb.append("'");
            }
        }
        sb.append(" />");
        sb.append("</div>");
        //图标
        sb.append("<span class='input-group-addon' onclick='$(this).prev().find(\'span\').click();'><i class='glyphicon glyphicon-th'></i></span>");
        sb.append("</div>");//end
        return sb.toString();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Object getSvalue() {
        return svalue;
    }

    public void setSvalue(Object svalue) {
        this.svalue = svalue;
    }

    public Object getEvalue() {
        return evalue;
    }

    public void setEvalue(Object evalue) {
        this.evalue = evalue;
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

}

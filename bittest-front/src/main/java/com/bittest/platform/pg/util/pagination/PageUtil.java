package com.bittest.platform.pg.util.pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class PageUtil {

    public static String PAGE_CONTAINER_START = "<form class=\"paginateForm\" method=\"post\" action=\"\"><div class=\"dataTables_paginate paging_bootstrap\"><ul class=\"pagination\">";

    public static String PAGE_CONTAINER_END = "</ul></div></form>";

    public static String INFO_CONTAINER_START = "<div class=\"dataTables_info\" role=\"status\" aria-live=\"polite\">";

    public static String INFO_CONTAINER_END = "</div>";

    public static String getPageStr(String url, int pagesize, int pages, int count) {
        StringBuffer sbInfo = new StringBuffer();
        StringBuffer sbPage = new StringBuffer();
        if (pages < 1)
            pages = 1;

        if (url.indexOf("?") < 0) {
            url = url + "?";
        }
        int totalpage = 0;
        totalpage = ((count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1));
        if (totalpage == 0) {
            totalpage = 1;
        }
        if (pages > totalpage) {
            pages = totalpage;
        } else if (pages < 1) {
            pages = 1;
        }
        Integer iDisplayStart = (pages - 1) * pagesize + 1;
        Integer iDisplayEnd = pages * pagesize;
        if (iDisplayEnd > count) {
            iDisplayEnd = count;
        }
        sbInfo.append(INFO_CONTAINER_START);
        if (count < 0) {
            sbInfo.append("显示第 0 至 0 条记录，共 0 条");
        } else {
            sbInfo.append("显示第 ");
            sbInfo.append(iDisplayStart);
            sbInfo.append("至 ");
            sbInfo.append(iDisplayEnd);
            sbInfo.append("条记录，共  ");
            sbInfo.append(count);
            sbInfo.append("条");
        }
        sbInfo.append(INFO_CONTAINER_END);

        sbPage.append(PAGE_CONTAINER_START);
        if (count < 0) {
            sbPage.append("<li class=\"first disabled\"><a href=\"javascript:void(0)\">首页</a></li> ");
            sbPage.append("<li class=\"prev disabled\"><a href=\"javascript:void(0)\">← 上页</a></li> ");
            sbPage.append("<li class=\"next disabled\"><a href=\"javascript:void(0)\">下页 → </a></li>");
            sbPage.append("<li class=\"last disabled\"><a href=\"javascript:void(0)\">末页</a></li>");
        } else {
            if (pages == 1) {
                sbPage.append("<li class=\"first disabled\"><a href=\"javascript:void(0)\">首页</a></li> ");
                sbPage.append("<li class=\"prev disabled\"><a href=\"javascript:void(0)\">← 上页</a></li> ");
            } else {
                sbPage.append("<li class=\"first\"><a href=\"javascript:void(0)\"");
                sbPage.append(" src=\"");
                sbPage.append(url);
                sbPage.append("&p=1");
                sbPage.append("\">");
                sbPage.append("首页</a></li>");
                sbPage.append("<li class=\"prev\"><a href=\"javascript:void(0)\"");
                sbPage.append(" src=\"");
                sbPage.append(url);
                sbPage.append("&p=");
                sbPage.append(pages - 1);
                sbPage.append("\">");
                sbPage.append("← 上页</a></li>");
            }

            int pagesFrom = 1, pagesTo = totalpage;
            if (pages <= 5 && totalpage - pages > 5) {
                pagesTo = totalpage >= 10 ? 10 : totalpage;
            } else if (pages > 5 && totalpage - pages > 5) {
                pagesFrom = pages - 5;
                pagesTo = pages + 5;
            } else if (pages > 5 && totalpage - pages <= 5) {
                pagesFrom = totalpage > 10 ? totalpage - 5 : 1;
            }
            for (int ik = pagesFrom; ik <= pagesTo; ik++) {
                if (ik == pages) {
                    sbPage.append("<li class=\"active\"><a href=\"javascript:void(0)\"");
                    sbPage.append(" src=\"");
                    sbPage.append(url);
                    sbPage.append("&p=");
                    sbPage.append(ik);
                    sbPage.append("\">");
                    sbPage.append(pages);
                    sbPage.append("</a></li>");
                } else {
                    sbPage.append("<li><a href=\"javascript:void(0)\"");
                    sbPage.append(" src=\"");
                    sbPage.append(url);
                    sbPage.append("&p=");
                    sbPage.append(ik);
                    sbPage.append("\">");
                    sbPage.append(ik);
                    sbPage.append("</a></li>");
                }
            }
            if (pages == totalpage) {
                sbPage.append("<li class=\"next disabled\"><a href=\"javascript:void(0)\">下页 → </a></li>");
                sbPage.append("<li class=\"last disabled\"><a href=\"javascript:void(0)\">末页</a></li>");
            } else {
                sbPage.append("<li class=\"next\"><a href=\"javascript:void(0)\"");
                sbPage.append(" src=\"");
                sbPage.append(url);
                sbPage.append("&p=");
                sbPage.append(pages + 1);
                sbPage.append("\">");
                sbPage.append("下页 → </a></li>");
                sbPage.append("<li class=\"last\"><a href=\"javascript:void(0)\"");
                sbPage.append(" src=\"");
                sbPage.append(url);
                sbPage.append("&p=");
                sbPage.append(totalpage);
                sbPage.append("\">");
                sbPage.append("末页</a></li>");
            }
            sbPage.append("<input type=\"text\" class=\"redirect\" value=\"");
            sbPage.append(pages);
            sbPage.append("\"/>");
        }
        sbPage.append(PAGE_CONTAINER_END);
        return sbInfo.append(sbPage).toString();
//		if (count < 1 || count < pagesize) {
//			return "";
//		}

//		StringBuffer sb = new StringBuffer();
//		if (pages == 1) {
//			sb.append(
//					"<div class=\"text-center\"><ul class=\"pagination\"><li class=\"disabled\"><a  href=\"javascript:void(0)\" "
//							+ "src=\"" + url + "&p=1\" >首页</a></li>");
//		} else {
//			sb.append("<div class=\"text-center\"><ul class=\"pagination\"><li ><a  href=\"javascript:void(0)\" "
//					+ "src=\"" + url + "&p=1\" >首页</a></li>");
//		}
//		int pagesFrom = 1, pagesTo = totalpage;
//		if (pages <= 5 && totalpage - pages > 5) {
//			pagesTo = totalpage >= 10 ? 10 : totalpage;
//		} else if (pages > 5 && totalpage - pages > 5) {
//			pagesFrom = pages - 5;
//			pagesTo = pages + 5;
//		} else if (pages > 5 && totalpage - pages <= 5) {
//			pagesFrom = totalpage > 10 ? totalpage - 5 : 1;
//		}
//
//		for (int ik = pagesFrom; ik <= pagesTo; ik++) {
//			if (ik == pages) {
//				sb.append("<li class=\"active\"><a href=\"javascript:void(0)\">" + ik
//						+ "<span class=\"sr-only\">(current)</span></a></li> ");
//			} else {
//				sb.append("<li><a href=\"javascript:void(0)\" src=\"" + url + "&p=" + ik + "\">&nbsp;" + ik
//						+ "</a></li> ");
//			}
//		}
//		if (totalpage == pages) {
//			sb.append("<li  class=\"disabled\"><a  href=\"javascript:void(0)\" src=\"" + url + "&p=" + totalpage
//					+ "\" class=\"pre\">末页</a></li>");
//		} else {
//			sb.append("<li><a href=\"javascript:void(0)\"  src=\"" + url + "&p=" + totalpage
//					+ "\" class=\"pre\">末页</a></li>");
//		}
//		sb.append("</ul></div>");
//		return sb.toString();
    }

    public static String joinParameter(HttpServletRequest request) {
        return joinParameter(request, new String[]{"p"}, true);
    }

    public static String joinParameter(HttpServletRequest request, String[] excludes, boolean attachURI) {
        String uri = request.getRequestURI();
        StringBuffer url = new StringBuffer();

        if (uri.toString().indexOf("?") < 0) {
            url.append(uri).append("?");
        }
        Enumeration<String> enums = request.getParameterNames();
        List<String> paras = new ArrayList<String>();

        if (enums != null) {
            while (enums.hasMoreElements()) {
                String para = enums.nextElement();

                if (isContain(excludes, para))
                    continue;
                paras.add(para);
            }
            Collections.sort(paras, String.CASE_INSENSITIVE_ORDER);
            for (int m = 0; m < paras.size(); m++) {

                String para = paras.get(m);
                String[] values = request.getParameterValues(para);
                for (int k = 0; values != null && k < values.length; k++) {
                    String value = values[k];
                    if (!isBlankStr(value)) {
                        url.append(para).append("=").append(value).append("&");
                    }
                }
            }
        }

        if (url.length() > 0 && url.charAt(url.length() - 1) == '&')
            url.deleteCharAt(url.length() - 1);
        return url.toString();

    }

    public static boolean isContain(String[] stringArray, String checkstr) {

        if (stringArray == null || isBlankStr(checkstr)) {
            return false;
        }
        for (int i = 0; i < stringArray.length; i++) {
            if (checkstr.equals(stringArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlankStr(String s) {
        return s == null || s.trim().length() == 0;
    }
}

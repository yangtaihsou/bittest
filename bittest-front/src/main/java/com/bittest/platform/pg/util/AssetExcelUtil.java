package com.bittest.platform.pg.util;

import com.bittest.platform.pg.common.ExcelTypeEnum;
import com.bittest.platform.pg.domain.ExcelUpMsg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AssetExcelUtil<T extends ExcelUpMsg> extends ExcelUtil<T> {

    static final Logger logger = LoggerFactory.getLogger(AssetExcelUtil.class);

    public AssetExcelUtil(ExcelTypeEnum type) {
        super(type);
    }

    /**
     * 获取输出流
     *
     * @param outfilename
     * @param response
     * @return
     */
    public static OutputStream getOutputStream(String outfilename, HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // String srcFileName = java.net.URLEncoder.encode(outfilename, "utf-8");
            //srcFileName = new String(srcFileName.getBytes("utf-8"), "iso-8859-1");
            response.setCharacterEncoding("utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + outfilename);
            OutputStream outputStream = response.getOutputStream();
            return outputStream;
        } catch (UnsupportedEncodingException ex) {
            logger.error("下载数据出现异常！", ex);
        } catch (IOException ex) {
            logger.error("下载数据出现异常！", ex);
        }
        return null;
    }

    public static String toUtf8String(String s) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    logger.error("将文件名中的汉字转为UTF8编码的串时错误，输入的字符串为：" + s);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据不同浏览器将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原文件名
     * @return 重新编码后的文件名
     */
    public static String toUtf8String(HttpServletRequest request, String s) {
        String agent = request.getHeader("User-Agent");
        try {
            boolean isFireFox = (agent != null && agent.toLowerCase().indexOf("firefox") != -1);
            if (isFireFox) {
                s = new String(s.getBytes("UTF-8"), "ISO8859-1");
            } else {
                s = toUtf8String(s);
                if ((agent != null && agent.indexOf("MSIE") != -1)) {
                    // see http://support.microsoft.com/default.aspx?kbid=816868
                    if (s.length() > 150) {
                        // 根据request的locale 得出可能的编码
                        s = new String(s.getBytes("UTF-8"), "ISO8859-1");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public void writeXlsFile(List<T> excelEntitys, Class<T> excelClass) throws Exception {
        super.writeXlsFile(excelEntitys, excelClass);
    }

    //插入额外数据
    public void insertExtendData(String sumMoney) {
        Sheet sheet = getSheet();
        int rownum = sheet.getLastRowNum();
        Row row = sheet.createRow(rownum + 1);
        Cell cell = row.createCell(0);
        cell.setCellValue(sumMoney);
    }


}

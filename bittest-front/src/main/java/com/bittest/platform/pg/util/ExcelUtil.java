package com.bittest.platform.pg.util;

import com.bittest.platform.pg.common.ExcelTypeEnum;
import com.bittest.platform.pg.domain.ExcelMsg;
import com.bittest.platform.pg.domain.ExcelUpMsg;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil<T extends ExcelUpMsg> {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//默认时间格式
    private Workbook workbook;
    private Sheet sheet;
    private String exceptionMsg;//异常信息，不为空则异常

    /**
     * 从流中读取excel文件，对应的sheet使用的是getSheetAt(0)方法
     *
     * @param xlsFileInStream
     * @param type
     * @throws Exception
     */
    public ExcelUtil(InputStream xlsFileInStream, String type) throws Exception {
        try {
            if (ExcelTypeEnum.xlsx.getCode().equals(type)) {
                workbook = new XSSFWorkbook(xlsFileInStream);
            } else if (ExcelTypeEnum.xls.getCode().equals(type)) {
                workbook = new HSSFWorkbook(xlsFileInStream);
            } else {
                throw new Exception("不支持此格式");
            }
            sheet = workbook.getSheetAt(0);
        } catch (Exception ex) {
            logger.error("ExcelUtil初始化异常", ex);
            throw new Exception(ex);
        }
    }

    /**
     * 从文件中读取excel文件，对应的sheet使用的是getSheetAt(0)方法
     *
     * @param xlsFilePath
     */
    public ExcelUtil(String xlsFilePath) throws Exception {
        FileInputStream in = null;
        try {
            in = new FileInputStream(xlsFilePath);
            if (xlsFilePath.endsWith("." + ExcelTypeEnum.xlsx.getCode())) {
                workbook = new XSSFWorkbook(in);
            } else if (xlsFilePath.endsWith("." + ExcelTypeEnum.xls.getCode())) {
                workbook = new HSSFWorkbook(in);
            }
            sheet = workbook.getSheetAt(0);
        } catch (Exception ex) {
            logger.error(xlsFilePath + "ExcelUtil初始化异常", ex);
            throw new Exception(ex);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
                logger.error("ExcelUtil文件读取流关闭异常", e);
            }
        }
    }

    /**
     * 创建新的excel文件，使用createSheet()
     */
    public ExcelUtil(ExcelTypeEnum type) {
        if (ExcelTypeEnum.xlsx.equals(type)) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }
        setExcelStyle();
        sheet = workbook.createSheet();
    }

    /**
     * 设置excel的文本样式
     */
    private void setExcelStyle() {
        CellStyle setBorder = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("仿宋_GB2312");
        //粗体显示
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 9);
        //选择需要用到的字体格式
        setBorder.setFont(font);
    }

    /**
     * 读取excel文件，并转换成指定的类型（excelClass）
     *
     * @param excelClass
     * @return
     * @throws Exception
     */
    public List<T> readXlsFile(Class<T> excelClass) throws Exception {
        List<T> excelEntitys = new ArrayList<T>();
        // 获取当前类的所有属性
        Map<Integer, Field> fieldMap = getFieldMap(excelClass);
        if (sheet.getLastRowNum() > 20000) {
            throw new Exception("文件长度超过了20000行");
        }
        StringBuffer eMsg = new StringBuffer();
        // 循环行Row，并把对应的值对应到相应实体
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }
            if (xssfRow.getCell(0) == null || StringUtils.isBlank(getValue(xssfRow.getCell(0)))) {
                continue;
            }
            // 新建实体类
            T excelEntity = excelClass.newInstance();
            // 设置索引号
            excelEntity.setIndex(rowNum);
            // 循环列Cell
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum() && cellNum < fieldMap.size(); cellNum++) {
                Cell xssfCell = xssfRow.getCell(cellNum);
//	                if (xssfCell == null) {
//	                    continue;
//	                }
                try {
                    // 设置属性值
                    setFiledValue(fieldMap.get(cellNum), getValue(xssfCell), excelEntity);
                } catch (Exception e) {
                    excelEntity.setParserStatus(false);
                    excelEntity.setBindMsg("参数异常");//反馈信息
                    logger.error("excel转换实体出现异常", e);
                    eMsg.append("行:").append(rowNum).append(",列:").append(cellNum).append("[").append(getValue(xssfCell)).append("]参数异常\n");//异常信息汇总
                }
            }
            if (CollectionUtils.isNotEmpty(excelEntitys)) {
                if (excelEntitys.contains(excelEntity)) {
                    excelEntity.setParserStatus(false);
                    excelEntity.setBindMsg("记录重复");
                    eMsg.append("行:").append(rowNum).append("记录重复\n");
                }
            }
            if (excelEntity.getParserStatus() == null) {
                excelEntity.setParserStatus(true);
                excelEntity.setBindMsg("设置成功");
            }
            excelEntitys.add(excelEntity);
        }

        setExceptionMsg(eMsg.toString());
        return excelEntitys;
    }

    /**
     * 生成EXCEL
     *
     * @param excelEntitys
     * @throws IOException
     * @throws InvalidFormatException
     */
    public void writeXlsFile(List<T> excelEntitys, Class<T> excelClass) throws Exception {
        //转换数据格式
        List<Map<Integer, String>> dataList = transExcelDatas(excelEntitys, excelClass);
        //logger.info("插入excel数据:"+GsonUtils.toJson(dataList));
        //将数据放入excel
        insertDatasToExcel(dataList);
    }

    /**
     * 将反馈信息写入excel文件
     *
     * @param excelEntitys 返回的excel实体信息
     * @throws IOException
     * @throws InvalidFormatException
     */
    public void writeFeedbackXlsFile(List<T> excelEntitys) throws IOException, InvalidFormatException {
        // 增加新的表头信息（上传结果，和结果具体信息）
        createFeedbackHeader();
        // 增加具体信息
        insertFeedbackDatasToCell(excelEntitys);
    }

    /**
     * 导出excel
     *
     * @param out
     * @throws Exception
     */
    public void exportXlsFile(OutputStream out) throws Exception {
        try {
            out.flush();
            workbook.write(out);
        } catch (Exception e) {
            throw new Exception(" 生成导出Excel文件出错! ", e);
        }
    }

    /**
     * 使用实体类的注解信息生成excel模板
     *
     * @param excelClass
     */
    public void writeTemplateXlsFile(Class<T> excelClass) {
        Map<Integer, String> headerMap = new HashMap<Integer, String>();
        Map<Integer, String> templateMap = new HashMap<Integer, String>();
        try {
            Field[] ybFields = excelClass.getDeclaredFields();
            for (Field field : ybFields) {
                ExcelMsg msg = field.getAnnotation(ExcelMsg.class);
                field.setAccessible(true);
                headerMap.put(msg.index(), msg.header());
                templateMap.put(msg.index(), msg.template());
                sheet.setColumnWidth(msg.index(), msg.width() * 256);
            }
        } catch (IllegalArgumentException ex) {
            logger.error("excel转换实体出现异常", ex);
        }
        insertDatasToTemplateExcel(headerMap, templateMap);
    }

    private List<Map<Integer, String>> transExcelDatas(List<T> excelEntitys, Class<T> excelClass) throws Exception {
        // 增加表头信息
        Map<Integer, String> headerMap = new HashMap<Integer, String>();
        List<Map<Integer, String>> dataList = new ArrayList<Map<Integer, String>>();
        try {
            Field[] ybFields = excelClass.getDeclaredFields();
            for (Field field : ybFields) {
                ExcelMsg msg = field.getAnnotation(ExcelMsg.class);
                field.setAccessible(true);
                headerMap.put(msg.index(), msg.header());
                sheet.setColumnWidth(msg.index(), msg.width() * 256);
            }
            dataList.add(headerMap);//表头信息
            if (excelEntitys != null && excelEntitys.size() > 0) {
                for (int rowNum = 0; rowNum < excelEntitys.size(); rowNum++) {//取值
                    Map<Integer, String> dataMap = new HashMap<Integer, String>();
                    for (Field field : ybFields) {
                        ExcelMsg msg = field.getAnnotation(ExcelMsg.class);
                        field.setAccessible(true);
                        String type = field.getType().toString();//得到此属性的类型
                        String fieldValue = null;
                        if (type.endsWith("Date")) {//日期类型特殊处理下
                            fieldValue = sdf.format(field.get(excelEntitys.get(rowNum)));
                        } else {
                            fieldValue = String.valueOf(field.get(excelEntitys.get(rowNum)));
                        }
                        dataMap.put(msg.index(), fieldValue);
                    }
                    dataList.add(dataMap);//转换数据
                }
            }
        } catch (Exception ex) {
            logger.error("excel转换实体出现异常", ex);
            throw new Exception(ex);
        }
        return dataList;
    }

    /**
     * 模板excel数值填充
     */
    private void insertDatasToExcel(List<Map<Integer, String>> dataList) {
        if (dataList != null && dataList.size() > 0) {
            Map<Integer, String> headerMap = dataList.get(0);
            for (int rowNum = 0; rowNum < dataList.size(); rowNum++) {
                Row row = sheet.createRow(rowNum);
                for (int cellNum = 0; cellNum < headerMap.size(); cellNum++) {
                    Cell dataCell = row.createCell(cellNum);
                    String inValue = dataList.get(rowNum).get(cellNum);
                    inValue = (StringUtils.isBlank(inValue) || "null".equals(inValue)) ? "" : inValue;
                    dataCell.setCellValue(inValue);
                }
            }
        }
    }

    /**
     * 模板excel数值填充
     *
     * @param headerMap
     */
    private void insertDatasToTemplateExcel(Map<Integer, String> headerMap, Map<Integer, String> templateMap) {
        Row header = sheet.createRow(0);
        Row template = sheet.createRow(1);
        for (int cellNum = 0; cellNum < headerMap.size(); cellNum++) {
            Cell headerCell = header.createCell(cellNum);
            headerCell.setCellValue(headerMap.get(cellNum));
            Cell templateCell = template.createCell(cellNum);
            templateCell.setCellValue(templateMap.get(cellNum));
        }
    }

    /**
     * 反馈的excel新增header
     */
    private void createFeedbackHeader() {
//	        System.out.println("创建表头");
        Row header = sheet.getRow(0);
        int lastCellNum = header.getLastCellNum();
        Cell header6 = header.createCell(lastCellNum);
        header6.setCellValue("上传结果");
        Cell header7 = header.createCell(lastCellNum + 1);
        header7.setCellValue("上传结果信息");
    }

    /**
     * 反馈的excel新增的信息
     *
     * @param excelEntitys
     */
    private void insertFeedbackDatasToCell(List<T> excelEntitys) {
        for (T ybbind : excelEntitys) {
            Row row = sheet.getRow(ybbind.getIndex());
            int lastCellNum = row.getLastCellNum();
            Cell column6 = row.createCell(lastCellNum);
            column6.setCellValue(ybbind.getParserStatus() == null || ybbind.getParserStatus() == false ? "失败" : "成功");
            Cell column7 = row.createCell(lastCellNum + 1);
            column7.setCellValue(ybbind.getBindMsg());
        }
    }

    private String getValue(Cell cell) {
        DecimalFormat df = new DecimalFormat("0.##########");
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {

                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                }
                return df.format(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING: {
                String cellVal = cell.getStringCellValue().trim();
                if (cellVal.equals("--") || cellVal.equals("") || cellVal.equals("null")) {
                    return null;
                } else {
                    return cellVal;
                }
            }
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_BLANK:
                return null;
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_ERROR:
                return String.valueOf(cell.getErrorCellValue());
        }
        return "";
    }

    /**
     * 利用实体类型获取实体所有的属性
     *
     * @param clazz
     * @return
     */
    private Map<Integer, Field> getFieldMap(Class<T> clazz) {
        Map<Integer, Field> map = new HashMap<Integer, Field>();
        Field[] ybFields = clazz.getDeclaredFields();
        for (Field field : ybFields) {
            ExcelMsg msg = field.getAnnotation(ExcelMsg.class);
            field.setAccessible(true);
            map.put(msg.index(), field);
        }
        return map;
    }

    /**
     * 把excel对应的值设置到对应的实体属性值里面
     *
     * @param field
     * @param value
     * @param obj
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private void setFiledValue(Field field, String value, Object obj) throws IllegalArgumentException, IllegalAccessException {
        String type = field.getType().toString();//得到此属性的类型
        try {
            if (StringUtils.isBlank(value)) {
                ExcelMsg msg = field.getAnnotation(ExcelMsg.class);
                if (msg.require()) {
                    throw new IllegalArgumentException("参数不能为空");//参数不能为空
                }
                field.set(obj, null);
            } else if (type.endsWith("String")) {
                field.set(obj, value);
            } else if (type.endsWith("int") || type.endsWith("Integer")) {
                field.set(obj, Integer.parseInt(value));
            } else if (type.endsWith("long") || type.endsWith("Long")) {
                field.set(obj, Long.parseLong(value));
            } else if (type.endsWith("double") || type.endsWith("Double")) {
                field.set(obj, Double.parseDouble(value));
            } else if (type.endsWith("float") || type.endsWith("Float")) {
                field.set(obj, Float.parseFloat(value));
            } else if (type.endsWith("byte") || type.endsWith("Byte")) {
                field.set(obj, Byte.parseByte(value));
            } else {
                field.set(obj, value);
            }
        } catch (NumberFormatException e) {
            logger.error("参数异常，无法格式化！");
            //field.set(obj, null);
            throw new IllegalArgumentException(e);
        }
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Sheet getSheet() {
        return sheet;
    }

}

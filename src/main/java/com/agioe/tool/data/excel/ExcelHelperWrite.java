package com.agioe.tool.data.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * POI操作Excel的工具类
 * @author: zhye CopyBy liyufei
 * @since: 2018/9/6
 */
@Slf4j
public class ExcelHelperWrite {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 通过POI实现数据导入到Excel
     * <p>
     * fieldMap 说明：
     * 定义一个 fieldMap ，里面存放字段名key和对应字段中文value
     * 优点：Excel标题可以展示中文更好理解；可把data直接作为一个map去查询数据，通过反射动态获取数据，避免类似boolean类型是is开头导致取不到数据的问题
     * 缺点：如果页面查询的数据多出几个字段或少几个字段，这里需要改动代码
     *
     * @param list      数据集合
     * @param fieldMap  对应数据实体（字段名和中文名的映射集合）
     * @param sheetName 工作表名称
     * @param sheetSize 工作表大小
     * @param response
     * @param <T>
     * @throws Exception
     */
    public static <T> void listToExcel(String fileName, List<T> list, Map<String, String> fieldMap, String sheetName, int sheetSize, HttpServletResponse response, HttpServletRequest request) throws Exception {
        if (list == null || list.size() == 0) {
            throw new Exception("数据源中没有任何数据");
        }
        String agent = request.getHeader("User-Agent");
//        log.info("========开始导出数据到Excel========数据大小：" + list.size());
        // 设置默认文件名为当前时间：年月日时分秒
//        String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // 设置response头信息
        response.reset(); // 清除空白行
        response.setContentType("application/x-download");
//        response.setContentType("application/vnd.ms-excel"); // 改成输出excel文件
//        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xls");
        response.setHeader("Content-disposition", "attachment; filename=" + FileUtils.encodeDownloadFilename(fileName, agent) + ".xls");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Expose-Headers", "fileName,fileType");
        response.setHeader("fileName", FileUtils.encodeDownloadFilename(fileName, agent));
        response.setHeader("fileType", ".xls");
        response.setCharacterEncoding("utf-8");

        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        createSheet(workbook, list, fieldMap, sheetName, sheetSize);

        // 将生成的工作簿发送到浏览器+
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
            out.close();
//            log.info("========Excel输出到浏览器成功===========");
        } catch (IOException e) {
            e.printStackTrace();
            try {
                out.close();
            } catch (Exception ee) {
                throw new Exception("流关闭失败");
            }
        }
    }

    /**
     * 创建工作表（包括多工作表情况）
     *
     * @param workbook  工作簿
     * @param list      数据源
     * @param fieldMap  对应数据实体（字段名和中文名的映射集合）
     * @param sheetName 工作表名称
     * @param sheetSize 工作表大小（即行数）
     * @param <T>
     * @throws Exception
     */
    private static <T> void createSheet(HSSFWorkbook workbook, List<T> list, Map<String, String> fieldMap, String sheetName, int sheetSize) throws Exception {
        // 标题样式
        HSSFCellStyle styleTitle = createStyleTitle(workbook);
        // 内容样式
        HSSFCellStyle styleContent = createStyleContent(workbook);

        // 因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
        // 所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
        // 1.计算一共有多少个工作表
        double sheetNum = Math.ceil(list.size() / new Integer(sheetSize).doubleValue());
//        log.info("========共生成" + sheetNum + "sheet页========");
        // 2.创建相应的工作表，并向其中填充数据
        for (int i = 0; i < sheetNum; i++) {
            // 如果只有一个工作表的情况
            if (1 == sheetNum) {
                // 生成一个表格
                HSSFSheet sheet = workbook.createSheet(sheetName);
                fillSheet(sheet, list, fieldMap, 0, list.size() - 1, styleTitle, styleContent);
            } else {
                // 有多个工作表的情况
                HSSFSheet multipleSheet = workbook.createSheet(sheetName + (i + 1));
                // 获取开始索引和结束索引
                int firstIndex = i * sheetSize;
                int lastIndex = (i + 1) * sheetSize - 1 > list.size() - 1 ? list.size() - 1 : (i + 1) * sheetSize - 1;
                // 填充工作表
                fillSheet(multipleSheet, list, fieldMap, firstIndex, lastIndex, styleTitle, styleContent);
            }
        }
    }

    /**
     * 填充Excel数据
     *
     * @param sheet        工作簿
     * @param list         数据源
     * @param fieldMap     对应数据实体（字段名和中文名的映射集合）
     * @param firstIndex   行开始索引
     * @param lastIndex    行结束索引
     * @param styleTitle   头部样式
     * @param styleContent 内容样式
     * @param <T>
     * @throws Exception
     */
    private static <T> void fillSheet(HSSFSheet sheet, List<T> list, Map<String, String> fieldMap, int firstIndex, int lastIndex, HSSFCellStyle styleTitle, HSSFCellStyle styleContent) throws Exception {
        // 定义存放英文字段名和中文字段名的数组
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];
        // 填充数组
        int count = 0;
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            enFields[count] = entry.getKey();
            cnFields[count] = entry.getValue();
            count++;
        }
        // 填充表头
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < cnFields.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleTitle);
            HSSFRichTextString text = new HSSFRichTextString(cnFields[i]);
            cell.setCellValue(text);
        }
//        log.info("========填充表头成功===========");

        // 填充内容
        int rowNo = 1;
        for (int index = firstIndex; index <= lastIndex; index++) {
            // 获取单个对象
            HSSFRow rowCell = sheet.createRow(rowNo);
            T item = list.get(index);
            for (int i = 0; i < enFields.length; i++) {
                Object objValue = getFieldValueByNameSequence(enFields[i], item);
                Object fieldValue = objValue == null ? "" : objValue;

                // TODO 还有更多数据类型待处理；这里暂时只处理日期类型
                if (null != fieldValue && fieldValue.getClass() == Long.class && fieldValue.toString().length() == 13) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                        fieldValue = sdf.format(new Date(Long.valueOf((Long) fieldValue)));
                    } catch (Exception e) {
                        throw new Exception("日期类型转换错误，此数据不是时间戳。fieldValue: " + fieldValue + ", e: " + e.getMessage());
                    }
                }

                String stringFieldValue = fieldValue.toString();
                // 自动设置列宽
                if (null != cnFields[i] && (cnFields[i].length() > stringFieldValue.length())) {
                    sheet.setColumnWidth(i, cnFields[i].getBytes().length * 2 * 256);
                } else {
                    sheet.setColumnWidth(i, stringFieldValue.getBytes().length * 2 * 256);
                }
                HSSFCell cell = rowCell.createCell(i);
                cell.setCellStyle(styleContent);
                cell.setCellValue(stringFieldValue);
            }
            rowNo++;
        }
//        log.info("========填充内容成功===========");
    }

    /**
     * @param fieldNameSequence 带路径的属性名或简单属性名
     * @param o                 对象
     * @Description : 根据带路径或不带路径的属性名获取属性值 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     */
    public static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {
        Object value;
        if (o instanceof Map) {
            value = ((Map) o).get(fieldNameSequence);
        } else {
            // 将fieldNameSequence进行拆分
            String[] attributes = fieldNameSequence.split("\\.");
            if (attributes.length == 1) {
                value = getFieldValueByName(fieldNameSequence, o);
            } else {
                // 根据属性名获取属性对象
                Object fieldObj = getFieldValueByName(attributes[0], o);
                if (null == fieldObj) {
                    return null;
                }
                String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
                value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
            }
        }
        return value;
    }

    private static Object getFieldValueByName(String fieldName, Object o) throws Exception {
        Object value;
        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            // 取消Java的权限控制检查
            field.setAccessible(true);
            value = field.get(o);
        } else {
            throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
        return value;
    }

    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        // 拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();
        // 如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        // 否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }
        // 如果本类和父类都没有，则返回空
        return null;
    }

    private static HSSFCellStyle createStyleTitle(HSSFWorkbook workbook) {
        // 生成标题样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    private static HSSFCellStyle createStyleContent(HSSFWorkbook workbook) {
        // 生成内容样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
//        HSSFColor.YELLOW.index
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        HSSFFont font = workbook.createFont();
        font.setBold(false);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

}

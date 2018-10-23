package com.agioe.tool.data.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel 读取
 *
 * @author zhye  copy by cdliujian1
 * @since 2018/9/11
 */
public class ExcelHelperRead<T> {

    private final Map<String, Integer> idxMap = new HashMap<String, Integer>();

    /**
     * 从文件中解析excel
     *
     * @param filename
     * @param clazz         准备读出的对象类型class
     * @param excelResolver 根据准备读出的对象类型 自定义一个excelResolver
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public List<T> readExcel(String filename, Class<T> clazz, ExcelResolver<T> excelResolver) throws IOException, InvalidFormatException {
        InputStream is = new FileInputStream(filename);
        Workbook workbook;
        try {
            if (filename.endsWith("xls")) {
                //未测试 excel 2003
                workbook = new HSSFWorkbook(is);
            } else {
                workbook = WorkbookFactory.create(is);
            }
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                return null;
            }
            //赋值idxMap
            resolveHeader(sheet);
            setObjectField(excelResolver, "idxMap", idxMap);
            return genResultList(clazz, excelResolver, sheet);
        } finally {
            try {
                is.close();

            } catch (Exception e) {
                //no op
            }
        }
    }

    /**
     * 解析header头，获取每个header对应的位置
     *
     * @param sheet
     */
    private void resolveHeader(Sheet sheet) {
        Row header = sheet.getRow(sheet.getFirstRowNum());
        for (int i = 0; i < header.getLastCellNum(); i++) {
            Cell cell = header.getCell(i);
            String h = cell.getStringCellValue();
            idxMap.put(h.trim(), i);
        }
    }

    private List<T> genResultList(Class<T> clazz, ExcelResolver<T> excelTranslator, Sheet sheet) {
        List<T> results = new ArrayList<T>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            try {
                T obj = clazz.newInstance();
                setObjectField(excelTranslator, "row", row);
                if (excelTranslator.resolve(obj)) {
                    results.add(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    private void setObjectField(Object excelUtil, String filedName, Object fieldValue) {
        Field field = null;
        try {
            field = excelUtil.getClass().getSuperclass()
                    .getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(excelUtil, fieldValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}

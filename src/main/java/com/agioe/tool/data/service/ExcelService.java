package com.agioe.tool.data.service;

public interface ExcelService {
    /**
     * 导入excel
     */
    String[][] importExcel(String xlsPath) throws Exception;

    /**
     * 导出excel
     */
    void exportExcel(String filePath, String[][] strings, String title, Integer colunmNumber) throws Exception;
}

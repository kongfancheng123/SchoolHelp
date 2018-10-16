package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.service.ExcelService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public String[][] importExcel(String xlsPath) throws Exception {
        //导入已存在的Excel文件，获得只读的工作薄对象
        FileInputStream fis = new FileInputStream(xlsPath);
        Workbook wk = Workbook.getWorkbook(fis);
        //获取第一张Sheet表
        Sheet sheet = wk.getSheet(0);
        //获取总行数
        int rowNum = sheet.getRows();
        //总列数
        int columns = sheet.getColumns();
        String[][] strings = new String[rowNum - 2][columns - 1];
        //从数据行开始迭代每一行
        for (int i = 0; i < rowNum - 2; i++) {
            for (int j = 0; j < columns - 1; j++) {
                strings[i][j] = sheet.getCell(j + 1, i + 2).getContents();
            }
        }
        fis.close();
        wk.close();
        return strings;
//        for(int i=2;i<rowNum;i++){
//
//            ScoreInfo info=new ScoreInfo();
//            //getCell(column,row)，表示取得指定列指定行的单元格（Cell）
//            //getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格
//            //使用实体类封装单元格数据
//            info.setStuName(sheet.getCell(0, i).getContents());
//            info.setClassName(sheet.getCell(1, i).getContents());
//            //判断单元格的类型，单元格主要类型LABEL、NUMBER、DATE
//            // if(sheet.getCell(2,i).getType==CellType.NUMBER){
//
//            //转化为数值型单元格
//            NumberCell numCell=(NumberCell)sheet.getCell(2,i);
//            //NumberCell的getValue()方法取得单元格的数值型数据
//            info.setRscore(numCell.getValue());
//
//        }
//        if(sheet.getCell(3,i).getType==CellType.NUMBER){
//            NumberCell numCell=(NumberCell)sheet.getCell(3,i);
//            info.setRscore(numCell.getValue);
//        }
//
//        if(sheet.getCell(4,i).getType==CellType.DATE){
//            DateCell dateCell=(DateCell)sheet.getCell(4,i);
////DateCell的getDate()方法取得单元格的日期型数据
//            info.setDate(dateCell.getDate());
//        }
//    }

    }


    @Override
    public void exportExcel(String filePath, String[][] strings, String title, Integer colunmNumber) throws Exception {
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        WritableWorkbook wk = Workbook.createWorkbook(file);
        ///创建可写入的Excel工作表
        WritableSheet sheet = wk.createSheet("成绩表", 0);
        //把单元格（column, row）到单元格（column1, row1）进行合并。
        //mergeCells(column, row, column1, row1);
        sheet.mergeCells(0, 0, colunmNumber - 1, 0);//单元格合并方法
        //创建WritableFont 字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色
        WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.LIGHT_BLUE);
        //创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
        WritableCellFormat titleFormat = new WritableCellFormat();
        //设置字体格式
        titleFormat.setFont(titleFont);
        //设置文本水平居中对齐
        titleFormat.setAlignment(Alignment.CENTRE);
        //设置文本垂直居中对齐
        titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        //设置背景颜色
        titleFormat.setBackground(Colour.GRAY_25);
        //设置自动换行
        titleFormat.setWrap(true);
        //添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
        Label lab_00 = new Label(0, 0, title, titleFormat);
        //将定义好的Label对象添加到工作表上，这样工作表的第一列第一行的内容为‘学员考试成绩一览表’并应用了titleFormat定义的样式
        sheet.addCell(lab_00);
        WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
        cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false));
        cloumnTitleFormat.setAlignment(Alignment.CENTRE);
        int r = 1;
        for (String[] strings1 : strings) {
            int c = 0;
            for (String string : strings1) {
                Label lab = new Label(c, r, string, cloumnTitleFormat);
                sheet.addCell(lab);
                c++;
            }
            r++;
        }
        wk.write();
        //操作完成时，关闭对象，释放占用的内存空间
        wk.close();
    }
}

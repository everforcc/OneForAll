/**
 * @Description
 * @Author everforcc
 * @Date 2022-09-27 11:13
 * Copyright
 */

package cn.cc.dawn.utils.excle;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * EXCLE 追加操作
 */
public class ExcelAppend {
    public static void main(String[] args) throws Exception {
        //获取xlsx
        FileInputStream fs = new FileInputStream("E:\\test-3.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        //获取到工作表，因为一个excel可能有多个工作表
        //0获取的是sheet1
        XSSFSheet sheet = wb.getSheetAt(0);
        //向xlsx中写数据
        FileOutputStream out = new FileOutputStream("E:\\test-3.xlsx");
        //从第二行开始，除去第一行表头，excel的第一行第一列索引都是从0开始的

        //
        XSSFRow row = sheet.createRow(5);
        XSSFCell cell4 = row.createCell(6);
        cell4.setCellValue("value");
        out.flush();
        wb.write(out);
        wb.close();
        System.out.println("end");
    }
}
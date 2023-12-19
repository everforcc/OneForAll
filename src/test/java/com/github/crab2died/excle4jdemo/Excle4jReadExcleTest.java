/**
 * @Description
 * @Author everforcc
 * @Date 2022-09-21 11:53
 * Copyright
 */

package com.github.crab2died.excle4jdemo;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.excle4jdemo.dto.ExcelDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excle4jReadExcleTest {

    @Test
    public void t1() throws Exception {
        String data = "   ";
        if (StringUtils.isEmpty(data)) {
            System.out.println("data 是 空");
        }
        if (data.contains(" ")) {
            System.out.println("data 有 空格");
        }
    }

    @Test
    public void ttt() throws Exception {

        String filePath = "D:/java/学校-年级-班级-A空.xlsx";
        ExcelUtils excelUtils = ExcelUtils.getInstance();
        List<List<String>> lists = excelUtils.readExcel2List(filePath, 0, Integer.MAX_VALUE, 0);
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.println("第" + i + "行" + j + "列,数据: " + lists.get(i).get(j));
            }
        }
    }

    /**
     * 从 excel 读取到对象
     */
    @Test
    public void testRead2Dto() throws Exception {

        String filePath = "D:/D4.xlsx";

        // 写入
        List<ExcelDto> excelDtoList = new ArrayList<>();
        excelDtoList.add(new ExcelDto());
        excelDtoList.add(new ExcelDto("1", "1"));
        excelDtoList.add(new ExcelDto("2", "1"));
        excelDtoList.add(new ExcelDto("3", "1"));
        ExcelUtils.getInstance().exportObjects2Excel(excelDtoList, ExcelDto.class, true, null, true, filePath);

        // 读取
//        ExcelUtils excelUtils = ExcelUtils.getInstance();
//        List<ExcelDto> list = excelUtils.readExcel2Objects(filePath, ExcelDto.class, 0, Integer.MAX_VALUE, 0);
//        System.out.println(list.size());
    }

    public static void main(String[] args) {
        TestExportThread t1 = new TestExportThread("a7", 1, 10);
        TestExportThread t2 = new TestExportThread("a7", 20, 30);
        TestExportThread t3 = new TestExportThread("a7", 40, 50);

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}

/**
 * 这种方式不行
 * 应该把  FileOutputStream 声明也拿出去
 * 然后线程执行完毕再关掉
 */
class TestExportThread implements Runnable {

    String name;
    int iMin;
    int iMax;

    public TestExportThread(String name, int iMin, int iMax) {
        this.name = name;
        this.iMin = iMin;
        this.iMax = iMax;
    }

    @Override
    public void run() {
        try {
            // 创建一份
            XSSFWorkbook excel = new XSSFWorkbook();
            // 创建第一个sheet
            Sheet sheet = excel.createSheet("color");
            // 生成单元格样式
            // CellStyle style = excel.createCellStyle();

            int tempX = -1;
            Row row = null;

            // 这块儿调整为为多线程， 太慢了 图片分辨率最大的边手动调整为100，分十个线程，一起跑
            for (int i = iMin; i < iMax; i++) {
                // 创建第x行
                synchronized ("sss") {
                    row = getRow(sheet, i);
                    for (int j = 0; j < 10; j++) {
                        // 创建第x个单元格
                        Cell cell = row.createCell(j);
                        // 设置单元格的值
                        cell.setCellValue("1");
                        System.out.println("正在写入坐标: " + i + "-" + j);
                    }
                }
            }

            // 写入磁盘
            FileOutputStream out = new FileOutputStream("D:/java/" + name + ".xlsx", true);
            excel.write(out);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Row getRow(Sheet sheet, int i) {
        return sheet.createRow(i);
    }
}

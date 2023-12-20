package com.github.crab2died;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Description 分批导出excel工具类
 * @Author everforcc
 * @Date 2023-12-20 20:20
 * Copyright
 */
public class ExcelWorkbook implements Closeable {

    // 输出流
    private OutputStream outputStream;
    // excel poi 下的类
    private Workbook workbook;
    // 数据开始行号
    private int dataRowNum;
    // sheet也行号
    private int[][] dataSheetRowNum;
    // excel导出工具
    private ExcelUtils excelUtils;

    ExcelWorkbook(OutputStream outputStream, Workbook workbook, ExcelUtils excelUtils) {
        this.outputStream = outputStream;
        this.workbook = workbook;
        this.dataRowNum = 0;
        this.excelUtils = excelUtils;
    }

    ExcelWorkbook(OutputStream outputStream, Workbook workbook, ExcelUtils excelUtils, int[][] dataSheetRowNum) {
        this.outputStream = outputStream;
        this.workbook = workbook;
        this.dataRowNum = 0;
        this.excelUtils = excelUtils;
        this.dataSheetRowNum = dataSheetRowNum;
    }

    public void append(List<?> data) {
        this.excelUtils.append(this.workbook, data, this.dataRowNum);
        this.dataRowNum += data.size();
    }

    public void append(List<List<String>> data, int sheetIndex) {
        int sheetRowNum = dataSheetRowNum[sheetIndex][0];
        this.excelUtils.append(this.workbook, data, sheetRowNum, sheetIndex);
        sheetRowNum += data.size();
        this.dataRowNum += data.size();
        this.dataSheetRowNum[sheetIndex][0] = sheetRowNum;
    }

    /**
     * 自动关闭，写入文件和释放
     *
     * @throws IOException 异常
     */
    @Override
    public void close() throws IOException {
        // 写入文件
        if (this.dataRowNum > 0 && this.outputStream != null && this.workbook != null) {
            this.workbook.write(this.outputStream);
        }
        // 关闭
        if (this.outputStream != null) {
            this.outputStream.close();
        }
        if (this.workbook != null) {
            this.workbook.close();
        }
    }
}

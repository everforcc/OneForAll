package com.github.crab2died;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description
 * @Author everforcc
 * @Date 2023-12-20 21:10
 * Copyright
 */
@Slf4j
public class ExcelUtilsTest {

    private String path = "D:\\Cache\\BaiduNetdiskWorkspace\\java\\filesystem\\project\\OneForAll\\Excel4j\\append\\";

    @Test
    public void testExportExcel1() throws IOException {
        String file = path + "2156.xlsx";
        List<List<String>> data = new ArrayList<>(200000);
        boolean flag = true;
        try (ExcelWorkbook excelWorkbook = ExcelUtils.getInstance().initExcelWithAppend(file)) {
            for (int i = 0; i < 10; i++) {
                List<String> line = new ArrayList<>();
                line.add("abcde" + String.format("%06d", i));
                line.add(String.valueOf(flag));
                flag = !flag;
                line.add("啊啊啊啊" + i);
                line.add("哈哈h哈" + i);
                data.add(line);
            }
            log.info("正在组装数据，准备导出");
            excelWorkbook.append(data);
            data.clear();

            for (int i = 0; i < 10; i++) {
                List<String> line = new ArrayList<>();
                line.add("abcde" + String.format("%06d", i));
                line.add(String.valueOf(flag));
                flag = !flag;
                line.add("啊啊啊啊" + i);
                line.add("哈哈h哈" + i);
                data.add(line);
            }
            log.info("正在组装数据，准备导出");
            excelWorkbook.append(data);
            data.clear();
        }
    }

    @Test
    public void testExportExcel2Sheet() throws IOException {
        String file = path + "2210.xlsx";
        List<List<String>> data = new ArrayList<>(200000);
        Map<Integer, String> sheetNameMap = new TreeMap<>();
        sheetNameMap.put(0, "sheet-0");
        sheetNameMap.put(1, "sheet-1");
        boolean flag = true;
        try (ExcelWorkbook excelWorkbook = ExcelUtils.getInstance().initExcelWithAppend(file, sheetNameMap)) {
            for (int i = 0; i < 10; i++) {
                List<String> line = new ArrayList<>();
                line.add(String.valueOf(flag));
                flag = !flag;
                line.add("啊啊啊啊" + i);
                data.add(line);
            }
            log.info("正在组装数据，准备导出");
            excelWorkbook.append(data, 0);
            data.clear();

            for (int i = 0; i < 10; i++) {
                List<String> line = new ArrayList<>();
                line.add(String.valueOf(flag));
                flag = !flag;
                line.add("啊啊啊啊" + i);
                data.add(line);
            }
            log.info("正在组装数据，准备导出");
            excelWorkbook.append(data, 1);
            data.clear();
        }
    }

    @Test
    public void testExportExcel3() throws IOException {
        String file = path + "2138.xlsx";
        List<List<String>> data = new ArrayList<>(200000);
        boolean flag = true;
        try (ExcelWorkbook excelWorkbook = ExcelUtils.getInstance().initExcelWithAppend(file)) {
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 5; i++) {
                    List<String> line = new ArrayList<>();
                    line.add("abcde" + String.format("%06d", i));
                    line.add(String.valueOf(flag));
                    flag = !flag;
                    line.add("啊啊啊啊" + i);
                    line.add("哈哈h哈" + i);
                    data.add(line);
                }
                log.info("正在组装数据，准备导出： {}", j);
                excelWorkbook.append(data);
                data.clear();
            }
        }
    }

}

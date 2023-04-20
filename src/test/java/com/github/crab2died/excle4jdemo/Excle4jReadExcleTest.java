/**
 * @Description
 * @Author everforcc
 * @Date 2022-09-21 11:53
 * Copyright
 */

package com.github.crab2died.excle4jdemo;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.excle4jdemo.dto.ExcelDto;
import org.junit.Test;

import java.util.List;

public class Excle4jReadExcleTest {

    /**
     * 从 excel 读取到对象
     *
     */
    @Test
    public void testRead2Dto() throws Exception {

        String filePath = "E:/D4.xlsx";

        // 写入
//        List<ExcelDto> excelDtoList = new ArrayList<>();
//        excelDtoList.add(new ExcelDto());
//        excelDtoList.add(new ExcelDto("1", "1"));
//        excelDtoList.add(new ExcelDto("2", "1"));
//        excelDtoList.add(new ExcelDto("3", "1"));
//        ExcelUtils.getInstance().exportObjects2Excel(excelDtoList, ExcelDto.class, true, null, true, filePath);

        // 读取
        ExcelUtils excelUtils = ExcelUtils.getInstance();
        List<ExcelDto> list = excelUtils.readExcel2Objects(filePath, ExcelDto.class, 0, Integer.MAX_VALUE, 0);
        System.out.println(list.size());
    }

}

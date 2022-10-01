/**
 * @Description
 * @Author everforcc
 * @Date 2022-09-21 10:15
 * Copyright
 */

package cn.cc.dawn.demo.excle4j;

import cn.cc.dawn.common.bddisk.filemsg.list.dto.FileListResDto;
import cn.cc.dawn.config.init.json.JSONInit;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.crab2died.ExcelUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Excle4jTest {

    @Test
    public void cal() throws Exception {
        int a = 3;
        int b = 3;
        System.out.println(a / b);
        System.out.println(a % b);
        if (a % b > 0) {
            System.out.println("总页数_1: " + (a / b + 1));
        } else {
            System.out.println("总页数_2: " + (a / b));
        }
    }

    @Test
    public void testObject2Excel_1() throws Exception {

        String tempPath = "/normal_template.xlsx";
        List<FileListResDto> list = new ArrayList<>();

        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");
        // 基于模板导出Excel
        ExcelUtils.getInstance().exportObjects2Excel(tempPath, 0, list, data, FileListResDto.class, false, "D:/A.xlsx");
        // 不基于模板导出Excel
        ExcelUtils.getInstance().exportObjects2Excel(list, FileListResDto.class, true, null, true, "D:/B.xlsx");

    }

    /**
     * 3. 基于模板List<Oject>导出
     */
    @Test
    public void testObject2Excel() throws Exception {

        //String tempPath = "/normal_template.xlsx";
        //List<FileListResDto> list = new ArrayList<>();

        String json = JSONInit.getBD_FileList();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("list");

        List<FileListResDto> list = jsonArray.toJavaList(FileListResDto.class);

        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");
        // 基于模板导出Excel
        //ExcelUtils.getInstance().exportObjects2Excel(tempPath, 0, list, data, FileListResDto.class, false, "D:/A.xlsx");
        // 不基于模板导出Excel
        FileOutputStream fileOutputStream = new FileOutputStream("./B1.xlsx", true);

        ExcelUtils excelUtils = ExcelUtils.getInstance();

        excelUtils.exportObjects2Excel(list, FileListResDto.class, true, "sheetName-cc", true, fileOutputStream);
        //excelUtils.exportObjects2Excel(list, FileListResDto.class, false, "sheetName-cc", true, fileOutputStream);
    }

    @Test
    public void testJSON2Excel() throws Exception {

        List<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key_1", "value");
        jsonObject.put("key_2", "value");
        jsonObject.put("key_3", "value");
        jsonObject.put("key_4", "value");
        list.add(jsonObject);
        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");
        // 基于模板导出Excel
        //ExcelUtils.getInstance().exportObjects2Excel(tempPath, 0, list, data, FileListResDto.class, false, "D:/A.xlsx");
        // 不基于模板导出Excel
        //FileOutputStream fileOutputStream = new FileOutputStream("./B.xlsx", true);

        ExcelUtils excelUtils = ExcelUtils.getInstance();

        excelUtils.exportObjects2Excel(list, JSONObject.class, true, "sheetName-cc", true, "./B2.xlsx");
        //excelUtils.exportObjects2Excel(list, JSONObject.class, false, "sheetName-cc", true, fileOutputStream);
    }

}

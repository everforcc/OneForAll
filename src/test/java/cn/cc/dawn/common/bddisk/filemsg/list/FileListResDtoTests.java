/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-27 22:46
 * Copyright
 */

package cn.cc.dawn.common.bddisk.filemsg.list;

import cn.cc.dawn.common.bddisk.filemsg.list.dto.FileListResDto;
import cn.cc.dawn.config.init.json.JSONInit;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

/**
 * 测试百度云文件列表 返回信息导出为excle
 */
public class FileListResDtoTests {

    /**
     * 测试网盘接口返回的时间戳
     */
    @Test
    public void timestamp() {
        Date date = new Date(1656206866000L);
        System.out.println(date.toString());
    }


    @Test
    public void export() {

        String json = JSONInit.getBD_FileList();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("list");

        List<FileListResDto> fileListResDtoList = jsonArray.toJavaList(FileListResDto.class);
        //List<FileListResDto> fileListResDtoList = JSONObject.parseArray(jsonArray, FileListResDto.class);
        System.out.println(fileListResDtoList.size());

        String path = "./FileListResDto.xlsx";
        // 导出到excle
        try {
            //ExcelUtils.getInstance().exportObjects2Excel(fileListResDtoList, FileListResDto.class, true, "sheet0", true, path);
            // (List<?> data, Class clazz, boolean isWriteHeader, Appendable appendable)
            ExcelUtils.getInstance().exportObjects2CSV(fileListResDtoList, FileListResDto.class, false, path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

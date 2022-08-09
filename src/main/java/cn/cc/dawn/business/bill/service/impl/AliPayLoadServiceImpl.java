/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-09 14:54
 * Copyright
 */

package cn.cc.dawn.business.bill.service.impl;

import cn.cc.dawn.business.bill.dto.AliPayDto;
import cn.cc.dawn.business.bill.service.IAliPayLoadService;
import com.github.crab2died.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AliPayLoadServiceImpl implements IAliPayLoadService {
    @Override
    public void loadExcel(String path) {
        // 从弟几行开始,第一行是0
        int offsetLine = 4;
        int sheetIndex = 0;


        try {
            List<AliPayDto> students = ExcelUtils.getInstance().readExcel2Objects(path, AliPayDto.class, offsetLine, sheetIndex);
            System.out.println("读取账单信息");
            for (AliPayDto aliPayDto : students) {
                log.info("对象值: {}", aliPayDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

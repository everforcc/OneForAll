/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-27 22:05
 * Copyright
 */

package com.github.crab2died.excle4jdemo.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import com.github.crab2died.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDto extends CommonFiledDto {

    /**
     * 文件在云端的唯一标识ID
     */
    @ExcelField(title = "标识ID", order = 1)
    private String fs_id;

    @ExcelField(title = "名字", order = 2)
    private String name;
}

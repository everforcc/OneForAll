/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-25 09:41
 * Copyright
 */

package cn.cc.dawn.demo.param.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamDto {

    private String name;
    private String description;

    @Override
    public String toString() {
        return "ParamDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

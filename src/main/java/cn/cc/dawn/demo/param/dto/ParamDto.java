/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-25 09:41
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

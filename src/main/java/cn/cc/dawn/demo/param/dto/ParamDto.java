/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-25 09:41
 */

package cn.cc.dawn.demo.param.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamDto {

    private FieldEnum statusEnum;
    private String name;
    private String description;

    public FieldEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(FieldEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

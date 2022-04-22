package cn.cc.dawn.demo.data.redis.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TOrderDto extends CommonFiledDto {

    private String orderno;
    private String userid;
    private int productid;

}

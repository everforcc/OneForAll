package cn.cc.dawn.demo.data.redis.dto;

import cn.cc.dawn.common.dto.CommonFiledDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TStockDto extends CommonFiledDto {

    private String stocknum;

}

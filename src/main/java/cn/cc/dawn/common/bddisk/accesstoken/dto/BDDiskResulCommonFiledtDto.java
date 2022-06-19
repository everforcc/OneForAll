/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-20 09:29
 */

package cn.cc.dawn.common.bddisk.accesstoken.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 百度返回公共的异常字段
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BDDiskResulCommonFiledtDto extends CommonFiledDto {

    // {"error":"invalid_grant","error_description":"invalid code , expired or revoked"};
    private String error;
    private String error_description;

}

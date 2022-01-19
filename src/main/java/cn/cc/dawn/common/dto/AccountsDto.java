package cn.cc.dawn.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto extends CommonFiledDto{

    private String webroot;
    private String webtype;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String cookie;
    private String safequestion;
    private String encrypt;

}

package cn.cc.dawn.open.auth.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserLoginVO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    private String checkkey;

    private String checkcode;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}

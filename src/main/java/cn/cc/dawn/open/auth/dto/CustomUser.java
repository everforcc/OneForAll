package cn.cc.dawn.open.auth.dto;

import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.open.auth.cache.CustomerUserCache;
import cn.cc.dawn.utils.commons.codec.JAESUtil;
import cn.cc.dawn.utils.commons.codec.JUUIDUtils;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.data.redis.impl.RedisBoundValueOperationsUtils;
import cn.cc.dawn.utils.exception.AppCode;
import com.alibaba.fastjson.JSONObject;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomUser extends CommonFiledDto implements UserDetails {

    private int id;

//    private String reg = "";
//
//    @Pattern(regexp = reg)
    private String username;



    @Pattern(regexp = "^(?![^a-z]+$)(?![^A-Z]+$)(?!\\D+$)(?![^!@#$%^&*_\\.]+$)(?=[a-zA-Z0-9!@#$%^&*_\\.]+$).{8,}$", message = "密码至少有一个小写，大写，数字，字符 !@#$%^&*_. ，最短8位")
    private String password;

    /**
     * 随后做单独把 role做个表，然后用id对应， Long[] roles
     * 然后使用的时候再查出来
     *
     */
    //@Convert(converter = StringListTranConvert.class)
    private String roles;

    public String token(){
        // 生成token
        /**
         * 设置个随机数，保证每次生成不同
         */
        setUuid(JUUIDUtils.uuid32());
        String json = JSONObject.toJSONString(this);
        return JAESUtil.aes_encrypt(json);
    }

    @Autowired
    RedisBoundValueOperationsUtils redis;
    //RedisBoundValueOperationsUtils redis = new RedisBoundValueOperationsUtils();

    /**
     * 正式使用改为加密数据，学习时方便测试
     * @param json
     * @return
     */
    public CustomUser tokenToUser(String json){

        log.info("解密前json: " + json);
        json = JAESUtil.aes_decrypt(json);
        log.info("解密后json: " + json);
        CustomUser customUser = JSONObject.parseObject(json,CustomUser.class);
        // 拿到user取数据库查询
        // 在redis 检查是否存在，如果不存在
        AppCode.A00102.assertHasTrue(RObjectsUtils.nonNull(customUser));
        AppCode.A00102.assertHasTrue(redis.hasKeyCacheAble(CustomerUserCache.CUSTOMERUSER_TOKEN,customUser.getUsername()));

        return customUser;
    }

    public CustomUser(int id) {
        this.id = id;
    }

    /**
     * 构造返回用户的权限
     * 由于springsecurity，必须以 ROLE_ 开头
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        /*roles = new ArrayList<>();
        roles.add(UserRole.ROLE_ROOT);
        roles.add(UserRole.ROLE_ADMIN);
        roles.add(UserRole.ROLE_TEST);
        roles.add(UserRole.ROLE_GUEST);*/

        if(RStringUtils.isBlank(roles)){
            return Collections.emptyList();
        }

        List<String> roleList = Arrays.asList(roles.split(","));
        return roleList
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        //return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


//    @Override
//    public boolean equals(Object obj) {
//        CustomUser customUser = (CustomUser)obj;
//        if(this.username.equals(customUser.getUsername())&&this.getUuid().equals()){
//
//        }
//        return super.equals(obj);
//    }

    public static void main(String[] args) {
//        CustomUser customUser = new CustomUser();
//        List<String> stringList = new ArrayList<>();
//        stringList.add(UserRole.ROLE_ROOT);
//        stringList.add(UserRole.ROLE_USER);
//        customUser.setRoles(stringList);
//        StringListTranConvert stringListTranConvert = new StringListTranConvert();
//        String result = stringListTranConvert.convertToDatabaseColumn(stringList);
//        System.out.println(result);
//        List<String> stringListResult = stringListTranConvert.convertToEntityAttribute(result);
//
//        stringListResult.forEach(System.out::println);
    }

}


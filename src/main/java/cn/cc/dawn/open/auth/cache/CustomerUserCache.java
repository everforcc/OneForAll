package cn.cc.dawn.open.auth.cache;

import cn.cc.dawn.config.cache.ICache;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;

@Getter
@Setter
@ToString
public class CustomerUserCache implements ICustomerUser {

    /**
     *  过期时间
     */
    private Duration expired;

}

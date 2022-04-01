package cn.cc.dawn.demo.init.yml.config;


import cn.cc.dawn.demo.init.yml.dto.ExpressFalseBean;
import cn.cc.dawn.demo.init.yml.dto.ExpressTrueBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ExpressAutoConfig {
    /**
     * 当存在配置，且配置为true时才创建这个bean
     * @return
     */
    @Bean
    //@org.springframework.boot.autoconfigure.condition.ConditionalOnExpression("'true'.equals('${spring.app.aes.enable}')")
    @ConditionalOnExpression("'true'.equals('${app.conditional.express}')")
    public ExpressTrueBean expressTrueBean() {
        log.debug("expressTrueBean()");
        return new ExpressTrueBean("express true");
    }

    /**
     * 配置不存在，或配置的值不是true时，才创建bean
     * @return
     */
    @Bean
    @ConditionalOnExpression("!'true'.equals('${app.conditional.express}')")
    public ExpressFalseBean expressFalseBean() {
        log.debug("expressFalseBean()");
        return new ExpressFalseBean("express != true");
    }
}
package cn.cc.dawn.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Collections;

/**
 * <pre>
 * Spring Core，参考：
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html
 *
 * Spring MVC 配置，参考 ：
 * https://linesh.gitbooks.io/spring-mvc-documentation-linesh-translation/content/
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html
 *
 * Spring Thymeleaf 配置，参考 ：
 * https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html#spring-mvc-configuration
 *
 * Spring validator 配置，参考
 * https://beanvalidation.org/2.0/spec/#introduction
 * https://www.baeldung.com/javax-validation-method-constraints
 * http://rubygems.torquebox.org/proposals/BVAL-241/
 * https://juejin.im/entry/5b5a94d2f265da0f7c4fd2b2
 * https://www.cnblogs.com/mr-yang-localhost/p/7812038.html
 */
@RestControllerAdvice
@Slf4j
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class AppConfig {

    @Configuration
    public static class APPWebMvcConfigurer implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("*")
                    .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                    .allowCredentials(false).maxAge(3600);
        }
    }

    /**
     * 启用 FastJson
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask(); // 解决循环引用问题
        final FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converter.getFastJsonConfig().setFeatures(Feature.OrderedField);
        converter.setSupportedMediaTypes(Collections.singletonList(
                MediaType.APPLICATION_JSON
        ));
        return converter;
    }

    /*
     * 快速失败返回模式，只要有一个异常就返回
     * https://www.cnblogs.com/mr-yang-localhost/p/7812038.html
     @Bean
     public Validator validator() {
     return Validation.byProvider(HibernateValidator.class)
     .configure()
     .failFast(true)
     .addProperty( "hibernate.validator.fail_fast", "true" )
     .buildValidatorFactory()
     .getValidator();
     }
     */

//    /**
//     * 注册过滤器
//     * <pre>
//     * 添加自定义过滤器：设置链路追踪，在日志打印和响应头中追加该标记
//     * 警告：多线程时需要特殊处理
//     * final Map<String, String> mdc = MDC.getCopyOfContextMap(); // 复制主线程 ThreadLocal
//     * new Thread(() -> {
//     *     try {
//     *         MDC.setContextMap(mdc); // 设置子线程 ThreadLocal
//     *         // 子线程代码
//     *     } finally {
//     *         MDC.clear(); // 清除子线程 ThreadLocal
//     *     }
//     * }).start();
//     *
//     * @return FilterRegistrationBean
//     */
//    @Order(0)
//    @Bean
//    public FilterRegistrationBean<Filter> requestIdFilter() {
//        final FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
//        bean.setOrder(0);
//        bean.setFilter(new RequestIdFilter());
//        bean.addUrlPatterns("/*");
//        return bean;
//    }

//    @ExceptionHandler
//    public Result<Void> exception(final JSONException e) {
//        return new Result<Void>(Code.A00008).setException(String.format("500：JSON 序列化或反序列化异常：%s", e.getMessage()));
//    }

}

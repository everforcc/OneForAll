package cn.cc.dawn.config.bean;

import cn.cc.dawn.utils.constant.CharsetsConstant;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.exception.Code;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
//@RestControllerAdvice(annotations = RestController.class)
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
@Configuration
//@Configuration(proxyBeanMethods = false)
public class AppBean {

    /**
     * 交给 security 管理
     */
//    @Configuration
//    public static class APPWebMvcConfigurer implements WebMvcConfigurer {
//
//        // 支持跨域
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**").allowedOrigins("*")
//                    .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
//                    .allowCredentials(false).maxAge(3600);
//        }
//    }

    /**
     * 注册bean
     */

    /**
     * 多线程管理
     *
     * @return ExecutorService
     */
    @Bean(name = "multiThread",destroyMethod = "shutdownNow")
    public ExecutorService multiThread() {
        return new ThreadPoolExecutor(
                16,
                16 * 4,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), // 线程池队列，超过 1024 个任务将会抛出异常
//                    new LinkedBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("multi-thread-%d").build(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * 单线程管理
     *
     * @return ExecutorService
     */
    @Bean(name = "singleThread",destroyMethod = "shutdownNow")
    public ExecutorService singleThread() {
        return new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), // 线程池队列，超过 1024 个任务将会抛出异常
                new ThreadFactoryBuilder().setNameFormat("single-thread-%d").build(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * 启用 FastJson
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask(); // 解决循环引用问题
        final FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        // 字符集
        converter.setDefaultCharset(CharsetsConstant.UTF_8);
        // 字段排序
        converter.getFastJsonConfig().setFeatures(Feature.OrderedField);
        //
        converter.setSupportedMediaTypes(Collections.singletonList(
                MediaType.APPLICATION_JSON
        ));
        return converter;
    }

    /**
     * 测试 @Configuration 的参数
     * @return
     */
    @Bean
    public String configProxyBeanMethods_1(){
        log.info("测试@Configuration的初始化_1");
        configProxyBeanMethods_2();
        return "string-1";
    }
    @Bean
    public String configProxyBeanMethods_2(){
        log.info("测试@Configuration的初始化_2");
        return "string-2";
    }

    @ExceptionHandler(Exception.class)
    public ResultE<Void> exception(final Exception e) {
        log.info("处理全局异常---");

        log.error("{}", Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Authentication::getPrincipal).orElse(null));
        log.error(e.getMessage(), e);
        if (e instanceof HttpMediaTypeNotSupportedException) {
            return new ResultE<Void>(Code.A00001).setException(String.format("500：content-type 不支持：%s", e.getMessage()));
        } else if (e instanceof MaxUploadSizeExceededException) {
            return new ResultE<Void>(Code.A00001).setException(String.format("500：文件大小超出限制：%s", e.getMessage()));
        } else if (e instanceof IllegalArgumentException) {
            return new ResultE<Void>(Code.A00006).setException("400：请求不存在");
        } else if (e instanceof MissingServletRequestParameterException) {
            return new ResultE<Void>(Code.A00007).setException(String.format("500：请求 url 映射的方法缺少必要的参数：%s", e.getMessage()));
        } else if (e instanceof HttpMessageNotReadableException) {
            return new ResultE<Void>(Code.A00007).setException(String.format("500：请求缺少必要的参数:%s", e.getMessage()));
        } else if (e instanceof NoHandlerFoundException) {
            return new ResultE<Void>(Code.A00007).setException("404：请求url不存在");
        } else if (e instanceof JSONException) {
            return new ResultE<Void>(Code.A00008).setException(String.format("500：JSON 序列化或反序列化异常：%s", e.getMessage()));
        } else if (e instanceof BindException || e instanceof MethodArgumentTypeMismatchException) {
            return new ResultE<Void>(Code.A00008).setException(String.format("500：参数转换异常：%s", e.getMessage()));
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return new ResultE<Void>(Code.A00009).setException(String.format("405：请求方式不被该接口支持，或者请求url错误未映射到正确的方法：%s", e.getMessage()));
        } else if (e instanceof AccessDeniedException) {
            return new ResultE<Void>(Code.A00011).setException("403：无操作权限");
        }
        ResultE resultE = new ResultE<Void>(AppCode.A00104).setException(e.getCause().getMessage());
        return resultE;
    }

}

package cn.cc.dawn.config.security;

import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.open.auth.util.CustomUserBuilder;
import cn.cc.dawn.utils.commons.codec.JUUIDUtils;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import cn.cc.dawn.utils.constant.SystemUrlConstant;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.enums.ContentTypeEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
// @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
// 使用 @EnableGlobalMethodSecurity 注解来启用基于注解的安全性
// @EnableGlobalMethodSecurity(securedEnabled = true) // 启用注解：@Secured；[@Secured("ROLE_USER"), @Secured("IS_AUTHENTICATED_ANONYMOUSLY")]
// @EnableGlobalMethodSecurity(prePostEnabled = true) // 启用注解：@PreAuthorize；[@PreAuthorize("hasAuthority('ROLE_USER')"), @PreAuthorize("isAnonymous()")]
@EnableWebSecurity
public class SPSecurityConfig{

    /**
     * 开放无需登录的接口
     */
    public static class OpenSecurityConfig{

    }

    @Order(1)
    @Configuration(proxyBeanMethods = false)
    public static class LoginSecurityConfig extends WebSecurityConfigurerAdapter{

        /**
         *
         * @param http
         * @throws Exception
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //super.configure(http);
            /**
             * 链路追踪
             */
            MDC.put(HttpHeadersConstant.MDC_header, JUUIDUtils.uuid32());
            /**
             * 处理登录401，403，200，error等五种情况
             */
            final AuthHandler authHandler = new AuthHandler();




            /**
             * http登录. start
             */
            http
                    /**
                     * 设置允许跨域
                     */
                    .csrf(
                        httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()
                            //httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringAntMatchers();
                    )
                    /**
                     * http在系统中的链路追踪
                     */
                    .headers(
                            headers -> headers
                                    .addHeaderWriter((req, res) -> res.addHeader(HttpHeadersConstant.MDC_header, MDC.get(HttpHeadersConstant.MDC_header)))
                                    .xssProtection()
                            // .and().cacheControl() // 启用缓存
                    )
                    /**
                     * 用户访问未经授权的rest API，返回错误码401（未经授权）
                     */
                    .exceptionHandling().authenticationEntryPoint(authHandler).accessDeniedHandler(authHandler)
                    /**
                     * 指定可以直接访问的路径
                     * 可以追加多个,
                     * 除了指定的方法外，其他的方法都需要认证
                     */
                    .and().authorizeRequests()
                    /**
                     * 这块随后使用处理到配置文件
                     */
                    .antMatchers("/open/**", SystemUrlConstant.package_open).permitAll()
                    .antMatchers("/local/**").permitAll()
                    .antMatchers("/user/**").permitAll()
                    .antMatchers("/error/**").permitAll()
                    //.antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    /**
                     * basi,postman或者http测试时候添加到请求头
                     */
                    .and().httpBasic()
                    /**
                     * 跳转登录页，但是没有用，下面的 successHandler需要这个返回的类
                     */
                    .and().formLogin()
                    .successHandler(authHandler)
                    .failureHandler(authHandler)
                    // 配置退出参数,下面单独列出
                    ;

            /**
             * http登录. end
             */

            /**
             * 过滤器 before
             */
            http.addFilterBefore(
                    new JsonUsernamePasswordAuthenticationFilter(authenticationManager(), authHandler, authHandler),
                    UsernamePasswordAuthenticationFilter.class
            );

            /**
             * 过滤器 After
             */
            http.addFilterAfter(authTokenFilter, BasicAuthenticationFilter.class);

            /**
             * http退出配置 start
             */
            http.logout()
                    .logoutUrl("/open/auth/logout")
                    .logoutSuccessHandler(authHandler)
                    // 指定是否在注销时让HttpSession无效, 默认设置为 true
                    .invalidateHttpSession(true)
                    // 删除 Cookies
                    //.deleteCookies("Cookies")
                    .addLogoutHandler(new SecurityContextLogoutHandler()) // 添加一个LogoutHandler, // 清除 session
                    // 向客户端发送 清除 “cookie、storage、缓存” 消息
                    .addLogoutHandler((request, response, authentication) -> {// 添加一个LogoutHandler, 退出操作需要删除 redis token 缓存
                        final String token = request.getHeader(HttpHeadersConstant.AUTH_TOKEN);
                        if (!"".equals(token)&&null!=token) {
                            try {
                                log.info("移除用户有token");
                                // 从缓存中删除 用户
                                CustomUserBuilder.removeToken(token);
                                //RedisUtils.removeUser(token);
                            } catch (Exception e) {
                                log.error(e.getMessage(), e);
                                // 有可能 token 已经过期了， 所以需要捕获异常，不向前端抛出
                            }
                        }else {
                            /**
                             * 已经进入，说明 .logoutUrl( 配置正确
                             */
                            log.info("测试是否进入logout");
                        }
                    })
            ;
            /**
             * http退出配置 end
             */
        }

        /**
         * token 验证模式
         */
        OncePerRequestFilter authTokenFilter = new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                if (StringUtils.isBlank(request.getHeader(HttpHeadersConstant.AUTH_TOKEN))) {
                    filterChain.doFilter(request, response);
                    return;
                }
                try {
                    final String token = request.getHeader(HttpHeadersConstant.AUTH_TOKEN);

                    CustomUser customUser = CustomUserBuilder.tokenToUser(token);
                    //customUser = customUser.tokenToUser(token);
                    if(RObjectsUtils.isNull(customUser)){
                        return;
                    }

                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities()));

                    /**
                     * 验证成功后,拦截器转发
                     */
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    ResultE resultE = new ResultE();
                    if(e instanceof UserException){
                        resultE.setCode(((UserException) e).getCode().name());
                        resultE.setException(e.getMessage());
                    }else {
                        resultE = new ResultE<String>(AppCode.A00102).setException(e.getMessage());
                    }

                    log.warn(e.getMessage());
                    response.setContentType(ContentTypeEnum.txt.utf8());
                    PrintWriter writer = response.getWriter();

                    writer.write(resultE.toString());
                    writer.flush();
                    writer.close();
                }
            }
        };

    }

}

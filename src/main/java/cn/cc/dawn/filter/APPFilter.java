package cn.cc.dawn.filter;


import cn.cc.dawn.utils.algo.UUIDUtils;
import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@Component
@WebFilter(urlPatterns="/*")
public class APPFilter implements Filter {


    /**
     * 正式使用 security，这里不做太多的处理
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        MDC.put(HttpHeadersConstant.MDC_header, UUIDUtils.uuid32());
        //Filter.super.init(filterConfig);
        log.info("初始化filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("进入filter");
        filterChain.doFilter(servletRequest, servletResponse);//doFilter将请求转发给过滤器链下一个filter , 如果没有filter那就是你请求的资源
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

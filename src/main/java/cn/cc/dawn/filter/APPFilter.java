package cn.cc.dawn.filter;


import cn.cc.dawn.utils.commons.codec.JUUIDUtils;
import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

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
        //MDC.put(HttpHeadersConstant.MDC_header, UUIDUtils.uuid32());
        //Filter.super.init(filterConfig);
        log.info("初始化filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put(HttpHeadersConstant.MDC_header, JUUIDUtils.uuid32());
        log.info("进入filter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        log.debug("便利请求头");
        Enumeration<String> enumeration = httpServletRequest.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = httpServletRequest.getHeader(name);
            log.debug("name: " + name + " --- value: " + value);
        }

//        Map<String,String> parmMap=new HashMap<String,String>();
//        //方式一：getParameterMap()，获得请求参数map
//        Map<String,String[]> map= httpServletRequest.getParameterMap();
//        log.debug("getParameterMap-size: " + map.size());
//        //参数名称
//        Set<String> key=map.keySet();
//        //参数迭代器
//        Iterator<String> iterator = key.iterator();
//        while(iterator.hasNext()){
//            String k=iterator.next();
//            parmMap.put(k, map.get(k)[0]);
//        }
//        log.debug("parmMap====="+parmMap.toString());

        //方式二：getParameterNames()：获取所有参数名称
        Enumeration<String> a = httpServletRequest.getParameterNames();
        String parm=null;
        String val="";
        log.debug("请求参数");
        while(a.hasMoreElements()){
            //参数名
            parm=a.nextElement();
            //值
            val=httpServletRequest.getParameter(parm);
            log.debug("parm: " + parm + " val: " + val);
        }




        filterChain.doFilter(servletRequest, servletResponse);//doFilter将请求转发给过滤器链下一个filter , 如果没有filter那就是你请求的资源
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

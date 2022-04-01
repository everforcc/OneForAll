package cn.cc.dawn.config.data.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
//        DruidDataSource druidDataSource = new DruidDataSource();
//        log.info("druidDataSource.getInitialSize(): " + druidDataSource.getInitialSize());
        return new DruidDataSource();
    }

    // 后台监控

    /**
     * 内置tomcat，所以没有web。xml ，用ServletRegistrationBean来代替
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        // 后台登录账号密码配置
        //servletRegistrationBean.getServlet()
        //servletRegistrationBean.setLoadOnStartup()

        Map<String, String> initParameters = new HashMap<>();

        // 登录key是固定的
        initParameters.put("loginUsername","admincc");
        initParameters.put("loginPassword","123456cc");

        /**
         * 允许水能访问
         */
        initParameters.put("allow","");

        // 禁止谁访问
        //initParameters.put("username","ip");

        // 设置初始化参数
        servletRegistrationBean.setInitParameters(initParameters);


        return servletRegistrationBean;
    }

    /**
     * filter 过滤器
     */
    @Bean
    public FilterRegistrationBean webStartFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new WebStatFilter());

        Map<String, String> initParameters = new HashMap<>();

        // 不进行统计
        initParameters.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        // 过滤请求
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

}

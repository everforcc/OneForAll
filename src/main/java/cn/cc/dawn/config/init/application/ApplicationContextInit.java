package cn.cc.dawn.config.init.application;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Order(-1) // 数字越小优先级越高
@Slf4j
@Component
public class ApplicationContextInit {

    /**
     * 在三层之外需要处理的信息
     */

    private static ApplicationContext applicationContext;

    /**
     * 线程服务
     * 单线程bean singleThread
     *           multiThread
     */
    private static ExecutorService executorService;

    /**
     * redis客户端
     */
    private static RedissonClient redissonClient;

    /**
     * 缓存管理器
     */
    private static CacheManager cacheManager;

    /**
     * 将获取到的bean放到全局map，后续直接使用
     */
    private static final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 1.1. 初始化 ApplicationContext
     */
    @Bean
    public CommandLineRunner beanInitPrintCommandLineRunner(ApplicationContext context) {
        applicationContext = context;
        return args -> {
            // TODO
            log.info("初始化完成，可以列出系统信息，端口，环境之类的");
        };
    }

    /**
     * 1.2. 获取 ApplicationContext 对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 2.1. cache bean
     * @return
     */
    public static CacheManager getCacheManager() {
        return getBean(CacheManager.class);
    }

    /**
     * 2.2. redis bean
     * @return
     */
    public static RedissonClient getRedissonClient() {
        return getBean(RedissonClient.class);
    }

    /**
     *  2.3.1. 单线程
     * @return
     */
    public static ExecutorService getSingleThread() {
        return getBean("singleThread", ExecutorService.class);
    }

    /**
     *  2.3.2. 多线程
     * @return
     */
    public static ExecutorService getMultiThread() {
        return getBean("multiThread", ExecutorService.class);
    }


    /**
     * 不指定bean name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        if (!beanMap.containsKey(clazz.getName())) {
            /**
             *  保存初始化的bean
             */
            beanMap.put(clazz.getName(), applicationContext.getBean(clazz));
        }
        return (T) beanMap.get(clazz.getName());
    }

    /**
     * 指定bean name
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName,Class<T> clazz) {
        if (!beanMap.containsKey(beanName)) {
            /**
             *  保存初始化的bean
             */
            beanMap.put(beanName, applicationContext.getBean(beanName,clazz));
        }
        return (T) beanMap.get(clazz.getName());
    }

}

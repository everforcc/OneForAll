package cn.cc.dawn.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @description: HttpClient代理配置
 * @author: 刘昊栋  wx:L065517
 * @create: 2021-06-08 15:06
 **/

@Slf4j
@Configuration
public class FeignProxyConfig {

    /**
     * 代理地址
     */
    //@Value("${spring.app.feign.proxy.host}")
    private String uri;

    //@Value("${spring.app.feign.proxy.port}")
    private Integer port;


    @Bean
    @ConditionalOnProperty(value = "spring.app.feign.proxy.enable", havingValue = "true")
    public Client httpClientFactory() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(Objects.requireNonNull(sslContext)))
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                                .setProxy(new HttpHost(uri, port))
                                .build()
                );
        return new ApacheHttpClient(httpClientBuilder.build());
    }
}

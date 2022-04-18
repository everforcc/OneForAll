package cn.cc.dawn.local.craw.web.data.dto;

import cn.cc.dawn.common.sys.dto.CommonFiledDto;
import cn.cc.dawn.utils.RandomUtils;
import cn.cc.dawn.utils.check.ObjectUtils;
import cn.cc.dawn.utils.constant.NumberConstant;
import cn.cc.dawn.utils.enums.BooleanEnum;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.enums.HttpDataTypeEnum;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.*;
import org.apache.http.HttpHost;

import javax.validation.constraints.NotNull;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpParamDto extends CommonFiledDto {

    /**
     * 最基本的要个url
     */
    @NotNull
    private String url;
    /**
     * 请求类型
     */
    private HttpTypeEnum httpTypeEnum;
    /**
     * 代理，
     * TODO 缺少入库映射
     */
    private UProxy proxy;
    /**
     * 超时，是否支持超时重试
     */
    private int timeout;
    /**
     * 超时重试
     */
    private int timeoutRetry;
    /**
     * 字符编码，不写默认UTF-8
     */
    private CharsetsEnum charset = CharsetsEnum.UTF_8;

    /**
     * TODO 认证方案，待处理
     */
    private String cookie;
    private String token;
    private String auth;

    /**
     * 请求头
     * TODO [@link: ] 枚举类支持
     */
    private Map<String,String> headers;

    /**
     * 是否可以输入输出
     */
    private BooleanEnum doinput = BooleanEnum.TRUE;
    private BooleanEnum output = BooleanEnum.FALSE;
    /**
     * 是否是文件
     */
    private BooleanEnum isFile = BooleanEnum.FALSE;

    /**
     * 请求 数据 类型
     */
    private HttpDataTypeEnum httpDataTypeEnum;

    /**
     * POST 请求数据
     *
     * 加个文件枚举类型 str,form,file
     */
    private String content;
    /**
     * TODO 文件
     */
    private String file;
    /**
     * GET 请求参数
     */
    private String requestParam;

    /**
     * 随机延迟
     */
    private int sleep;

    /**
     * TODO 线程池
     */
    private int threadMax;
    private int threadPool;

    /**
     * TODO SSL
     */
    private String ssl;

    /**
     * 设置代理方法
     * @param ip
     * @param port
     */
    public void setProxy(String ip,int port) {
        //this.proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ip,port));
        this.proxy = new UProxy(ip,port);
    }

    /**
     * 如果没设置，最少延迟一秒，最多延迟三秒
     */
    public void sleep(){
        sleep(RandomUtils.randomInt(NumberConstant.N_1,NumberConstant.N_3) * NumberConstant.N_1000);
    }
    public void sleep(int sleep){
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UProxy{

        private String ip;

        private int port;

        private Proxy.Type type;

        public UProxy(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        public Proxy toProxy(){
            if(ObjectUtils.isNull(this.type)){
                return new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ip,port));
            }
            return new Proxy(type,new InetSocketAddress(ip,port));
        }

        public HttpHost toHttpHost(String ip,int port) {
            return new HttpHost(ip,port);
        }

    }

}
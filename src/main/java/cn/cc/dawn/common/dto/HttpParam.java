package cn.cc.dawn.common.dto;

import cn.cc.dawn.common.enums.BooleanEnum;
import cn.cc.dawn.common.enums.CharsetsEnum;
import cn.cc.dawn.common.enums.HttpTypeEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpParam {

    /**
     * 入库的话加个执行顺序，
     * 网站的请求流程 seq
     */

    private String url;
    private HttpTypeEnum httpTypeEnum;
    private Proxy proxy;
    private int timeout;
    private CharsetsEnum charset = CharsetsEnum.UTF_8;

    private String cookie;
    private String token;

    private Map<String,String> headers;

    private BooleanEnum doinput = BooleanEnum.TRUE;
    private BooleanEnum output = BooleanEnum.FALSE;
    private BooleanEnum isFile = BooleanEnum.FALSE;

    private String content;

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(String ip,int port) {
        this.proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ip,port));
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

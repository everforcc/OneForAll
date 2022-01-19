package cn.cc.dawn.common.dto;

import lombok.*;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDo extends CommonFiledDto{
    /**
     * 网站数据对象
     */

    /**
     *  如果是post就是地址
     *  如果是get  ?  传参就是后面的参数
     *  如果是rest风格那就是 去掉参数部分
     */
    private String url;
    // 参数
    private String params;
    // 网站内容
    private String html;

    public String getUrl() {
        return url;
    }

    public void dealUrl(String url) throws MalformedURLException {
        URL u = new URL(url);
        this.url = u.getProtocol() + "://"  + u.getHost() + u.getPath();
        params = u.getQuery();
    }

    public String getParams() {
        return params;
    }

}

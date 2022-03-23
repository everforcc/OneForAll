package cn.cc.dawn.demo.craw.website.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDataDto extends CommonFiledDto{
    /**
     * 网站数据对象
     */

    // tag url 的数据
    private int parentid;
    // 类型
    private int type;
    // 网站内容
    private String html;

//    public void dealUrl(String url) throws MalformedURLException {
//        URL u = new URL(url);
//        this.url = u.getProtocol() + "://"  + u.getHost() + u.getPath();
//        params = u.getQuery();
//    }

}

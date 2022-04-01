package cn.cc.dawn.open.web.data.dto;

import cn.cc.dawn.common.sys.dto.CommonFiledDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDataDto extends CommonFiledDto {
    /**
     * 网站数据对象
     */

    // tag url 的数据
    private int parentid;
    // 类型
    private int type;
    // 网站内容
    private String html;

    /**
     * 是否存在磁盘
     * 1 是
     * 0 否
     */
    private boolean disk;

    /**
     * 磁盘路径
     */
    private String path;

//    public void dealUrl(String url) throws MalformedURLException {
//        URL u = new URL(url);
//        this.url = u.getProtocol() + "://"  + u.getHost() + u.getPath();
//        params = u.getQuery();
//    }

}

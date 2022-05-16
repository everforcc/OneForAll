package cn.cc.dawn.local.craw.business.data.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.impl.FileMediumEnum;
import cn.cc.dawn.utils.enums.impl.FileTypeEnum;
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
    //
    /**
     * 类型
     * 1 IO
     * 2 数据库
     * 3 oss
     *
     */
    private FileMediumEnum medium;
    // 网站内容，不入库
    private String html;
    // 媒体内容，不入库
    private byte[] bytes;
    // 文件名
    private String filename;
    // 磁盘路径
    private String path;
    // 文件类型，文本还是字节流
    private FileTypeEnum fileTypeEnum;

    /**
     * 是否存在磁盘
     * 1 是
     * 0 否
     * FileMediumEnum字段
     */
    //@Deprecated
    //private boolean disk;



//    public void dealUrl(String url) throws MalformedURLException {
//        URL u = new URL(url);
//        this.url = u.getProtocol() + "://"  + u.getHost() + u.getPath();
//        params = u.getQuery();
//    }

}

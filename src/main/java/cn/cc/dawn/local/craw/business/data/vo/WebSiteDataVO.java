package cn.cc.dawn.local.craw.business.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求网站数据临时保存的地方
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDataVO {

    private String pageSource;
    private String title;
    private byte[] bytes;

}

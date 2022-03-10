package cn.cc.dawn.business.novel.dto;

import cn.cc.dawn.utils.constant.HttpConstant;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovelReqRegDto {

    /**
     * 此类用来处理正则
     */

    private HttpTypeEnum defaultRequestType = HttpTypeEnum.GET;

    private CharsetsEnum charset = CharsetsEnum.UTF_8;
    private int defaultTimeout = 6000;

    /**
     * 1. 请求头
     */
    private Map<String,String> headers;

    /**
     * 2. 不同网站的认证方式，有的是cookie，有的是其他
     */
    private Map<String,String> authon;

    // 3. 网站名
    private String regWebName;

    // 4.0
    private HttpTypeEnum regNovelUrlReqType;
    // 4.1 小说地址
    private String regNovelUrl;

    // 5. 小说名
    private String regNovelName;

    // 6.0
    private HttpTypeEnum regCapterUrlReqType;
    // 6.1 获取小说章节地址
    private String regCapterListUrl;
    // 6.2 下一页 可以为空
    private String regCapterListUrlNext;
    // 6.3 END 可以为空
    private String regCapterListUrlEnd;

    // 6.4 小说章节地址列表
    private String regCapterList;
    // 6.5 章节链接
    private String regCapterUrl;
    // 6.6 章节名
    private String regCapterName;

    // 7.0
    private HttpTypeEnum regContentUrlReqType;
    // 7.1 小说章节 地址
    private String regContentUrl;
    // 7.2 下一页 可以为空
    private String regContentUrlNext;
    // 7.3 END 可以为空
    private String regContentUrlEnd;
    // 7.4 内容正则
    private String regContent;

}

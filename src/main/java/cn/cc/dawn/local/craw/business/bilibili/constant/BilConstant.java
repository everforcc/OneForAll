package cn.cc.dawn.local.craw.business.bilibili.constant;

import cn.cc.dawn.utils.date.DateUtils;
import cn.cc.dawn.utils.file.IFilePathBusi;

/**
 * Yukino
 * 2020/3/3
 */
public class BilConstant {

    /**
     * 用户信息 start
     */
    public static String user_msg = "https://api.bilibili.com/x/space/acc/info?mid=%s&jsonp=jsonp";
    /**
     * 用户信息 end
     */

    /**
     * 相册 start
     */
    /**
     *  TODO 需要存这个信息,如果doc_id 失效了还能从这个开始找
     *  获取相册总数的url
     *  x/dynamic/feed/draw/doc_list
     *  https://api.bilibili.com/x/dynamic/feed/draw/doc_list?uid=28380168&page_num=0&page_size=30&biz=all&jsonp=jsonp
     *  doc_id 换详情
     *  https://api.vc.bilibili.com/link_draw/v1/doc/detail?doc_id=192542805
     */
    public static String album_allcount = "https://api.vc.bilibili.com/link_draw/v1/doc/upload_count?uid=%s";

    public static String album_alldetail = "https://api.vc.bilibili.com/link_draw/v1/doc/doc_list?uid=%s&page_num=0&biz=all&page_size=%s";
    /**
     * 相册 end
     */


    /**
     * 设置为自己的文件夹
     */
    public static final IFilePathBusi BILIBILI_FILE_PATH_BUSI = new BilFililePathBusi();

    /**
     * cookie
     */
    public static String cookie="l=v; _uuid=8B9D4BFE-64EF-B8B6-E901-BC64AA36FA6B81905infoc; buvid3=34DEA7B4-4819-4329-841A-F1D908941268138392infoc; sid=6bgvpmoi; DedeUserID=58572396; DedeUserID__ckMd5=20e54bd3090b9e60; SESSDATA=40c8ae33%2C1616331012%2Caa953*91; bili_jct=d9767064501a2b1a310c59e20f1e3721; CURRENT_FNVAL=80; blackside_state=1; rpdid=|(J|)J|uullJ0J'uY||~~m|Ru; bsource=search_baidu; bp_video_offset_58572396=438132817916329216; bp_t_offset_58572396=438132817916329216";

    /**
     * 请求头
     */
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/"+ DateUtils.nowTime("yyyyMMdd")+" Firefox/29.0";

    /**
     * 数据参数
     */
    public static final String count = "count" ;
    public static final String vlist = "vlist";
    public static final String aid = "aid" ;

    public static class BilFililePathBusi implements IFilePathBusi {
        @Override
        public String busiPath() {
            return "/bilibili";
        }
    }

    public static void main(String[] args) {
        String result = String.format(BilConstant.album_allcount,"avbv");
        System.out.println(result);
    }

}

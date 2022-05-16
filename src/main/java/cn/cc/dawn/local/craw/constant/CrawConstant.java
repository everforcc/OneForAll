package cn.cc.dawn.local.craw.constant;

import cn.cc.dawn.utils.date.DateUtils;
import cn.cc.dawn.utils.file.IFilePathBusi;

/**
 * Yukino
 * 2020/3/3
 */
public class CrawConstant {

    /**
     * 设置为自己的文件夹
     */
    public static final IFilePathBusi rootFilePath = new SysFilePathBusi();

    /**
     * 通用的请求头
     */
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/"+ DateUtils.nowTime("yyyyMMdd")+" Firefox/29.0";
    public static final String Chrome = "Chrome";
    public static final String Android = "Android";

    public static class SysFilePathBusi implements IFilePathBusi {

        @Override
        public String busiPath() {
            return "/system";
        }
    }

}

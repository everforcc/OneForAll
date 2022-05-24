/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-24 15:15
 * Copyright
 */

package cn.cc.dawn.utils.http;

import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.regex.RegexUtils;
import lombok.SneakyThrows;

import java.net.URLDecoder;

public class HttpHeaderUtils {

    private static String dispositonFileRegex = "attachment;filename=\"(.*)\"";
    private static int dispositonFileGroup = 1;

    /**
     * 将 content_dispositon 处理为文件名
     * @param content_dispositon
     * @return
     */
    @SneakyThrows
    public static String contentDispositionToFileName(String content_dispositon){
        AppCode.A00111.assertHasTrue(StringUtils.isNotEmpty(content_dispositon));
        //String fileName = content_dispositon.replace("attachment;filename=\"", "").replace("\"", "");
        String fileName = RegexUtils.matcheStr(dispositonFileRegex,content_dispositon,dispositonFileGroup);
        return URLDecoder.decode(fileName, CharsetsEnum.UTF_8.charset.toString());
    }

    public static void main(String[] args) {
        String content_dispositon = "attachment;filename=\"中文.mp4\"";
        System.out.println(contentDispositionToFileName(content_dispositon));
    }

}

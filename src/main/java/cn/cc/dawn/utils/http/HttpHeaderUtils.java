/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-24 15:15
 */

package cn.cc.dawn.utils.http;

import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.commons.regex.RegexUtils;
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
        AppCode.A00111.assertHasTrue(RStringUtils.isNotEmpty(content_dispositon));
        //String fileName = content_dispositon.replace("attachment;filename=\"", "").replace("\"", "");
        String fileName = RegexUtils.matcheStr(dispositonFileRegex,content_dispositon,dispositonFileGroup);
        return URLDecoder.decode(fileName, CharsetsEnum.UTF_8.charset.toString());
    }

    public static void main(String[] args) {
        String content_dispositon = "attachment;filename=\"中文.mp4\"";
        System.out.println(contentDispositionToFileName(content_dispositon));
    }

}

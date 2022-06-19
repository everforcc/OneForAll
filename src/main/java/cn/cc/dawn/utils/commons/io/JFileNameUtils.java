/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-26 09:22
 */

package cn.cc.dawn.utils.commons.io;

import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JFileNameUtils {

    // 文件名最长 255
    private static final int nameMaxLength = 255;

    public static String checkFileName(String fileName) {
        return checkFileName(fileName, false);
    }

    /**
     * 默认false需要截取
     *
     * @param fileName 文件名
     * @param split
     * @return
     */
    public static String checkFileName(String fileName, boolean split) {
        //在Windows系统中，文件名命名规则如下：
        //1）文件名最长可以使用255个字符；
        //2）可以使用扩展名，扩展名用来表示文件类型，也可以使用多间隔符的扩展名（如win.ini.txt是一个合法的文件名，但其文件类型由最后一个扩展名决定）；
        //3）文件名中允许使用空格，但不允许使用下列字符（英文输入法状态）：< > / \ | : " * ?；
        //4）windows系统对文件名中字母的大小写在显示时有不同，但在使用时不区分大小写。

        AppCode.A00100.assertHasTrue(StringUtils.isNotEmpty(fileName), "文件名不能为空");

        String pattern = "\\<*\\>*\\/*\\\\*\\|*\\:*\"*\\**\\?*\\；*\\ *";

        fileName = fileName.replaceAll(pattern, "");

        int length = fileName.length();

        if (split) {
            if (length > nameMaxLength) {
                log.info("处理完特殊字符仍然超长的文件名 [{}]", fileName);
                // 文件后缀名
                String fileSuffixName = FilePath.getSuffix(fileName, true);
                // 后缀名长度
                int fileSuffixLength = fileSuffixName.length();
                AppCode.A00164.assertHasTrue(fileSuffixLength < nameMaxLength, "文件后缀名[%s]过长", fileSuffixName);
                // 为了获取文件名不含后缀
                int lastIndexOf = fileName.lastIndexOf(".");
                // 超长多少
                int dif = length - nameMaxLength;
                // 文件名这个长度
                int nameLength = lastIndexOf - dif;
                String newFileName = fileName.substring(0, nameLength) + fileSuffixName;
                log.info("截取后的后缀名 [{}]", newFileName);
                return newFileName;
            }
        } else {
            AppCode.A00162.assertHasTrue((length < nameMaxLength), "文件名[%s]长度不能超过255", fileName);
        }

        return fileName;
    }

    public static void main(String[] args) {

        String fileName = "abc.txt";
        System.out.println(checkFileName(fileName));

    }

}

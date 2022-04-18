package cn.cc.dawn.utils.file;

import cn.cc.dawn.utils.algo.UUIDUtils;
import cn.cc.dawn.utils.enums.ContentTypeEnum;
import cn.cc.dawn.utils.exception.AppCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * 构建文件信息
 */
@Slf4j
public class FileBuilder {

    /**
     * 文件名处理
     */
    public static class FileMsg {
        /**
         * 文件目录，可选参数
         */
        @Getter
        private final String dir;
        /**
         * 原始文件名，带后缀
         */
        @Getter
        private final String rname;
        /**
         * 目录+唯一文件名，带后缀
         */
        @Getter
        private String uname;

        /**
         * 文件大小
         */
        @Getter
        private String size;

        private FileMsg(String size,String dir, String rname) {
            this.size = size;
            this.dir = dir;

            try {
                this.rname = URLDecoder.decode(rname,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw AppCode.A00100.toUserException("文件名解码异常");
            }
            if (StringUtils.isBlank(dir)) {
                this.uname = rname;
            } else {
                this.uname = String.format("%s/%s", dir, rname);
            }
        }

        public static FileMsg of(MultipartFile uploadFile, final String... dirs) {
            String filename = uploadFile.getOriginalFilename();
            String size = String.valueOf(uploadFile.getSize());
            Objects.requireNonNull(filename, "参数【filename】是必须的");
            return new FileMsg(size,
                    String.join("/", dirs)
                            .replaceAll("[/]{2,}", "/") //  如果给的目录结构带的有 "/",去除目录中间多余的 "/"
                            .replaceFirst("/$", ""), // 去除目录结尾的 "/"
                    filename
            );
        }

        /**
         * 获取文件后缀名,带"."
         *
         * @return String
         */
        public String getSuffix() {
            return getSuffix(true);
        }

        /**
         * 文件名到最后一个点的正则
         * 获取文件后缀名，可选择是否带点；例：test.txt，带点则返回：.txt，不带点则返回：txt
         *
         * @param offset 是否带.,true：带点.，false：不带点.
         * @return String
         */
        public String getSuffix(boolean offset) {
            return rname.replaceFirst("^.+\\.", (offset ? "." : ""));
        }

        /**
         * 获取文件名，不带后缀
         *
         * @return String
         */
        public String getPrefix() {
            return rname.substring(0, rname.lastIndexOf("."));
        }

        /**
         * 文件类型必须在 {@link ContentTypeEnum#type} 中先定义
         *
         * @return {@link String}
         */
        public String getContentType() {
            try {
                return ContentTypeEnum.valueOf(getSuffix(false)).type;
            } catch (IllegalArgumentException e) {
                log.warn("文件类型{}未定义,ContentTypeEnum，默认使用 默认的content-type: application/octet-stream",getSuffix());
                return ContentTypeEnum.stream.type;
            }
        }

        /**
         * 构造 UUID 文件名带后缀， 拼接目录前缀
         *
         * @return FileName
         */
        public FileMsg buildUuidUname() {
            if (StringUtils.isBlank(dir)) {
                this.uname = UUIDUtils.uuid32() + getSuffix();
            } else {
                this.uname = String.format("%s/%s%s", dir, UUIDUtils.uuid32(), getSuffix());
            }
            return this;
        }

        /**
         * MD5 工具类已删，需要再加
         */

    }

}

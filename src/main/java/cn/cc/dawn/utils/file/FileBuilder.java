package cn.cc.dawn.utils.file;

import cn.cc.dawn.utils.algo.MD5Utils;
import cn.cc.dawn.utils.algo.UUIDUtils;
import cn.cc.dawn.utils.enums.ContentTypeEnum;
import lombok.Cleanup;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * 构建文件信息
 */
@Slf4j
public class FileBuilder {

    /**
     * 文件名处理
     */
    public static class FileName {
        /**
         * 文件目录，可选参数
         */
        @Getter
        private final String dir;
        /**
         * 原始文件名，带后缀
         */
        @Getter
        private final String name;
        /**
         * 目录+唯一文件名，带后缀
         */
        @Getter
        private String uname;

        private FileName(String dir, String name) {
            this.dir = dir;
            this.name = name;
            if (StringUtils.isBlank(dir)) {
                this.uname = name;
            } else {
                this.uname = String.format("%s/%s", dir, name);
            }
        }

        public static FileName of(final String filename, final String... dirs) {
            Objects.requireNonNull(filename, "参数【filename】是必须的");
            return new FileName(
                    String.join("/", dirs)
                            .replaceAll("[/]{2,}", "/") // 去除目录中间多余的 "/"
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
            return name.replaceFirst("^.+\\.", (offset ? "." : ""));
        }

        /**
         * 获取文件名，不带后缀
         *
         * @return String
         */
        public String getPrefix() {
            return name.substring(0, name.lastIndexOf("."));
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
        public FileName buildUuidUname() {
            if (StringUtils.isBlank(dir)) {
                this.uname = UUIDUtils.uuid32() + getSuffix();
            } else {
                this.uname = String.format("%s/%s%s", dir, UUIDUtils.uuid32(), getSuffix());
            }
            return this;
        }

        /**
         * 构造 MD5 文件名带后缀，拼接目录前缀
         *
         * @return FileName
         */
        @SneakyThrows
        public FileName buildMd5Uname(final String text) {
            if (StringUtils.isBlank(dir)) {
                this.uname = MD5Utils.md5Hex(text) + getSuffix();
            } else {
                this.uname = String.format("%s/%s%s", dir, MD5Utils.md5Hex(text), getSuffix());
            }
            return this;
        }

        /**
         * 构造 MD5 文件名带后缀，拼接目录前缀
         *
         * @return FileName
         */
        @SneakyThrows
        public FileName buildMd5Uname(final byte[] bytes) {
            if (StringUtils.isBlank(dir)) {
                this.uname = MD5Utils.md5Hex(bytes) + getSuffix();
            } else {
                this.uname = String.format("%s/%s%s", dir, MD5Utils.md5Hex(bytes), getSuffix());
            }
            return this;
        }

        /**
         * 构造 MD5 文件名带后缀，拼接目录前缀
         *
         * @return FileName
         */
        @SneakyThrows
        public FileName buildMd5Uname(final File file) {
            @Cleanup final FileInputStream fileInputStream = new FileInputStream(file);
            @Cleanup final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            if (StringUtils.isBlank(dir)) {
                this.uname = DigestUtils.md5Hex(bufferedInputStream) + getSuffix();
            } else {
                this.uname = String.format("%s/%s%s", dir, DigestUtils.md5Hex(bufferedInputStream), getSuffix());
            }
            return this;
        }

        /**
         * 构造 MD5 文件名带后缀，拼接目录前缀
         *
         * @return FileName
         */
        @SneakyThrows
        public FileName buildMd5Uname(final InputStream inputStream) {
            if (StringUtils.isBlank(dir)) {
                this.uname = DigestUtils.md5Hex(inputStream) + getSuffix();
            } else {
                this.uname = String.format("%s/%s%s", dir, DigestUtils.md5Hex(inputStream), getSuffix());
            }
            return this;
        }

    }

}

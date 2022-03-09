package cn.cc.dawn.utils.enums;

import lombok.SneakyThrows;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 字符编码
 *
 */
public enum CharsetsEnum {
    // UTF-8 字符编码
    UTF_8("UTF-8", StandardCharsets.UTF_8),
    GBK("GBK", Charset.forName("GBK")),
    ISO_8859_1("ISO-8859-1", StandardCharsets.ISO_8859_1),
    UTF_16BE("UTF-16BE", StandardCharsets.UTF_16BE),
    UTF_16LE("UTF-16LE", StandardCharsets.UTF_16LE),
    UTF_16("UTF-16", StandardCharsets.UTF_16),
    big5("big5", Charset.forName("big5")),
    gb2312("gb2312", Charset.forName("gb2312")),
    ;
    public final String comment;
    public final Charset charset;

    public String displayName() {
        return charset.displayName();
    }

    /**
     * url 字符串编码
     *
     * @param value {@link String}
     * @return {@link String}
     */
    @SneakyThrows
    public String encode(final String value) {
        return URLEncoder.encode(value, charset.displayName());
    }

    /**
     * url 字符串解码
     *
     * @param value {@link String}
     * @return {@link String}
     */
    @SneakyThrows
    public String decode(final String value) {
        return URLDecoder.decode(value, charset.displayName());
    }

//    /**
//     * url 字符串编码
//     *
//     * @param value {@link String}
//     * @return {@link String}
//     */
//    public String encoder(final String value) {
//        return charset.encode(value);
//    }
//
//    /**
//     * url 字符串解码
//     *
//     * @param value {@link String}
//     * @return {@link String}
//     */
//    public String decoder(final String value) {
//
//        return ByteBuffer.wrap(value.getBytes(charset));
//    }

    CharsetsEnum(final String comment, final Charset charset) {
        this.comment = comment;
        this.charset = charset;
    }

    public static void main(String[] args) {
        System.out.println(UTF_8.encode("?json={\"key\":\"value\"}"));
        System.out.println(UTF_8.encode("?json={\"key\":\"value+JS\"}"));
        System.out.println(UTF_8.decode("%3Fjson%3D%7B%22key%22%3A%22value%22%7D"));

        System.out.println(UTF_8.displayName());
    }
}

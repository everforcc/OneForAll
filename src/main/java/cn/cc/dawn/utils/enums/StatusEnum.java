package cn.cc.dawn.utils.enums;

/**
 * @author guokailong 2021-09-07
 */
public enum StatusEnum {

    /**
     * 无效
     */
    UNEFFECT("0"),
    /**
     * 有效
     */
    EFFECT("1");

    /**
     * 枚举属性说明
     */
    public final String comment;
    /**
     * 是否已废弃
     */
    public final boolean deprecated;

    StatusEnum(final String comment) {
        this(comment, false);
    }

    StatusEnum(final String comment, final boolean deprecated) {
        this.comment = comment;
        this.deprecated = deprecated;
    }

}

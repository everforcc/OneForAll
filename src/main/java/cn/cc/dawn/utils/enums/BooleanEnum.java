package cn.cc.dawn.utils.enums;

public enum BooleanEnum {

    TRUE("true", true),
    FALSE("false", false),
    ;

    public final String comment;
    public final boolean flag;

    BooleanEnum(String comment, boolean flag) {
        this.comment = comment;
        this.flag = flag;
    }

    public static void main(String[] args) {
        System.out.println(BooleanEnum.TRUE.comment);
        System.out.println(BooleanEnum.TRUE.flag);

        System.out.println(BooleanEnum.FALSE.comment);
        System.out.println(BooleanEnum.FALSE.flag);
    }

}

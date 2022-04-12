package cn.cc.dawn.utils.enums;

public enum HttpDataTypeEnum {

    STR("STR"),
    FORM("FORM"),
    FILE("FILE"),
    PARAMS("?a=a"),
    ;

    public final String type;

    HttpDataTypeEnum(String type) {
        this.type = type;
    }

    public static void main(String[] args) {
        System.out.println(HttpTypeEnum.GET.type);
    }


}

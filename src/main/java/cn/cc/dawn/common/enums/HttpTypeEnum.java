package cn.cc.dawn.common.enums;

public enum HttpTypeEnum {

    GET("GET", "GET"),
    POST("POST", "POST"),
    // PUT 等不常用
    ;

    public final String comment;
    public final String type;

    HttpTypeEnum(String comment, String type) {
        this.comment = comment;
        this.type = type;
    }

    public static void main(String[] args) {
        System.out.println(HttpTypeEnum.GET.type);
        System.out.println(HttpTypeEnum.GET.comment);
    }

}

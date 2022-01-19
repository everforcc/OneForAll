package cn.cc.dawn.utils.exception;


public enum Code implements ICode {
    A00000("成功"),
    A00001("失败"),
    A00002("自定义2"),
    A00003("自定义3"),
    A00004("自定义4"),
    ;
    /**
     * 枚举属性说明
     */
    public final String comment;

    Code(String comment) {
        this.comment = comment;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    public static void main(String[] args) {

    }

}

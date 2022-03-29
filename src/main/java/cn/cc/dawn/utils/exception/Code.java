package cn.cc.dawn.utils.exception;


public enum Code implements ICode {
    A00000("成功"),
    A00001("失败"),
    A00002("自定义异常，将会以 exception 内容替换 message 内容，一般用于抛出带动态参数消息，直接在前端弹窗"),
    A00003("会话超时"),
    A00004("参数校验失败"),
    A00005("接口版本号不匹配"),
    A00006("请求地址不存在"),
    A00007("请求缺少必要的参数"),
    A00008("请求参数类型不匹配或 JSON 格式不符合规范"),
    A00009("请求方式不支持"),
    A00010("请求不存在"),
    A00011("无操作权限"),
    A00012("排序字段不在可选范围"),
    A00013("分页查询，每页显示数据量超过最大值"),
    A00014("上传文件列表为空"),
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

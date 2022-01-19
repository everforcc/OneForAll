package cn.cc.dawn.utils.exception;

import lombok.val;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum AppCode implements ICode {
    A01000("用户名密码错误"),
    A01001("数据库更新失败"),
    A01002("抢购失败"),

    A02000("没有库存了"),
    ;
    /**
     * 枚举属性说明
     */
    public final String comment;

    AppCode(String comment) {
        this.comment = comment;
    }


    @Override
    public String getComment() {
        return this.comment;
    }



}

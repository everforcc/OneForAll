package cn.cc.dawn.utils.exception;

import lombok.val;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum AppCode implements ICode {
    /**
     * A00000 系列为系统公共异常 Code
     * A00100 A00200 系列为用户异常,和测试代码使用异常
     * A00201 A00300 数据库相关异常
     * A00201 A00300 数据库相关异常
     * A00301 A00400 数据校验异常
     */
    A00100("自定义异常，可以使用comment替换"),

    A00101("用户名密码错误"),
    A00102("已过期，请重新登录"),

    A00111("数据库更新失败"),
    A00112("抢购失败"),
    A00113("没有库存了"),

    A00201("数据库插入失败"),
    A00202("数据库更新失败"),

    A00301("对象不能为空"),
    A00302("string不能为空"),
    A00303("json格式有误"),

    /**
     * 业务类异常，分类在下面追加,每个不超过100个，如果超过，另起一块追加
     */

    /**
     * xx需求异常
     */
    A01000("业务异常 start"),
    A01099("业务异常 end"),

    /**
     * xx需求异常
     */
    A01100("业务异常 start"),
    A01199("业务异常 end"),

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

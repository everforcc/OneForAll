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
    A00103("无权限访问，请登录"),
    A00104("系统中未拦截到的异常"),
    A00105("邮件数据配置异常"),

    A00111("数据库更新失败"),
    A00112("抢购失败"),
    A00113("没有库存了"),

    A00150("IO异常"),
    // servlet文件异常
    A00160("未查询到指定文件"),
    A00161("文件无数据"),
    A00162("文件名超长"),
    A00163("辛茹苦网站必须有 softtype 和 webtype "),

    A00201("数据库插入失败"),
    A00202("数据库更新失败"),

    A00301("对象不能为空"),
    A00302("string不能为空"),
    A00303("json格式有误"),
    A00304("入参不允许为空"),
    A00305("文件名不允许为空"),

    /**
     * 业务类异常，分类在下面追加,每个不超过100个，如果超过，另起一块追加
     */

    /**
     * webdata需求异常 start
     */
    A01000("网站根地址  不允许为空"),
    A01001("网站名     不允许为空"),
    A01002("网站类型    不允许为空"),
    A01003("数据类型    不允许为空"),
    A01004("网站vip类型    不允许为空"),
    A01005("网站数据保存地址    不允许为空"),
    A01006("网站数据保存内容    不允许为空"),
    A01007("暂时只支持将数据保存到磁盘中"),
    A01008("指定需要保存的数据类型"),

    A01050("ys查询地址为空"),
    A01051("ys查询类型为空"),
    A01052("uid为空"),
    A01053("星级为空"),
    A01054("链接异常"),
    A01099("业务异常 end"),
    /**
     * webdata需求异常 end
     */


    /**
     * xx需求异常 start
     */
    A01100("业务异常 start"),
    A01199("业务异常 end"),
    /**
     * xx需求异常 end
     */

    /**
     * IDM start
     */
    A01200("返回文件大小"),
    /**
     * IDM end
     */

    /**
     * 抽卡小游戏 start
     */
    A01300("游戏不存在"),
    A01301("必须设置基础概率"),
    /**
     * 抽卡小游戏 end
     */

    /**
     * bd网盘 start
     */
    A01400("bddisk 接口请求结果有误"),
    /**
     * bd网盘 end
     */
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

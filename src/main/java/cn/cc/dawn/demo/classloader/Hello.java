/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-06-09 09:07
 * Copyright
 */

package cn.cc.dawn.demo.classloader;

import cn.cc.dawn.utils.constant.CommonCharConstant;

public class Hello {

    public void say(String s){
        System.out.println("s: " +  s);
    }

    public void sayWord(){
        System.out.println("s: " +  Word.word);
    }

    public void sayConstant(){
        System.out.println("s: " + CommonCharConstant.AND);
    }

}

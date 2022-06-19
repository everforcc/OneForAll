/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-09 09:07
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

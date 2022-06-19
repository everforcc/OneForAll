/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-06-08 22:39
 * Copyright
 */

package cn.cc.dawn.demo.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

//@Slf4j
//@RequestMapping(value = "/demo/resource")
//@RestController
public class ResourceController {

    public static void main(String[] args) {
        //classResource();
        //newFile();
        //classLoader();
        systemProperty();
    }

    public static void classResource(){
        //获取当前类class所在的resource路径
        System.out.println(ResourceController.class.getResource("/").getPath());
        //获取当前类class所在的路径：
        System.out.println(ResourceController.class.getResource("").getPath());
    }

    public static void newFile(){
        File f = new File("");
        System.out.println("空字符file的Path : " + f.getPath());
        try {
            System.out.println("空字符file的标准路径CanonicalPath : " + f.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("空字符file的绝对路径AbsolutePath : " + f.getAbsolutePath());
    }

    /**
     * 类路径
     */
    public static void classLoader(){
        URL xmlpath = new ResourceController().getClass().getClassLoader().getResource("/");
        URL xmlpath1 = new ResourceController().getClass().getClassLoader().getResource("");
        System.out.println("获取当前类被加载的工程路径：" + xmlpath);
        System.out.println("获取当前类被加载的路径：" + xmlpath1);
    }

    public static void systemProperty(){
        System.out.println(System.getProperty("user.dir"));

        System.out.println("------system配置清单----");

        Properties p = System.getProperties();
        Set<Map.Entry<Object, Object>> entrys = p.entrySet();
        for (Map.Entry<Object, Object> entry : entrys)
        {
            System.out.println(entry.getKey().toString() + " : " + entry.getValue().toString());
        }
    }

}

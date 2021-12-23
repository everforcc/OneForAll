package cn.cc.dawn.config.init;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Yukino
 * 2020/3/9
 */
public class Init {

    private static Map<Integer,String> map ;
    private static Integer i = 0 ;

    private Init(Map<Integer,String> map){
        this.map=map;
    };

    public static synchronized  Init getInstanse(){
        if(map == null){
            map = new HashMap<Integer, String>();
            map = initNOWSFUN();
            System.out.println("初始化--------");
        }
        return new Init(map);
    }


    public String getFun(){
        int a = new Double(Math.random()* 1000).intValue()  + 1 ;
        System.out.println("a:"+a);
        return map.get(a);
    }

     static Map<Integer,String> initNOWSFUN(){
         /**
          * 1.
          * File file = new File("src/main/resources/static/assets/test.txt");
          * InputStream inputStream=new FileInputStream(file);
          * 2.
          * File file = ResourceUtils.getFile("classpath:static/assets/test.txt");
          * InputStream inputStream=new FileInputStream(file);
          * 3.
          * ClassPathResource classPathResource = new ClassPathResource("static/assets/test.txt");
          * InputStream inputStream = classPathResource.getInputStream();
          */
        ClassPathResource classPathResource = new ClassPathResource("NOWSFUN.txt");

        try {
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String content;
            while((content = bufferedReader.readLine() )!=null){
                map.put(i,content);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常");
        }
        return map;
    }

}

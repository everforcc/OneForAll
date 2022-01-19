package cn.cc.dawn.config.init.properties;


import org.springframework.core.io.ClassPathResource;

import java.util.*;

public class PropertiesHeader {

    // 配置文件根目录
    private static String path = "init/properties/header-properties/";
    public static void main(String[] args) {
        spring4uMap();
    }
    private static Map<String,String>  getMap(String resource){

        try {
            // 静态方法和非晶态方法不同
            //InputStream in = getClass().getResourceAsStream("/properties/basecom.properties");
            //InputStream in = PropertiesHeader.class.getResource(path + resource).openStream();
            ClassPathResource classPathResource = new ClassPathResource(path + resource);
            Properties properties = new Properties();
            properties.load(classPathResource.getInputStream());

            Map<String,String> map = new HashMap<>();
            Iterator it = properties.entrySet().iterator();
            // 每个配置文件单独处理一个请求的请求头，只需要便利就可以获取所有的请求头信息, 可以把配置文件的名字也独立出来
            while(it.hasNext()){
                Map.Entry entry=(Map.Entry)it.next();
                //System.out.println(entry.getKey().toString()+"="+entry.getValue());
                map.put((String)entry.getKey(),(String) entry.getValue());
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // spring4u.info.properties
    public static Map<String,String> spring4uMap(){
        String fileName = "spring4u.info.properties";
        return getMap(fileName);
    }

    public static Map<String,String> ysCardMap(){
        String fileName = "ys.properties";
        return getMap(fileName);
    }

}

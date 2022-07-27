package cn.cc.dawn.config.init.json;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JSONInit {

    private static String initJSON(String name){
        ClassPathResource classPathResource = new ClassPathResource("init/json/" + name);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String content;
            while((content = bufferedReader.readLine() )!=null){
                stringBuffer.append(content);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static String getYSJson(){
        return initJSON("ys/YsCard.json");
    }

    public static String getBD_FileList(){
        return initJSON("bd/filelist.json");
    }

}

package cn.cc.dawn.demo.file.service;

import com.google.common.io.ByteStreams;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service()
public class ResponseBytesService {

    public byte[] getFileBytes(){
        String path = "E:/项目/boc/临时/sj-健康监测二维码.jpg";
        try {
            return ByteStreams.toByteArray(FileUtils.openInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}

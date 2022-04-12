package cn.cc.dawn.demo.servlet.service;

import cn.cc.dawn.demo.servlet.vo.ServletVO;
import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.file.IFile;
import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import com.google.common.io.ByteStreams;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service()
public class ResponseBytesService {

    IFile iFile = new FileApacheUtils();

    public ServletVO getFileBytes(){
        String fileName = "test中文-1.txt";
        String path = "E:\\filesystem\\test\\临时\\test中文.txt";
        File file = new File(path);
        ServletVO servletVO = new ServletVO();
        try {
            servletVO.setBytes(ByteStreams.toByteArray(FileUtils.openInputStream(file)));
            servletVO.setContentType(HttpHeadersConstant.txt_plain_UTF_8);
            servletVO.setDownFileName(fileName);
            servletVO.setCache_control("0", iFile.lastModifiedTime(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return servletVO;
    }

    public byte[] getTXTBytes(){
        String path = "<h1>这是个文本</h1>";
        return path.getBytes(CharsetsEnum.UTF_8.charset);
    }

}

package cn.cc.dawn.common.file.service;

import cn.cc.dawn.common.file.vo.ServletVO;
import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import cn.cc.dawn.utils.enums.ContentTypeEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.path.FilePath;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.file.IFileHandle;
import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import com.google.common.io.ByteStreams;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service()
public class ResponseBytesService {

    IFileHandle iFileHandle = new FileApacheUtils();

    public ServletVO getFileBytes(String uuname){
        //String fileName = "20210712-双手剑原胚.png";
        FilePath filePath = FilePath.build().ofPath("test").ofFileName(uuname);
        File file = filePath.file();
        AppCode.A00160.assertHasTrue(file.exists());
        ServletVO servletVO = new ServletVO();
        try {
            servletVO.setBytes(ByteStreams.toByteArray(FileUtils.openInputStream(file)));
            //servletVO.setContentType(HttpHeadersConstant.txt_plain_UTF_8);


            servletVO.setContentType(ContentTypeEnum.valueOf(filePath.getSuffix()).type);
            //servletVO.setDownFileName(fileName);
            servletVO.setReadFileName(uuname);
            servletVO.setCache_control("0", iFileHandle.lastModifiedTime(file));
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

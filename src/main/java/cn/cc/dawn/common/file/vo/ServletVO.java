package cn.cc.dawn.common.file.vo;

import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ServletVO {

    /**
     * servlet对象，组装返回所用
     */

    private byte[] bytes;

    private String contentType;

    /**
     * response.setHeader("content-disposition", "attachment;filename="+ new String( "webmagic中文.txt".getBytes("gb2312"), "ISO8859-1" ));
     */
    private Map<String,String> headerMap  ;

    /**
     * JSP或者Servlet中的输出被浏览器保存在缓冲区中
     */
    private Map<String,Long> dateHeaderMap;

    private String downFileName;

    private String cache_control;

    private String last_modified;

    private Long expire;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 当中文文字超过17个时，IE6 无法下载文件。这是IE的bug，懒得适配改没必要
     *
     * @param downFileName
     * @throws UnsupportedEncodingException
     */
    public void setDownFileName(String downFileName) throws UnsupportedEncodingException {
        this.downFileName = downFileName;
        // "attachment;filename="+ new String( "webmagic中文.txt".getBytes("gb2312"), "ISO8859-1" )
        headerMap.put("content-disposition","attachment;filename="+ URLEncoder.encode( this.downFileName, CharsetsEnum.UTF_8.charset.toString()));
    }

    public void setReadFileName(String downFileName) throws UnsupportedEncodingException {
        this.downFileName = downFileName;
        // "attachment;filename="+ new String( "webmagic中文.txt".getBytes("gb2312"), "ISO8859-1" )
        //headerMap.put("content-disposition","attachment;filename="+ URLEncoder.encode( this.downFileName, CharsetsEnum.UTF_8.charset.toString()));
        headerMap.put(HttpHeaders.CACHE_CONTROL, "max-age=" + bytes.length);
    }

    public void setCache_control(String cache_control,String last_modified) {
        this.cache_control = cache_control;
        this.last_modified = last_modified;
        // HttpConstant.CACHE_CONTROL, "max-age=" + bytes.length
        headerMap.put(HttpHeadersConstant.CACHE_CONTROL, "max-age=" + cache_control);
        headerMap.put(HttpHeadersConstant.LAST_MODIFIED,last_modified);
    }



    public void setExpire(Long expire) {
        this.expire = expire;
        // (HttpConstant.EXPIRES, System.currentTimeMillis() + 1 * 1000);
        dateHeaderMap.put(HttpHeadersConstant.EXPIRES, System.currentTimeMillis() + expire);
    }

    public ServletVO() {
        headerMap = new HashMap<>();
        dateHeaderMap = new HashMap<>();
    }

    public Map<String,String> getHeaderMap() {
        return headerMap;
    }

    public Map<String,Long> getDateHeaderMap() {
        return dateHeaderMap;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}

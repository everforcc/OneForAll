package cn.cc.dawn.utils.file;

import cn.cc.dawn.utils.check.ObjectUtils;

import java.io.InputStream;

/**
 * 本地文件存储对象
 */
public class FileVO {

    /**
     * 忘了这个是干啥的
     */

    private String defalut = "/project/OneForAll/";
    /**
     * 哪个项目
     */
    private String project;
    /**
     * 数据类型
     */
    private String type;
    /**
     * 文件流
     */
    private InputStream inputStream;
    /**
     * 目录结构
     */
    private String[] dirs;
    /**
     * 文件名
     */
    private String fileName;

    public void setDirs(String... dir){
        if(ObjectUtils.isNull(dir)){
            return;
        }else {
            dirs = dir;
        }
    }

    public void save(){

    }

    public void saveWithProgress(){

    }

}

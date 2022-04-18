package cn.cc.dawn.utils.file;

public interface IFilePath {

    /**
     * 业务类以此为根目录在此基础上进行文件 持久化
     * 每类业务实现该方法，不要直接调用，方便后期统计业务
     * @return
     */
    static String pathRoot(){
        return IFilePath.pathRoot();
    }

}

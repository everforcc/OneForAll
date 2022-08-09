package cn.cc.dawn.business.bill.service;

/**
 * 加载 账单信息
 */
public interface IAliPayLoadService {

    /**
     * 从本地excel加载账单信息
     * 测试逻辑使用
     *
     * @param path 文件路径
     */
    void loadExcel(String path);

}

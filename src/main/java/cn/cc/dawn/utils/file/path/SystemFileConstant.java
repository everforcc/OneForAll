package cn.cc.dawn.utils.file.path;

/**
 * 系统文件的根目录，
 * 命名逻辑参考
 */
public class SystemFileConstant {

    /**
     * 处理掉全局的写死的代码
     * <br>
     * <font font-family="SimSun" color="red">每次开发的时候，必须定义好，该业务的文件分类，不入库，仅本地使用</face>
     */
    private static String winRoot = "E:\\filesystem\\project\\OneForAll";
    private static String linuxRoot = "\\tmp";

    /**
     * 二级目录说明
     */

    /**
     * 代码测试文件存放位置
     */
    private static String l_2_test = "\\test";
    /**
     * 爬虫后数据存放位置
     */
    private static String l_2_craw = "\\craw";
    /**
     * 存放网站的地址不带协议
     * www.baidu.com
     */
    private static String l_2_1_weburl = "\\weburl";

    /**
     * TODO 修改为配置文件
     * @return
     */
    protected static String pathRoot(){
        String os = System.getProperty("os.name");
        //System.out.println(os);
        if(os.toLowerCase().contains("windows")) {
            return winRoot;
        }else {
            return linuxRoot;
        }
    }

}

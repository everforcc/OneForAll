package cn.cc.dawn.business.craw.sp4u.constant;

public class Sp4uUrl {

    // 1
    public static String root = "http://spring4u.info";

    // 1.1
    public static String level_dxf = "http://spring4u.info/forumdisplay.php?fid=122";

    // 1.1.2
    public static String level_dxf_zkf = "http://spring4u.info/forumdisplay.php?fid=124";

    //public static String xp_chapter = "//div[@class='maintable']//table//tbody//tr[1]//td[2]//table//tbody//tr[0]//td[0]";
    // 还是要调整为编译一次，然后从根目录开始找数据
    // 但是现在这种也能用
    public static String xp_chapter_list = "//center//form//div"; // [2]
    public static String xp_chaptermsg_list = "//div[@class='maintable']//tbody//tr//td"; // [2]
    public static String xp_chaptermsg_url = "//table//tbody//tr//td//a/@href"; // [2]
    public static String xp_chaptermsg_name = "//table//tbody//tr//td//a/text()"; // [2]
    public static String xp_chaptermsg_issuetime = "//span/text()"; // [2]
}

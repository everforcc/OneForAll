package cn.cc.dawn.open.novel.util;

import cn.cc.dawn.utils.constant.LogConstant;
import cn.cc.dawn.utils.file.IFile;
import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class NovelCapterUtils {

    /**
     * 默认加载500个字
     */
    private static int defaultLoadLength = 500;
    //String regex = "第(.*?)章\\s+(.*?)([\\s\\S]+?)";
    private static String defaultMatchRegex = "(第(.*?)章\\s+(.*?))([\\s]+?)";
    private static int defaultMatchGroup = 1;
    private static int zero = 0;
    private static int totalLength = -1;

    // 章节信息
    private static List<String> stringList = new ArrayList<>();
    // 每个菜单对应的位置
    private static List<Integer> menuSize = new ArrayList<>();
    // 临时存储重复出现的章节名称的位置
    private static Map<String,Integer> menuMap = new HashMap<>();

    // 文件工具类
    private static IFile apacheiFile = new FileApacheUtils();

    /**
     * TODO 此处返回str可能会超长
     * @param path
     * @return
     */
    public static String fileToStr(String path){
        String str = "";
        try {
            str = apacheiFile.read(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 这块逻辑最好前端自行处理
     * 1. 正则匹配章节
     * 2. 匹配的过程中，每个放入List,然后再放入map，key为(先查后插)值，map为size, 位置信息再存一个list，和之前的list，一一对应
     * 3. 读取时，展示第一个list，然后再从第二个list拿位置
     */
    public static List<String> flow(String str){
        return flow(str,defaultMatchRegex,defaultMatchGroup);
    }
    public static List<String> flow(String str,String matchRegex){
        return flow(str,matchRegex,defaultMatchGroup);
    }
    public static List<String> flow(String str,String matchRegex,int matchGroup){
        Pattern pattern = Pattern.compile(matchRegex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String charter = matcher.group(matchGroup);
            stringList.add(charter);
            if(menuMap.containsKey(charter)){
                // 此处，可能会有重复的名称，如果有多次，每次记录最后一次出现的位置
                int tempLength = str.indexOf(charter,menuMap.get(charter) + 1);
                menuMap.put(charter,tempLength);
            }else {
                int tempLength = str.indexOf(charter);
                menuMap.put(charter,tempLength);
            }
            menuSize.add(menuMap.get(charter));
        }
        return stringList;
    }

    public static Map<String,Object> load(String str,String type,int index){
        Map<String,Object> map = new HashMap<>();

        if("menu".equals(type)&&(index<0||index>stringList.size()-1)){
            log.info(index + "： 没这页");
            return null;
        }

        if(-1 == totalLength){
            totalLength = str.length();
        }

        if("".equals("menu")){// 点击章节
            index = menuSize.get(index);
        }

        int endIndex = index+defaultLoadLength;
        endIndex = endIndex>totalLength?totalLength:endIndex;

        // 第二个参数为从第n个字符串开始
        String returnStr = str.substring(index,endIndex);

        map.put("content",returnStr);
        map.put("index",endIndex);
        return map;
    }

    public static void main(String[] args) {

        String str = "";
        try {
            str = fileToStr("F:\\娱乐\\03.文\\txt\\05.网文\\魔王三部曲\\魔王奶爸.txt");
            log.info(LogConstant.SPLIT + str.length());
            //log.info(LogConstant.SPLIT + str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 通用章节名正则
         */
        //String regex = "第(.*?)章\\s+(.*?)([\\s\\S]+?)";
        String regex = "(第(.*?)章\\s+(.*?))([\\s]+?)";
        defaultMatchGroup= 1;


        /**
         * 处理文本到缓存
         */
        flow(str,regex);

        // 测试输出缓存
        log.info(LogConstant.SPLIT + "stringList");
        stringList.forEach(System.out::println);
        log.info(LogConstant.SPLIT + "menuSize");
        menuSize.forEach(System.out::println);
        log.info(LogConstant.SPLIT + "menuMap");
        menuMap.forEach((s, integer) -> log.info(s + ": " + integer));

        // 查看内容
        load(str,"menu",700);

        // 之前章节返回的结尾数字
        //loadNext(str,1);
    }

}

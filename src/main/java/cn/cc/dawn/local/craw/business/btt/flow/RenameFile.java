package cn.cc.dawn.local.craw.business.btt.flow;

import cn.cc.dawn.utils.check.ObjectUtils;
import cn.cc.dawn.utils.regex.RegexUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RenameFile {

    /**
     *
     */
    public static String newFileNameFormat = "[%s]-[%s]-[%s]-[%s]%s%s";
    public static String regex_code = "[a-zA-Z]*-[0-9]*";
    public static String actor = "";
    public static String language = "[中文字幕]";
    public static String languageWithout = "中文字幕";
    public static String withOut_1 = "正在播放 ";
    public static String withOut_2 = "第1集-博天堂资源网-精品三级伦理资源中心";
    public static List<String> without = new ArrayList<>();
    static {
        without.add(withOut_1);
        without.add(withOut_2);
        without.add(language);
    }

    public static String sourePath = "F:\\娱乐\\btt400.com";
    public static String copyTempPath = "E:\\filesystem\\project\\OneForAll\\btt\\copytemp";
    public static String renamePath   = "E:\\filesystem\\project\\OneForAll\\btt\\renamePath";



    public static void recursionFile(String sourcePath, Consumer<File> consumer){
        File sourceFile = new File(sourcePath);
        File[] files = sourceFile.listFiles();

        if(ObjectUtils.nonNull(files)){
            int length = files.length;
            for(int i = 0; i<length; i++){
                consumer.accept(files[i]);
            }
        }
    }

    /**
     * 将 sourePath 的文件名copy到copyTempPath
     * 创建临时 空 文件
     * @param file
     */
    public static void mkNewFile(File file){
        String absolutePath = file.getAbsolutePath();
        if(file.isDirectory()){
            String newPathDir = absolutePath.replace(sourePath, copyTempPath);
            //System.out.println("newPath: " + newPath);
            File newPath = new File(newPathDir);
            newPath.mkdirs();
            // 进入下一级目录
            recursionFile(absolutePath, e -> mkNewFile(e));
        }else {
            String newFilePath = absolutePath.replace(sourePath, copyTempPath);
            // 模拟压缩后的文件命名
            File newFile = new File(newFilePath + ".7z");
            //newFile.
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("创建文件失败: " + e.toString());
            }
        }
    }

    /**
     * 修改
     * @param file
     */
    public static void renameFile(File file){
        String absolutePath = file.getAbsolutePath();
        if(file.isDirectory()){
            String newPathDir = absolutePath.replace(copyTempPath, renamePath);
            //System.out.println("newPath: " + newPath);
            File newPath = new File(newPathDir);
            newPath.mkdirs();
            // 进入下一级目录
            recursionFile(absolutePath, e -> renameFile(e));
        }else {

            String parentPath = file.getParent();
            parentPath = parentPath.replace(copyTempPath, renamePath);

            String oldFileName = file.getName();
            System.out.println("oldFileName 原文件名: " + oldFileName);

            for(String out:without){
                oldFileName = oldFileName.replace(out,"");
                System.out.println("oldFileName out: " + oldFileName);
            }

            /**
             * 提取出来的数据在这里面都排除
             */
            String code = RegexUtils.matcheStr(regex_code,oldFileName);
            oldFileName = oldFileName.replace(code,"");

            String type = oldFileName.replaceFirst("^.+\\.", ".");
            oldFileName = oldFileName.replace(type,"");

            String realType = oldFileName.replaceFirst("^.+\\.", ".");
            oldFileName = oldFileName.replace(realType,"");

            System.out.println("番号: " + code);
            System.out.println("演员: " + actor);
            System.out.println("中文字幕: " + language);


            String title = oldFileName.trim();
            System.out.println("标题: " + title);
            System.out.println("类型: " + type );

            String newFileName = String.format(newFileNameFormat,code,actor,languageWithout,title,realType,type);

            System.out.println("新文件名: " + newFileName);
//            String newFilePath = absolutePath.replace(copyTempPath, renamePath);
//            // 模拟压缩后的文件命名
            File newFile = new File(parentPath + File.separator + newFileName);
            //newFile.
            try {
                newFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("创建文件失败: " + e.toString());
            }
        }
    }

    private static void tFile(String path){
        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println(file.getName());
    }

    public static void main(String[] args) {
        String path = "E:\\filesystem\\project\\OneForAll\\btt\\copytemp\\IPX\\[IPX-593]-[二葉惠麻]-[中文字幕]-[我持續被新義父侵犯… ].ts";
        String path_old = "E:\\filesystem\\project\\OneForAll\\btt\\copytemp\\明里紬\\正在播放 [中文字幕]IPX-087 痴漢電車被盯上的女高中生明里紬-第1集-博天堂资源网-精品三级伦理资源中心.ts.7z";
        // tFile(path);
        // renameFile(new File(path_old));

        // 1. copy临时文件
       //  recursionFile(sourePath, e -> mkNewFile(e));

        // 2. 修改文件名
         recursionFile(copyTempPath, e -> renameFile(e));
    }

}

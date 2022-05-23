package cn.cc.dawn.utils.file.impl;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.utils.enums.BooleanEnum;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.FilePath;
import cn.cc.dawn.utils.file.IFile;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class FileApacheUtils implements IFile {

    //private static int bdMDSize = 1024 * 1024 * 4;
    private static long bdMDSize = 4;
    private static String temp = "/temp";

    @Override
    public void copy() {
        //FileUtils.copyFile();
    }

    @Override
    public void move() {

    }

    @Override
    public void rename() {

    }

    @Override
    public long fileSizeByte(String path) {
        return FileUtils.sizeOf(new File(path));
    }

    @Override
    public File[] fileSplit(String srcFilePath, Long fileSizeMB) {
        //FilePath.getSuffix()
        File file = new File(srcFilePath);
        String parentFile = file.getParent();
        return this.fileSplit(srcFilePath,parentFile + temp,fileSizeMB);
    }

    @Override
    public File[] fileSplit(String srcFilePath, String targetFilePath, Long fileSizeMB) {
        File srcFile = new File(srcFilePath);
        File targetFile = new File(targetFilePath);
        // 判断目标文件是否存在
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //计算： 拆多少份？  拆分后的文件名， partSize(MB)
        Long len = srcFile.length();
        Long size = fileSizeMB * (1024 * 1024);
        boolean isOdd=false;//是否：不能整除

        int fileCount = 0;
        //boolean
        if(len%size==0){
            fileCount=(int)(len/size);
        }else {
            fileCount=(int)(len/size)+1;
            isOdd=true;//不能整除
        }

        // 创建: fileCount个文件
        File[] files = new File[fileCount];
        try {
            // 使用：RandomAccessFile--->读全部文件---->写部分文件到： files
            @Cleanup
            RandomAccessFile rf = new RandomAccessFile(srcFile, "rw");
            for (int i = 1; i <= fileCount; i++) {
                files[i - 1] = new File(targetFile, srcFile.getName() + "_" + i);
                //byte[]---数组大小==内容大小， 最后一个文件特殊： 大小待定？
                @Cleanup
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(files[i - 1]));
                //容器： rf放内容到数组中
                byte[] bytes;
                if (i == fileCount && isOdd == true) {// 最后一个文件
                    bytes = new byte[new Long(len - (fileCount - 1) * size).intValue()];
                } else {
                    bytes = new byte[new Long(size).intValue()];
                }
                rf.read(bytes);
                out.write(bytes);

                log.info("fileName -->" + files[i - 1].getName() + ",\t fileSize: " + files[i - 1].length());
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("拆分 完成....");
        return files;
    }

    @Override
    public boolean isDir(String path) {
        File file = new File(path);
        return file.isDirectory();
    }

    /**
     * 将文件差分为列表
     * @param path
     * @return
     */
    @Override
    public BDFileVo block_list(String path, boolean deleteSlice) {
        BDFileVo bdFileVo = new BDFileVo();
        // 1. 文件拆分
        File[] files = this.fileSplit(path,bdMDSize);
        // 2. 将拆分后的文件处理为md5
        //DigestUtils.md5()
        List<String> fileMD5List = new ArrayList<>();
        for (File file : files) {

            try(InputStream inputStream = new FileInputStream(file)) {
                fileMD5List.add(DigestUtils.md5Hex(inputStream));
                // 生成之后直接删除文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 3. 删除拆分后的文件
        if(deleteSlice) {
            for (File file : files) {
                log.info("使用完成清除文件, {}", file.delete());
            }
        }else {
            bdFileVo.setFiles(files);
        }
        // 4. 异常也删除
        // 5. 特殊中断情况再说
        bdFileVo.setBlock_list(fileMD5List);

        return bdFileVo;
    }

    @Override
    public String md5(String path) {
        try(InputStream inputStream = new FileInputStream(new File(path))) {
            return DigestUtils.md5Hex(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppCode.A00100.toUserException(e);
        }
    }

    @Override
    public String slice_md5(String path, long lengthKB) {
        try(InputStream inputStream = new FileInputStream(path)) {
            return DigestUtils.md5Hex(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppCode.A00100.toUserException(e);
        }
    }

    @SneakyThrows
    public static List<String> md5block_list(String path, int size){
        byte[] bytes = FileUtils.readFileToByteArray(new File(path));
        /*System.arraycopy();

        DigestUtils.md5();*/
        return null;
    }

    @SneakyThrows
    @Override
    public void write(String path, String content){
        FileUtils.writeStringToFile(new File(path),content, CharsetsEnum.UTF_8.charset,BooleanEnum.TRUE.flag);
    }

    @SneakyThrows
    @Override
    public void write(String path, String fileName, String content) {
        FileUtils.writeStringToFile(new File(path + File.separator + fileName),content, CharsetsEnum.UTF_8.charset,BooleanEnum.TRUE.flag);
    }

    @SneakyThrows
    @Override
    public void write(String path, byte[] bytes){
        FileUtils.writeByteArrayToFile(new File(path),bytes,BooleanEnum.TRUE.flag);
    }

    @SneakyThrows
    @Override
    public void write(String path, String fileName, byte[] bytes) {
        FileUtils.writeByteArrayToFile(new File(path + File.separator + fileName),bytes,BooleanEnum.TRUE.flag);
    }

    @SneakyThrows
    @Override
    public String readTXT(String path){
        return FileUtils.readFileToString(new File(path),CharsetsEnum.UTF_8.charset);
    }

    @SneakyThrows
    @Override
    public byte[] readBytes(String path) {
        return FileUtils.readFileToByteArray(new File(path));
    }

    @Override
    public boolean exist(String path) {
        return new File(path).exists();
    }
}

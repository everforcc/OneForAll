/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 10:11
 */

package cn.cc.dawn.common.bddisk.upload.vo;

import cn.cc.dawn.utils.exception.AppCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BDFileVo {

    /**
     * 本地文件位置
     */
    private String localPath;
    /**
     * 目标文件位置
     */
    private String targetPath;
    private long size;

    private List<String> block_list;

    private File[] files;

    private String content_md5;
    /**
     * 前256k
     */
    private String slice_md5;

    private String uploadid;

    private boolean uploadResult;

    public boolean getUploadResult() {
        return uploadResult;
    }

    public void setUploadResult(boolean uploadResult) {
        this.uploadResult = uploadResult;
        if(uploadResult){
            for(File file:files){
                log.info("文件上传成功,正在删除文件分片 {}",file.delete());
            }
        }
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
        //AppCode.A00111.assertHasTrue(files.length<);
    }
}

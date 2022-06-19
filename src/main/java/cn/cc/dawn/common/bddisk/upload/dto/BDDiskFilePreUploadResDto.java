/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 09:49
 */

package cn.cc.dawn.common.bddisk.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * bddisk 文件预上传 返回值
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BDDiskFilePreUploadResDto {

    /**
     * 错误码
     */
    private String errno;
    /**
     * 文件的绝对路径
     */
    private String path;
    /**
     * 上传唯一ID标识此上传任务
     */
    private String uploadid;
    /**
     * 返回类型，1 文件在云端不存在，2 文件在云端已存在
     * 如果返回2，那么这次上传任务结束
     */
    private String return_type;
    /**
     * 需要上传的分片序号列表，索引从0开始
     */
    private String block_list;

}

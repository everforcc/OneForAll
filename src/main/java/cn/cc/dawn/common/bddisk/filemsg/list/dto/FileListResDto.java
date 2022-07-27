/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-27 22:05
 * Copyright
 */

package cn.cc.dawn.common.bddisk.filemsg.list.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.excle.converter.TimeStampToStringConverter;
import com.github.crab2died.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * <a href="https://pan.baidu.com/union/doc/nksg0sat9">...</a>
 * 请求文件列表接口返回
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileListResDto extends CommonFiledDto {

    /**
     * 文件在云端的唯一标识ID
     */
    @ExcelField(title = "标识ID", order = 1)
    private String fs_id;

    /**
     * 文件的绝对路径
     */
    @ExcelField(title = "文件的绝对路径", order = 2)
    private String path;

    /**
     * 文件名称
     */
    @ExcelField(title = "文件名称", order = 3)
    private String server_filename;

    /**
     * 文件大小，单位B
     */
    @ExcelField(title = "文件大小B", order = 4)
    private long size;

    /**
     * 文件在服务器修改时间
     */
    @ExcelField(title = "文件在服务器修改时间", order = 5, writeConverter = TimeStampToStringConverter.class)
    private long server_mtime;

    /**
     * 文件在服务器创建时间
     */
    @ExcelField(title = "文件在服务器创建时间", order = 6, writeConverter = TimeStampToStringConverter.class)
    private long server_ctime;

    /**
     * 文件在客户端修改时间
     */
    @ExcelField(title = "文件在客户端修改时间", order = 7, writeConverter = TimeStampToStringConverter.class)
    private long local_mtime;

    /**
     * 文件在客户端创建时间
     */
    @ExcelField(title = "文件在客户端创建时间", order = 8, writeConverter = TimeStampToStringConverter.class)
    private long local_ctime;

    /**
     * 是否为目录，0 文件、1 目录
     */
    @ExcelField(title = "是否为目录", order = 9)
    private long isdir;

    /**
     * 文件类型，1 视频、2 音频、3 图片、4 文档、5 应用、6 其他、7 种子
     */
    @ExcelField(title = "文件类型", order = 10)
    private long category;

    /**
     * 云端哈希（非文件真实MD5），只有是文件类型时，该字段才存在
     */
    @ExcelField(title = "云端哈希", order = 11)
    private String md5;

    /**
     * 该目录是否存在子目录，只有请求参数web=1且该条目为目录时，该字段才存在， 0为存在， 1为不存在
     */
    @ExcelField(title = "该目录是否存在子目录", order = 12)
    private String dir_empty;

    /**
     * 只有请求参数web=1且该条目分类为图片时，该字段才存在，包含三个尺寸的缩略图URL
     */
    @ExcelField(title = "包含三个尺寸的缩略图URL", order = 13)
    private List<String> thumbs;

}

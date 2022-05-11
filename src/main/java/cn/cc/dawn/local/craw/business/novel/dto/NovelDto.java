package cn.cc.dawn.local.craw.business.novel.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovelDto extends CommonFiledDto {

    /**
     * 1. 网站信息
     * 2. 小说名
     * 3. 章节名
     * 章节链接
     * 4. 作者
     * 作者链接
     * 1.
     *
     *
     */



    private String novelname;
    private String chapter;
    private String url;
    private String params;
    private String author;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp issuetime;
    private String content;

}

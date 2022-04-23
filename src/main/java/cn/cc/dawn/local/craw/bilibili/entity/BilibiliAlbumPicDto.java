package cn.cc.dawn.local.craw.bilibili.entity;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BilibiliAlbumPicDto extends CommonFiledDto {

    private String doc_id ;

    private String img_width ;
    private String img_height ;
    private String img_size ;
    private String img_src ;

}

package cn.cc.dawn.local.craw.bilibili.entity;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BilibiliAlbumDto extends CommonFiledDto {

    private String view;
    private String like;
    private String count;
    private String description;
    private String ctime;
    private String poster_uid;
    private String title;
    private String doc_id;

    private String album;

    private List<BilibiliAlbumPicDto> pictures;
}

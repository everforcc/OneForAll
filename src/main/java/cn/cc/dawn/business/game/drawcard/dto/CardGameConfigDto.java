package cn.cc.dawn.business.game.drawcard.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// cc_game_user_drawcardconfig
public class CardGameConfigDto extends CommonFiledDto {

    /**
     * 描述
     */
    private String description;
    /**
     * 公告
     */
    private String notice;
    /**
     * 游戏名
     */
    private String gameName;
    /**
     * 基础概率
     */
    private Double baseProbability;



}

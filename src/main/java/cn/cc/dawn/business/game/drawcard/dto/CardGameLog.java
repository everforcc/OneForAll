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
public class CardGameLog extends CommonFiledDto {

    /**
     * 游戏id
     */
    private String gameid;
    /**
     * 用户id
     */
    private int userid;
    /**
     * 最终概率
     */
    private Double userProbability;

    private Double gameProbability;

    private String result;

}

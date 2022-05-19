/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-17 20:28
 * Copyright
 */

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

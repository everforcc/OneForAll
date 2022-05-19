/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-17 20:23
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
     * TODO 校验必须小于1
     */
    private Double baseProbability;



}

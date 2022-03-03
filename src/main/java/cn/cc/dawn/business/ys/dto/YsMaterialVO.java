package cn.cc.dawn.business.ys.dto;

import cn.cc.dawn.common.dto.CommonFiledDto;
import cn.cc.dawn.common.enums.StatusEnum;

public class YsMaterialVO extends CommonFiledDto {

    /**
     * 名称
     */
    private String name;
    /**
     * null 公共
     * 0 突破
     * 1 技能
     */
    private StatusEnum type;
    /**
     * 星级，武器专用
     */
    private String start;
    /**
     * 材料
     */
    private String materia;
    /**
     * 摩拉
     */
    private String mola;

}

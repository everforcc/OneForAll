package cn.cc.dawn.open.menu.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.Data;

@Data
public class MenuDto extends CommonFiledDto {

    /**
     * 系统内的目录结构,
     *
     * 数据就挂在uuid下
     */

    /**
     * 级别，几级菜单
     */
    private int level;

    /**
     * 目录名
     */
    private String name;

    /**
     * 父节点id
     */
    private int parentid;

    /**
     * uuid
     */
    private String parentuuid;

    /**
     * TODO 是否有查询权限
     */
    private String auth;

    /**
     * 版本更新逻辑处理，不一定需要
     */
    private String version;

}

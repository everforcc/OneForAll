package cn.cc.dawn.open.menu.vo;

import cn.cc.dawn.open.menu.dto.MenuDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuVO {

    /**
     * 初始化系统menu
     */

    /**
     * 当前目录级别
     * 顶级目录为0，从下级开始计算
     */
    private int level;

    /**
     * 当前目录信息
     */
    private MenuDto menuDto;

    /**
     * 下一级目录信息
     */
    private List<MenuVO> childMenuList;

}

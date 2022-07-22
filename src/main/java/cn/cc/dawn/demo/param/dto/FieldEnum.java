package cn.cc.dawn.demo.param.dto;

import cn.cc.dawn.utils.enums.BaseEnum;

import java.util.Objects;

/**
 * @author guokailong 2021-09-07
 */
public enum FieldEnum implements BaseEnum<Integer> {

    /**
     * 无效, 需要vip不能用
     */
    UNEFFECT,
    /**
     * 有效, 不需要vip可用
     */
    EFFECT;


    FieldEnum() {

    }


    public static void main(String[] args) {
        System.out.println(FieldEnum.UNEFFECT);
        System.out.println(FieldEnum.UNEFFECT.name());
        System.out.println(FieldEnum.UNEFFECT.ordinal());
        System.out.println(FieldEnum.UNEFFECT.getCode());
        FieldEnum[] fieldEnums = FieldEnum.values();
        FieldEnum fieldEnum = fieldEnums[2];
    }

    /**
     * 用坐标取出枚举
     *
     * @param index 坐标
     * @return 枚举值
     */
    public static FieldEnum getEnum(Integer index) {
        FieldEnum[] fieldEnums = FieldEnum.values();
        int length = fieldEnums.length;
        if (Objects.isNull(index) || index < 0 || index >= length) {
            return null;
        }
        return fieldEnums[index];
    }

    @Override
    public Integer getCode() {
        return this.ordinal();
    }
}

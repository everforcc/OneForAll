package cn.cc.dawn.business.ys.dto;

import cn.cc.dawn.common.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author everforcc 2021-09-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YsCardVO extends CommonFiledDto {

    /**
     * 原字段id冲突，替换为cardid
     */
    private String cardid;
    private String uid;
    private String gachaType;
    private String itemId;
    private String count;
    private String time;
    private String name;
    private String lang;
    private String itemType;
    private String rankType;

    private String cardPool;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YsCardVO ysCardVO = (YsCardVO) o;
        return uid.equals(ysCardVO.uid) && id.equals(ysCardVO.id);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(uid, cardid);
    }

}

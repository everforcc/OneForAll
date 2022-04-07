package cn.cc.dawn.business.ys.dao;

import cn.cc.dawn.business.ys.dto.YsCardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

//@Mapper
@Repository
public interface YsCardDaoMapper {

    YsCardVO selectByCardID(BigInteger cardid);

    List<YsCardVO> selectByUid(String uid,List<String> typeList);
    //List<YsCardVO> selectByUid(String uid,String type);

    int insert(YsCardVO ysCardVO);

}

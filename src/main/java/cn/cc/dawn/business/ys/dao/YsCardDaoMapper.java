package cn.cc.dawn.business.ys.dao;

import cn.cc.dawn.business.ys.dto.YsCardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface YsCardDaoMapper {

    YsCardVO selectByCardID(String cardid);

    List<YsCardVO> selectByUid(String uid);

    int insert(YsCardVO ysCardVO);

}

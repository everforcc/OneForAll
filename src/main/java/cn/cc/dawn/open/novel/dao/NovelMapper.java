package cn.cc.dawn.open.novel.dao;

import cn.cc.dawn.open.novel.dto.NovelDto;

import java.util.List;

public interface NovelMapper {

    String existUrl(String sourceurl);

    NovelDto selectSourceurl(String sourceurl);

    List<NovelDto> selectPage(int pn,int size);

    int insert(NovelDto novelDto);

}

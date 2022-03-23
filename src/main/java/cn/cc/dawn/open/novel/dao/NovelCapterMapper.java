package cn.cc.dawn.open.novel.dao;

import cn.cc.dawn.open.novel.dto.NovelCapterDto;

import java.util.List;

public interface NovelCapterMapper {

    /**
     * 随后在全局加入uuid
     */

    String existUrl(String url);

    NovelCapterDto selectUrl(String url);

    List<NovelCapterDto> selectPage(int parentid,int pn, int size);

    String selectContent(int id,String capterName);

    int insert(NovelCapterDto novelCapterDto);

}

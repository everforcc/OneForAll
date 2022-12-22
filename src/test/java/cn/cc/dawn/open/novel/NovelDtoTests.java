/**
 * @Description
 * @Author everforcc
 * @Date 2022-11-16 18:07
 * Copyright
 */

package cn.cc.dawn.open.novel;

import cn.cc.dawn.open.novel.dto.NovelDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovelDtoTests {

    public static void main(String[] args) {
        List<NovelDto> novelDtoList = new ArrayList<>();
        NovelDto novelDto = new NovelDto();

        novelDto.setSourcename("a");
        novelDto.setSourceurl("a");
        novelDtoList.add(novelDto);

        novelDto = new NovelDto();
        novelDto.setSourcename("a");
        novelDto.setSourceurl("a");
        novelDtoList.add(novelDto);

        novelDto = new NovelDto();
        novelDto.setSourcename("b");
        novelDto.setSourceurl("b");
        novelDtoList.add(novelDto);

        novelDto = new NovelDto();
        novelDto.setSourcename("c");
        novelDto.setSourceurl("c");
        novelDtoList.add(novelDto);

        System.out.println("novelDtoList: " + novelDtoList.size());

        // set测试
        Set<NovelDto> novelDtoSet = new HashSet<>();
        for (NovelDto n : novelDtoList) {
            boolean flag = novelDtoSet.add(n);
            System.out.println(flag);
        }
        System.out.println("novelDtoSet: "+novelDtoSet.size());

    }

}

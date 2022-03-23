package cn.cc.dawn.open.novel;

import cn.cc.dawn.open.novel.util.NovelCapterUtils;
import cn.cc.dawn.utils.constant.LogConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@Slf4j
public class NovelCapterUtilsT {


    @Test
    void contextLoads() {

        String filep_1 = "F:\\娱乐\\03.文\\txt\\05.网文\\魔王三部曲\\勇者之师.txt";
        String filep_2 = "F:\\娱乐\\03.文\\txt\\05.网文\\魔王三部曲\\魔王奶爸.txt";

        String str = NovelCapterUtils.fileToStr(filep_2);

        /**
         * 可以自定义正则和组
         */
        //String matchRegex= "";

        List<String> chapter =  NovelCapterUtils.flow(str);
        chapter.forEach(log::info);

        log.info(LogConstant.SPLIT);

        /**
         * 选择章节
         */
        Map<String,Object> map = NovelCapterUtils.load(str,"menu",1);

        log.info(LogConstant.SPLIT + map.get("content"));
        log.info(LogConstant.SPLIT + map.get("index"));

        log.info(LogConstant.SPLIT + "下一页");

        /**
         * 模拟翻页
         */
        map = NovelCapterUtils.load(str,"next",(Integer) map.get("index"));

        log.info(LogConstant.SPLIT + map.get("content"));
        log.info(LogConstant.SPLIT + map.get("index"));

        log.info(LogConstant.SPLIT + "下一页");

        map = NovelCapterUtils.load(str,"next",(Integer) map.get("index"));

        log.info(LogConstant.SPLIT + map.get("content"));
        log.info(LogConstant.SPLIT + map.get("index"));

        log.info(LogConstant.SPLIT + "下一页");

        map = NovelCapterUtils.load(str,"next",(Integer) map.get("index"));

        log.info(LogConstant.SPLIT + map.get("content"));
        log.info(LogConstant.SPLIT + map.get("index"));

    }

}

package cn.cc.dawn.local.craw.business.bilibili.service;

import cn.cc.dawn.local.craw.business.bilibili.flow.Bilibili_Album;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Service
public class BilAlbumService {

    public String upAllalbum(@NotNull(message = "uid不能为空") String uid){
        // 添加到定时任务中去
        /**
         * 28380168 yane
         * 7198052 鸦居
         * 3403527 蒋七七ChiChan
         * 51588985 是一只九龄
         */
        Bilibili_Album bilibili_album = new Bilibili_Album();
        return bilibili_album.requestFlow(uid);
    }

}

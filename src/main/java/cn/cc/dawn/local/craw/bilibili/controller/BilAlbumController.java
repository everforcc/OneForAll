package cn.cc.dawn.local.craw.bilibili.controller;

import cn.cc.dawn.local.craw.bilibili.flow.Bilibili_Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RequestMapping("/local/craw/bilalbum")
@RestController
public class BilAlbumController {

    /**
     * 1. 单个id
     * 2. 某个用户的数据
     */

    public void albumid(){
        return;
    }

    @GetMapping("/allalbum")
    public String allalbum(){
        // 添加到定时任务中去
        /**
         * 28380168 yane
         * 7198052 鸦居
         * 3403527 蒋七七ChiChan
         */
        Bilibili_Album bilibili_album = new Bilibili_Album("3403527");
        try {
            bilibili_album.requestFlow("3403527");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Autowired
    ExecutorService multiThread;

    @GetMapping("/existsTask")
    public String existsTask(){

        multiThread.execute(new Runnable() {
            @Override
            public void run() {
                log.info("执行一下就返回");
            }
        });

        if(multiThread.isTerminated()) {
            System.out.println("执行完毕");
            return "0";
        }else {
            //System.out.println("正在执行 >>> ");
            int threadCount = ((ThreadPoolExecutor)multiThread).getActiveCount();
            return threadCount + " ]";
        }
    }


}

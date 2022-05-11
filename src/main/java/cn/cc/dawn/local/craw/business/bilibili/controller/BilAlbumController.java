package cn.cc.dawn.local.craw.business.bilibili.controller;

import cn.cc.dawn.local.craw.business.bilibili.service.BilAlbumService;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RequestMapping("/local/craw/bilalbum")
@RestController
public class BilAlbumController {


    @Autowired
    ExecutorService multiThread;

    @Autowired
    BilAlbumService bilAlbumService;

    /**
     * 1. 单个id
     * 2. 某个用户的数据
     */

    public void albumid(){
        return;
    }

    @GetMapping("/allalbum/{uid}")
    public ResultE<String> allalbum(@PathVariable String uid){
        // 添加到定时任务中去
        /**
         * 28380168 yane
         * 7198052 鸦居
         * 3403527 蒋七七ChiChan
         * 51588985 是一只九龄
         */
        return new ResultE<String>().execute(e ->
                e.setSuccess(bilAlbumService.upAllalbum(uid))
        );
    }

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

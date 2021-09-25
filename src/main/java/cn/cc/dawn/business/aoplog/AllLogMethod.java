package cn.cc.dawn.business.aoplog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guokailong 2021-09-25
 */
@RestController
public class AllLogMethod {

    @GetMapping("/taop1/{id}")
    public void tAOPLog1(@PathVariable("id")int id){
        System.out.println("tAOPLog1(AllLogMethod)");
    }

    @GetMapping("/taop2/{id}")
    public int tAOPLog(@PathVariable("id")int id){
        System.out.println("tAOPLog(AllLogMethod)");
        return id;
    }

}

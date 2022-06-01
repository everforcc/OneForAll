package cn.cc.dawn.demo.returne.controller;

import cn.cc.dawn.demo.returne.service.ReturnEexceptionService;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/demo/returne")
@RestController
public class ReturnEController {

    @Autowired
    private ReturnEexceptionService returnEexceptionService;

    /**
     * 返回数据
     * @return
     */
    @GetMapping("/testExceptionReturn")
    public ResultE<String> testExceptionReturn(){
        return new ResultE<String>().execute(e ->{
            e.setSuccess(returnEexceptionService.userException());
        });
    }

    /**
     * 返回空
     * @return
     */
    @GetMapping("/testReturnVoid")
    public ResultE<Void> testReturnVoid(){
        return new ResultE<Void>().call(() ->{
            log.info("返回值为空");
            returnEexceptionService.userException();
        });
    }

}

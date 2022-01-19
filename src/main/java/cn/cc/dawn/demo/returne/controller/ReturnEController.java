package cn.cc.dawn.demo.returne.controller;

import cn.cc.dawn.demo.returne.service.ReturnEexceptionService;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/returne")
@RestController
public class ReturnEController {

    @Autowired
    private ReturnEexceptionService returnEexceptionService;

    @GetMapping("/exception")
    public ResultE<String> feignClient(){
        return new ResultE<String>().execute(e ->{
            e.setSuccess(returnEexceptionService.userException());
        });
    }

}

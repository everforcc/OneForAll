package cn.cc.dawn.demo.init.yml.controller;

import cn.cc.dawn.demo.init.yml.dto.ExpressFalseBean;
import cn.cc.dawn.demo.init.yml.dto.ExpressTrueBean;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat;

@RequestMapping(value = "/demo/init/expression")
@RestController
@Slf4j
public class InitExpressController {

    @Autowired(required = false)
    private ExpressTrueBean expressTrueBean;
    @Autowired(required = false)
    private ExpressFalseBean expressFalseBean;

    @GetMapping(path = "/show")
    public ResultE<JSONObject> show() {
        return new ResultE<JSONObject>().execute(e -> {
            log.info("进入方法: show()");
            Map<String, Object> result = new HashMap<>(4);
            result.put("expressTrueBean", expressTrueBean == null ? "null ==> false" : expressTrueBean.getName());
            result.put("expressFalseBean", expressFalseBean == null ? "null ==> true" : expressFalseBean.getName());
            result.put("expressTrueBean_1", expressTrueBean.getName());
            result.put("expressFalseBean_1", expressFalseBean.getName());
            //JSONObject jsonObject = JSON.parseObject(result,Map.class);
            JSONObject jsonObject = new JSONObject(result);
            log.info(JSON.toJSONString(jsonObject, PrettyFormat));
            e.setSuccess(jsonObject);
        });
    }

}

package cn.cc.dawn.demo.returne.service;

import cn.cc.dawn.utils.exception.AppCode;
import org.springframework.stereotype.Service;

@Service
public class ReturnEexceptionService {

    public String userException(){
        //throw new RuntimeException("自定义异常");
        AppCode.A00101.assertHasTrue(1 == 0);
        throw AppCode.A00101.toUserException();
        //return "1";
    }

}

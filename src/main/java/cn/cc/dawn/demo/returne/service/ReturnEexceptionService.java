package cn.cc.dawn.demo.returne.service;

import cn.cc.dawn.utils.exception.AppCode;
import org.springframework.stereotype.Service;

@Service
public class ReturnEexceptionService {

    public String userException(){
        //throw new RuntimeException("自定义异常");
        AppCode.A01000.assertHasTrue(1 == 0);
        throw AppCode.A01000.toUserException();
        //return "1";
    }

}

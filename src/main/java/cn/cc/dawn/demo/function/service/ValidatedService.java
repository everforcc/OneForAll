package cn.cc.dawn.demo.function.service;

import cn.cc.dawn.demo.function.dto.ValidatedDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
@Transactional
//@RequiredArgsConstructor
public class ValidatedService {

    public String testPattern(@Valid @NotNull(message = "【fileSystemDto】不能为null") final ValidatedDto fileSystemDto){
        System.out.println(fileSystemDto.getName());
        return "";
    }

}

package cn.cc.dawn.common.file.service;

import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

public interface IServletService {

    void down(HttpServletResponse response, @PathVariable String uuname);

    void read(HttpServletResponse response, @PathVariable String uuname);

}

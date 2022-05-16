package cn.cc.dawn.demo.mail.service;

import cn.cc.dawn.demo.mail.dto.MailDto;

public interface IMailService {

    boolean send(MailDto mailDto);

}

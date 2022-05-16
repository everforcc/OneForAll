/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-16 16:53
 * Copyright
 */

package cn.cc.dawn.demo.mail.service.impl;

import cn.cc.dawn.config.component.MailThymeleafComponent;
import cn.cc.dawn.demo.mail.dto.MailDto;
import cn.cc.dawn.demo.mail.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mailServiceThymeleafImpl")
public class MailServiceThymeleafImpl implements IMailService {

    @Autowired
    MailThymeleafComponent mailTemplateEngine;

    @Override
    public boolean send(MailDto mailDto) {
        return mailTemplateEngine.send(mailDto);
    }

}

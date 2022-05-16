/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-16 17:06
 * Copyright
 */

package cn.cc.dawn.demo.mail.service.impl;

import cn.cc.dawn.config.component.MailFreemarkerComponent;
import cn.cc.dawn.demo.mail.dto.MailDto;
import cn.cc.dawn.demo.mail.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mailServiceFreemarkerImpl")
public class MailServiceFreemarkerImpl  implements IMailService {

    @Autowired
    MailFreemarkerComponent mailFreemarkerComponent;

    @Override
    public boolean send(MailDto mailDto) {
        return mailFreemarkerComponent.sendFreemarkerMail(mailDto);
    }
}

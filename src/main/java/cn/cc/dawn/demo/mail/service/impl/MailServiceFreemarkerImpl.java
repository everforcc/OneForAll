/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-16 17:06
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

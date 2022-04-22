package cn.cc.dawn.demo.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 邮件相关对象
 * [代码参考](http://springboot.javaboy.org/2019/0717/springboot-mail)
 * [配置文件参考](https://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=371)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {


    /**
     * 标题
     */
    private String subject;
    /**
     * 标题
     */
    private String from;
    /**
     * 发送给给谁
     */
    private String toMail;
    /**
     * 抄送
     */
    private String[] ccMail;
    /**
     * 秘送
     */
    private String[] bccMail;
    /**
     * 发送时间
     */
    private Date sendDate;
    /**
     * 发送内容
     */
    //private String content;

    /**
     * 行内图片
     * TODO 由于模板的问题，目前支持一个就够了，需要多个再说
     */
    private String inlinePic;

    /**
     * 附件， 先给个文件url
     * TODO 格式待定
     */
    private List<String> attachmentURL;

    private Map<String,String> context;

}

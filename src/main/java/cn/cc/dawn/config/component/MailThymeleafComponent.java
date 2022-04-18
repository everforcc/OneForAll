package cn.cc.dawn.config.component;

import cn.cc.dawn.common.sys.dto.MailDto;
import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.file.IFilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * Thymeleaf
 */
@Component
public class MailThymeleafComponent {

    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    JavaMailSender javaMailSender;

    public void send(MailDto mailDto)throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // true表示是否有附件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject(mailDto.getSubject());
        helper.setFrom(mailDto.getFrom());
        helper.setTo(mailDto.getToMail());
        if(Objects.nonNull(mailDto.getCcMail())){
            helper.setCc(mailDto.getCcMail());
        }
        if(Objects.nonNull(mailDto.getBccMail())) {
            helper.setBcc(mailDto.getBccMail());
        }
        if(Objects.nonNull(mailDto.getSendDate())) {
            helper.setSentDate(mailDto.getSendDate());
        }else {
            helper.setSentDate(new Date());
        }
        if(Objects.nonNull(mailDto.getInlinePic())){
            helper.addInline("",new FileUrlResource(new URL(mailDto.getInlinePic())));
        }
        if(Objects.nonNull(mailDto.getAttachmentURL())){
            int size = mailDto.getAttachmentURL().size();
            for(int i=0;i<size;i++){
                String url = mailDto.getAttachmentURL().get(i);
                helper.addAttachment(StringUtils.urlSubFileName(url),new FileUrlResource(new URL(url)));
            }
        }

        Context context = new Context();
        // 设置模板中的变量
        Map<String,String> map = mailDto.getContext();

        if(map.containsKey("username")) {
            context.setVariable("username", map.get("username"));
        }
        if(map.containsKey("num")) {
            context.setVariable("num",map.get("num"));
        }
        if(map.containsKey("salary")) {
            context.setVariable("salary", map.get("salary"));
        }
        // 第一个参数为模板的名称
        String process = templateEngine.process("mail.html", context);
        // 第二个参数true表示这是一个html文本
        helper.setText(process,true);

        javaMailSender.send(mimeMessage);
    }


    public void sendThymeleafMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // true表示是否有附件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件-Thymeleaf");
        helper.setFrom("718497737@qq.com");
        helper.setTo(new String[]{"2446678230@qq.com","2212355809@qq.com"});
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        // 这里引入的是Template的Context
        Context context = new Context();
        // 设置模板中的变量
        context.setVariable("username", "javaboy");
        context.setVariable("num","000001");
        context.setVariable("salary", "99999");
        // 第一个参数为模板的名称
        String process = templateEngine.process("mail.html", context);
        // 第二个参数true表示这是一个html文本
        helper.setText(process,true);
        helper.addInline("p01", new FileUrlResource(new URL("https://gitee.com/MyYukino/media/raw/master/picture/面包.jpg")));
        /**
         * 四种实现示例两种，其他的需要再说
         */
        helper.addAttachment("磁盘.txt",new File(IFilePath.pathRoot() + "/磁盘.txt"));
        /**
         * 实现接口 InputStreamSource
         */
        helper.addAttachment("面包.jpg", new FileUrlResource(new URL("https://gitee.com/MyYukino/media/raw/master/picture/面包.jpg")));


        javaMailSender.send(mimeMessage);
    }

}

package cn.cc.dawn.config.component;

import cn.cc.dawn.demo.mail.dto.MailDto;
import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
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

    public boolean send(MailDto mailDto){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            // true表示是否有附件
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(mailDto.getSubject());
            helper.setFrom(mailDto.getFrom());
            // 都支持送多个，支持数组
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
                helper.addInline("p01",new FileUrlResource(new URL(mailDto.getInlinePic())));
            }
            if(Objects.nonNull(mailDto.getAttachmentURL())){
                int size = mailDto.getAttachmentURL().size();
                for(int i=0;i<size;i++){
                    String url = mailDto.getAttachmentURL().get(i);
                    /**
                     * 这里有不同构造，可以根据实际业务选一种
                     */
                    helper.addAttachment(RStringUtils.urlSubFileName(url),new FileUrlResource(new URL(url)));
                    /**
                     * 四种实现示例两种，其他的需要再说
                     */
                    //helper.addAttachment("磁盘.txt", FilePath.build().ofFileName("磁盘.txt").file());
                    /**
                     * 实现接口 InputStreamSource
                     */
                    //helper.addAttachment("面包.jpg", new FileUrlResource(new URL("https://gitee.com/MyYukino/media/raw/master/picture/面包.jpg")));
                }
            }

            Context context = new Context();
            // 设置模板中的变量,怎么设计成动态的
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
            String process = templateEngine.process("salary.html", context);
            // 第二个参数true表示这是一个html文本
            helper.setText(process,true);
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
            throw AppCode.A00105.toUserException(e);
        }
        return true;
    }

}

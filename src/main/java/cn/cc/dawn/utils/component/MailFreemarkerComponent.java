package cn.cc.dawn.utils.component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Freemarker
 */
@Component
public class MailFreemarkerComponent {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendFreemarkerMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件-Freemarker");
        helper.setFrom("718497737@qq.com");
        helper.setTo("2212355809@qq.com");
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        //构建 Freemarker 的基本配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // 配置模板位置
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "templates");
        //加载模板
        Template template = configuration.getTemplate("mail.ftl");

        Map<String,String> map = new HashMap<>();
        map.put("username","javaboy");
        map.put("num","123");
        map.put("salary","99999");

        StringWriter out = new StringWriter();
        //模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可
        template.process(map, out);
        helper.setText(out.toString(),true);
        javaMailSender.send(mimeMessage);
    }

}

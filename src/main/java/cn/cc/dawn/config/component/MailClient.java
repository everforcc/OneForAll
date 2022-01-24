//package cn.cc.dawn.config.component;
//
//import lombok.extern.slf4j.Slf4j;
//<!--低版本mail-->
//<dependency>
//<groupId>javax</groupId>
//<artifactId>javaee-api</artifactId>
//<version>7.0</version>
//</dependency>
//import javax.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class MailClient {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${spring.mail.username}")
//    private String from;
//
//
//    public void sendMail(String to, String subject, String context){
//        try{
//            //构建邮件发送对象
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setFrom(from);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(context, true);//支持html格式
//            //发送邮件
//            mailSender.send(helper.getMimeMessage());
//        }catch (Exception e){
//            logger.error("发送邮件失败："+e.getMessage());
//        }
//
//    }
//
//}
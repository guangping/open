package com.varela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by 51offer on 2016/3/4.
 */
@ContextConfiguration("classpath:applicationContext-mail-test.xml")
public class MailTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.username}")
    private String mail;

    private String recMail = "guangping.m@51offer.com";

    @Test
    public void send() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);//发送人
        mailMessage.setSubject("测试邮件!");//主题
        mailMessage.setTo(recMail);//接收人
        String msg = "测试";
        mailMessage.setText(msg);//邮件内容

        mailSender.send(mailMessage);
        System.out.println("对象:" + mailSender);
    }

    /**
     * 发送html的邮件
     */
    @Test
    public void sendHtml() throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
                "UTF-8");
        messageHelper.setTo(recMail);
        messageHelper.setFrom(mail);//发送人
        messageHelper.setSubject("测试HTML邮件!");//主题
        String msg = html();
        messageHelper.setText(msg, true);//邮件内容

        mailSender.send(mailMessage);
        System.out.println("对象:" + mailSender);
    }

    @Test
    public void sendFile()throws MessagingException{
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,
                "UTF-8");
        messageHelper.setTo(recMail);
        messageHelper.setFrom(mail);//发送人
        messageHelper.setSubject("测试HTML邮件!");//主题
        //附件
        messageHelper.addAttachment("icon.xml",new File("F:\\maven\\settings.xml"));
        String msg = html();
        messageHelper.setText(msg, true);//邮件内容

        mailSender.send(mailMessage);
        System.out.println("对象:" + mailSender);
    }

    private String html() {
        String url = "http://baidu.com";
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head>");
        builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
        builder.append("</head><body>");
        builder.append("您好，张三：<br />");
        builder.append("\t系统已为您重置了RUI密码，账户信息如下：<br />");
        builder.append("用户账户：zhangsan<br />用户密码：123456<br />您可以点击以下链接登录RUI：");
        builder.append("<a href=\"");
        builder.append(url);
        builder.append("\">");
        builder.append(url);
        builder.append("</a>");
        builder.append("</body></html>");

        return builder.toString();
    }


}

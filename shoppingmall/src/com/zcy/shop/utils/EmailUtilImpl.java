package com.zcy.shop.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

//文件上传工具类具体实现  
@Component("emailUtilImpl")
public class EmailUtilImpl implements EmailUtil {

	public void sendEmail(String emailAddress, String id) {
		// TODO Auto-generated method stub
		// 1. 登陆邮件客户端(创建会话session)
        Properties prop = new Properties();
        Session session = null;
        Message message = null;
        Transport transport = null;
        try {
        	//指定SMTP服务器
        	prop.setProperty("mail.smtp.host", "smtp.sina.com");
        	prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.transport.protocol", "smtp");
            // 创建了session会话
            session = Session.getDefaultInstance(prop);
            // 设置debug模式来调试发送信息
            session.setDebug(true);
            // 创建一封邮件对象
            message = new MimeMessage(session);
            
            // 写信
            message.setSubject("网上商城订单反馈");
            // 正文内容
            message.setContent("顾客您好，欢迎您光顾网上商城，订单" + id + "已支付成功！", "text/html;charset=utf-8");
            // 发件人地址
            message.setFrom(new InternetAddress("mczxzcy@sina.com"));    //设置收件人的邮箱       
            transport = session.getTransport();
            // 链接邮件服务器的认证信息
            transport.connect("smtp.sina.com", "mczxzcy", "zcy992815");

            // 设置收件人地址，并发送邮件
            transport.sendMessage(message, new InternetAddress[] { new InternetAddress(emailAddress) });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {         
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
	}

}

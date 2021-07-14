package com.zdj.TMBookStore.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author 华韵流风
 * @ClassName SendEmailMsg
 * @Description TODO
 * @Date 2021/5/30 22:45
 * @packageName com.zdj.TMBookStore.utils
 */
public class SendEmailMsg {

    public static boolean send(String email, String title, String massage) {

        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host", "smtp.qq.com");

        properties.setProperty("mail.transport.protocol", "smtp");

        properties.setProperty("mail.smtp.auth", "true");

        try {
            //QQ存在一个特性设置SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //创建一个session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("3300358443@qq.com", "hejrrgjkfysgdbdd");
                }
            });

            //开启debug模式
            session.setDebug(true);

            //获取连接对象
            Transport transport = session.getTransport();

            //连接服务器
            transport.connect("smtp.qq.com", "3300358443@qq.com", "hejrrgjkfysgdbdd");

            //创建邮件对象
            MimeMessage mimeMessage = new MimeMessage(session);

            //邮件发送人
            mimeMessage.setFrom(new InternetAddress("3300358443@qq.com"));

            //邮件接收人
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

            //邮件标题
            mimeMessage.setSubject(title);

            //邮件内容
            mimeMessage.setContent(massage, "text/html;charset=UTF-8");

            //发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            //关闭连接
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}

package com.zhuanleme.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: EmailUtil.java</p>
 * <p/>
 * <p>Description: EmailUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/9/5
 */
public class EmailUtil {

    private static String CONFIG_FILE;

    static {
        URL path = EmailUtil.class.getProtectionDomain().getCodeSource().getLocation();
        CONFIG_FILE = path.getPath() + "mail.properties";
    }

    private Properties properties = new Properties();
    /**
     * mimeMessage对象将存储我们实际发送的电子邮件信息，
     */
    private MimeMessage mimeMessage;
    /**
     * Session 类代表javaMail中的一个邮件会话
     */
    private Session session;

    private Transport transport;

    private String mailHost = "smtp.qq.com";
    private int port        = 25;
    private boolean auth    = true;
    private String  sender_username = "632776297@qq.com";
    private String  sender_password = "zhangdihong1314@";

    public EmailUtil(boolean debug){
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(CONFIG_FILE))
            ){
            properties.load(inputStream);
            this.mailHost = properties.getProperty("mail.smtp.host");
            this.port = Integer.valueOf(properties.getProperty("mail.smtp.port"));
            this.auth = Boolean.parseBoolean(properties.getProperty("mail.smtp.auth"));
            this.sender_username = properties.getProperty("mail.sender.username");
            this.sender_password = properties.getProperty("mail.sender.password");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        session = Session.getInstance(properties);

        session.setDebug(debug);
        mimeMessage = new MimeMessage(session);
    }

    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser){
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            // 下面这个是设置发送人的Nick name
            mimeMessage.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);

            mimeMessage.setRecipient(Message.RecipientType.TO, to);

            //邮件主题
            mimeMessage.setSubject(subject);

            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本 "text/plain"
            mimeMessage.setContent(content, "text/plain;charset=UTF-8");

            // 保存邮件
            mimeMessage.saveChanges();
            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件用户名密码
            transport.connect(mailHost, port, sender_username, sender_password);
            // 发送
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 发送邮件
     *
     * @param subject     邮件主题
     * @param sendHtml    邮件内容
     * @param receiveUser 收件人地址
     * @param attachment  附件
     */
    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser, File attachment) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            mimeMessage.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            mimeMessage.setRecipient(Message.RecipientType.TO, to);

            // 邮件主题
            mimeMessage.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

                //MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // 将multipart对象放到message中
            mimeMessage.setContent(multipart);
            // 保存邮件
            mimeMessage.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, port, sender_username, sender_password);
            // 发送
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

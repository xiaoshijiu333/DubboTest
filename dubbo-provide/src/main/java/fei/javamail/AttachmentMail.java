package fei.javamail;


import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @author xiaoshijiu
 * @description 发送附件邮件
 * @package javaemil
 * @date 2019/5/2
 */
public class AttachmentMail {
    /**
     * @description: SMTP服务器地址
     */
    private static final String MAIL_HOST = "smtp.126.com";

    /**
     * @description: 邮箱地址
     */
    private static final String MAIL_ADDRESS = "mychenshan@126.com";

    /**
     * @description: 开启SMTP服务器地址的授权码
     */
    private static final String MAIL_PASSWORD = "guozhongyu12128";

    /**
     * @description: SMTP服务器(SSL启用端口)
     * 25端口不需要ssl安全认证，如网易
     */
    //private static final String GMAIL_PORT = "465";

    /**
     * @params: toAddress收件人地址
     * @params: mailContex邮件内容
     */
    public static void sendMail(String toAddress, MimeMultipart mm) throws Exception {

        Properties properties = new Properties();
        //设置参数配置，由于连接邮件服务器
        //设置协议，JavaMail规范要求
        properties.setProperty("mail.transport.protocol", "smtp");

        //设置发件人的邮箱SMTP服务器地址
        properties.setProperty("mail.smtp.host", MAIL_HOST);

        // 需要身份认证（端口25的不需要，如网易）

        //创建会话
        Session session = Session.getInstance(properties);

        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);

        //创建一封邮件
        MimeMessage mimeMessage = new MimeMessage(session);

        /**
         * @function: 设置发件人
         * @params: 发件人邮箱地址，显示的昵称，昵称字符编码
         */
        mimeMessage.setFrom(new InternetAddress(MAIL_ADDRESS, "佚名", "UTF-8"));

        /**
         * @function: 设置收件人
         * @params: 发送邮件类型，TO普通收件人，CC抄送，BCC密送
         * @params: 收件人邮箱地址，显示的昵称，昵称字符编码
         */
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAddress, "周宇", "UTF-8"));
        //设置自己为抄送人可以避免 网易 554 垃圾 识别
        mimeMessage.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(toAddress, "小诗酒", "UTF-8"));

        //设置邮件主题
        mimeMessage.setSubject("周宇先生");


        //设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
        mimeMessage.setContent(mm);

        //设置发件时间，立即发送
        mimeMessage.setSentDate(new Date());

        //得到邮差对象，让他帮我们发送邮件
        Transport transport = session.getTransport();

        //连接，发送
        transport.connect(MAIL_ADDRESS, MAIL_PASSWORD);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //将该邮件保存到本地
        OutputStream out = new FileOutputStream("MyAttachmentEmail.eml");
        mimeMessage.writeTo(out);
        out.flush();
        out.close();

        transport.close();
    }
}


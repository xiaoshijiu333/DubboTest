package javaemil;

import fei.javamail.JavaMail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * @author xiaoshijiu
 * @description 发送附件邮件
 * @package javaemil
 * @date 2019/5/2
 */
public class AttachmentMail {

    private static final Log log= LogFactory.getLog(JavaMail.class);

    @Test
    public void mailTest(){
        try {

            //复杂类型邮件，图片加附件
            //创建图片"节点"
            MimeBodyPart image = new MimeBodyPart();
            // 读取本地文件
            DataHandler dh = new DataHandler(new FileDataSource("yang.jpg"));
            // 将图片数据添加到"节点"
            image.setDataHandler(dh);
            // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）
            image.setContentID("yang");

            // 6. 创建文本"节点"
            MimeBodyPart text = new MimeBodyPart();
            // 这里添加图片的方式是将整个图片包含到邮件内容中
            text.setContent("仰慕你<br/><img src='cid:yang'/>", "text/html;charset=UTF-8");

            // 7. （文本+图片）设置 文本 和 图片"节点"的关系（将 文本 和 图片"节点"合成一个混合"节点"）
            MimeMultipart mm_text_image = new MimeMultipart();
            mm_text_image.addBodyPart(text);
            mm_text_image.addBodyPart(image);
            mm_text_image.setSubType("related");    // 关联关系

            // 8. 将 文本+图片 的混合"节点"封装成一个普通"节点"
            // 最终添加到邮件的 Content 是由多个 BodyPart(普通结点) ， 不能是 Multipart(混合结点)
            // 上面的 mailTestPic 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
            MimeBodyPart text_image = new MimeBodyPart();
            text_image.setContent(mm_text_image);

            // 9. 创建附件"节点"
            MimeBodyPart attachment = new MimeBodyPart();
            // 读取本地文件
            DataHandler dh2 = new DataHandler(new FileDataSource("周宇先生.txt"));
            // 将附件数据添加到"节点"
            attachment.setDataHandler(dh2);
            // 设置附件的文件名（需要编码）
            attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

            // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
            MimeMultipart mm = new MimeMultipart();
            mm.addBodyPart(text_image);
            mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
            mm.setSubType("mixed");         // 混合关系

            //发送
            //设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
            fei.javamail.AttachmentMail.sendMail("884341537@qq.com", mm);

            log.info("发送成功");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

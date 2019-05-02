package javaemil;

import fei.javamail.JavaMail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author xiaoshijiu
 * @location dubbo2/javaemil
 * @date 2019/4/24
 */
public class JavaEmail {

    private static final Log log= LogFactory.getLog(JavaMail.class);

   @Test
    public void mailTest(){
        StringBuilder context = new StringBuilder("在吗在吗？？？这是一个测试。");
        try {
            JavaMail.sendMail("384115451@qq.com", context);
            log.info("发送成功");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}


package com.kenyon.springboot2train;

import com.kenyon.springboot2train.config.JavaConfig;
import com.kenyon.springboot2train.entity.Book;
import com.kenyon.springboot2train.entity.User;
import com.kenyon.springboot2train.itf.Food;
import com.kenyon.springboot2train.service.RedisService;
import com.kenyon.springboot2train.service.UserMpService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
class Springboot2trainApplicationTests {
    @Autowired
    private Book book;

    @Test
    void contextLoads() {
        System.out.println(book);
    }

    /**
     * 因为需要向环境中写入配置值，所以不可以使用Junit测试
     *
     * java.lang.IllegalStateException: Failed to load ApplicationContext
     */
    @Test
    void testCondition() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().getSystemProperties().put("people", "南方人");
        ctx.register(JavaConfig.class);
        ctx.refresh();
        Food food = (Food) ctx.getBean("food");
        System.out.println(food.showName());
    }

    @Autowired
    TemplateEngine templateEngine;
    @Test
    public void testMail() throws MessagingException {
        Context context = new Context();
        context.setVariable("username", "kenyon");
        context.setVariable("position", "Java工程师");
        context.setVariable("salary", 10000);
        String mail = templateEngine.process("mail", context);
        //省略邮件发送，将模板打印出来
        System.out.println(mail);
    }

    @Autowired
    RedisService redisService;
    @Test
    public void  testRedis(){
        redisService.setValue("addr3","宁德");
        Object addr1 = redisService.getValue("addr3");
        System.out.println(addr1);
    }

    @Autowired
    UserMpService userMpService;
    @Test
    public void testCache(){
        User user = userMpService.getUserById(7L);
        System.out.println(user);
        user.setAddress("福州");
        User updateUserById = userMpService.updateUserById(user);
        System.out.println(updateUserById);
        userMpService.deleteUserById(updateUserById.getId());
    }
}

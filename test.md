近几年来，越来越多的公司加入前后端分离的大趋势之中，但是还是有部分开发离不开后端模板，在前后端不分离的项目上，后端模板更是不可缺少的部分。

早期，Jsp作为Java后端的基本页面模板，在SpringBoot中已经很少使用，整合起来和使用都比较麻烦，SpringBoot目前的主要的后端模板以Thymeleaf和 Freemarker为主，这一节我们就来讲讲在SpringBoot中如何使用Themeleaf。

### ==Themeleaf==
Thymeleaf 是新一代 Java 模板引擎，它类似于 Velocity、FreeMarker 等传统 Java 模板引擎，但是与传统 Java 模板引擎不同的是，Thymeleaf 支持 HTML 原型。

它既可以让前端工程师在浏览器中直接打开查看样式，也可以让后端工程师结合真实数据查看显示效果，同时，SpringBoot 提供了 Thymeleaf 自动化配置解决方案，因此在 SpringBoot 中使用 Thymeleaf 非常方便。

事实上， Thymeleaf 除了展示基本的 HTML ，进行页面渲染之外，也可以作为一个 HTML 片段进行渲染，例如我们在做邮件发送时，可以使用 Thymeleaf 作为邮件发送模板。

另外，由于 Thymeleaf 模板后缀为 .html，可以直接被浏览器打开，因此，预览时非常方便。

### ==实战==
#### 1. 首先是创建包含 Thymeleaf 模板的项目
在IDEA中新创建SpringBoot项目是，直接选中Thymeleaf模板穿建即可
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205124109822.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21yX2FjY29tcGFueQ==,size_16,color_FFFFFF,t_70)
在已经存在的SpringBoot项目，只需要在pom.xml中引入Thymeleaf的dependency即可
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
然后在resources目录下创建templates目录
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205124454787.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21yX2FjY29tcGFueQ==,size_16,color_FFFFFF,t_70)
#### 2. 创建 Controller
```
@Controller
public class HelloController {

    @GetMapping("/index")
    public String index(Model model){
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User u = new User();
            u.setId((long) i);
            u.setName("名称:" + i);
            u.setAddress("地址:" + i);
            users.add(u);
        }
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/name")
    public String name(Model model){
        model.addAttribute("username", "李四");
        return "name";
    }
}
```
#### 3. 在 templates 目录下创建 Thymeleaf 模板
index.html
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>编号</td>
            <td>用户名</td>
            <td>地址</td>
        </tr>
        <tr th:each="user : ${users}">
            <td th:text="${user.id}"></td>
            <td th:text="${user.name}"></td>
            <td th:text="${user.address}"></td>
        </tr>
    </table>
</body>
</html>
```
name.html

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    <script th:inline="javascript">
        const username = [[${username}]];
        alert("姓名是："+username);
    </script>

</body>
</html>
```
#### 4. 测试
启动项目，到浏览器中访问
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205125202281.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21yX2FjY29tcGFueQ==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205125211321.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21yX2FjY29tcGFueQ==,size_16,color_FFFFFF,t_70)
#### 5. 邮件模板
现在 templates 目录下穿建邮件模板
mail.html
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>hello 欢迎 <span th:text="${username}"></span>加入 中国社会 集团，您的入职信息如下：</p>
<table border="1">
    <tr>
        <td>职位</td>
        <td th:text="${position}"></td>
    </tr>
    <tr>
        <td>薪水</td>
        <td th:text="${salary}"></td>
    </tr>
</table>
<img src="http://www.javaboy.org/images/sb/javaboy.jpg" alt="">
</body>
</html>
```
然后进行Junit测试

```
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
```
测试结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205125636346.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21yX2FjY29tcGFueQ==,size_16,color_FFFFFF,t_70)
### ==总结==
这里列举了SpringBoot整合Themeleaf的几个要点，还是比较简单的，更多的用法可以阅读[Themeleaf的官方文档](https://www.thymeleaf.org/documentation.html)。
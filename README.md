#SpringBoot 2 练习

## 1. 认识application.properties与application.yaml

在 Spring Boot 中，配置文件有两种不同的格式，一个是 properties ，另一个是 yaml 。

虽然 properties 文件比较常见，但是相对于 properties 而言，yaml 更加简洁明了，而且使用的场景也更多，很多开源项目都是使用 yaml 进行配置（例如 Hexo）。除了简洁，yaml 还有另外一个特点，就是 yaml 中的数据是有序的，properties 中的数据是无序的，在一些需要路径匹配的配置中，顺序就显得尤为重要（例如我们在 Spring Cloud Zuul 中的配置），此时我们一般采用 yaml。

###1. 默认的application.properties
首先，当我们创建一个 Spring Boot 工程时，默认 resources 目录下就有一个 application.properties 文件，可以在 application.properties 文件中进行项目配置，但是这个文件并非唯一的配置文件，在 Spring Boot 中，一共有 4 个地方可以存放 application.properties 文件。
1. 当前项目根目录下的 config 目录下
2. 当前项目的根目录下
3. resources 目录下的 config 目录下
4. resources 目录下

这四个位置是默认位置，即 Spring Boot 启动，默认会从这四个位置按顺序去查找相关属性并加载。但是，这也不是绝对的，我们也可以在项目启动时自定义配置文件位置。
    
application.yaml 也可以写在这四个位置中。

###2. 属性注入
属性注入分为普通的属性注入和类型安全的属性注入。

1. 普通的属性注入,主要是使用@Value注解实现
    自定义 book.properties 文件
    ```properties
    book.name=三国演义
    book.author=罗贯中
    book.id=1
    ```
    定义实体类，配合lombok自动生成getter和setter方法，实现注入默认值
    ```java
    @Data //生成getter,setter等函数
    @AllArgsConstructor //生成全参数构造函数
    @NoArgsConstructor //生成无参构造函数
    @Component
    @PropertySource("classpath:book.properties")
    public class Book {
        @Value("${book.id}")
        private Long id;
        @Value("${book.name}")
        private String name;
        @Value("${book.author}")
        private String author;
    }
    ```

2. 类型安全的属性注入
    自定义author.properties文件
    ```properties
    author.name=罗贯中
    author.age=18
    author.id=1
    author.sex=男
    ```
    定义实体类，配合lombok自动生成getter和setter方法，引入 @ConfigurationProperties(prefix = "book") 注解，并且配置了属性的前缀，此时会自动将 Spring 容器中对应的数据注入到对象对应的属性中
    ```java
    @Data //生成getter,setter等函数
    @AllArgsConstructor //生成全参数构造函数
    @NoArgsConstructor //生成无参构造函数
    @Component //将Author 对象交给 Spring 容器去管理
    @PropertySource(value = "classpath:author.properties") //引入配置文件
    @ConfigurationProperties(prefix = "author") //根据属性的前缀自动将 Spring 容器中对应的数据注入到对象对应的属性中
    public class Author {
        private Long id;
        private Integer age;
        private String sex;
        private String name;
    }
    ```

3. yaml 支持数组注入
    例如
    ```yaml
    my:
      servers:
        - dev.example.com
        - another.example.com
    ```
    这段数据可以绑定到一个带 Bean 的数组中：
    ```java
    @ConfigurationProperties(prefix="my")
    @Component
    public class Config {
    
        private List<String> servers = new ArrayList<String>();
    
        public List<String> getServers() {
            return this.servers;
        }
    }
    ```

4. yaml 不仅可以存储这种简单数据，也可以在集合中存储对象。
    例如
    ```yaml
    redis:
      redisConfigs:
        - host: 192.168.66.128
          port: 6379
        - host: 192.168.66.129
          port: 6380
    ```
    这个可以被注入到如下类中：
    ```java
    @Component
    @ConfigurationProperties(prefix = "redis")
    public class RedisCluster {
        private List<SingleRedisConfig> redisConfigs;
        //省略getter/setter
    }
    ```

5. 测试
    使用Junit进行：
    ```java
    @RunWith(SpringRunner.class)
    @SpringBootTest
    class Springboot2trainApplicationTests {
        @Autowired
        private Book book;
    
        @Test
        void contextLoads() {
            System.out.println(book);
        }
    }
    ```
    输出：
    ```
    Book(id=1, name=西游记, author=罗贯中)
    ```
    另外的几个各位小伙伴可以自行动手试一试呢
   
###总结
application.properties 是 Spring Boot 中配置的一个重要载体，很多组件的属性都可以在这里定制。不同于 properties 文件的无序，yaml 配置是有序的，这一点在有些配置中是非常有用的，例如在 Spring Cloud Zuul 的配置中，当我们配置代理规则时，顺序就显得尤为重要了。当然 yaml 配置也不是万能的，例如，yaml 配置目前不支持 @PropertySource 注解。

#github项目源码地址
```
https://github.com/kenyonlover/springboot2train.git
```
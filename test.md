# 2. 自动化配置之条件注解@Condition

曾经臃肿繁琐的 Spring 配置确实让人感到头大，而 Spring Boot 带来的全新自动化配置，确实缓解了这个问题。

自动化配置中，有一个非常关键的点，那就是条件注解，甚至可以说条件注解是整个 Spring Boot 的基石。

条件注解并非一个新事物，这是一个存在于 Spring 中的东西，我们在 Spring 中常用的 profile 实际上就是条件注解的一个特殊化。

Spring4 中提供了更加通用的条件注解，让我们可以在满足不同条件时创建不同的 Bean，这种配置方式在 Spring Boot 中得到了广泛的使用，大量的自动化配置都是通过条件注解来实现的。

## 实践

### Spring中的条件注解

首先定义接口

```java
public interface Food {
    String showName();
}
```

定义两个实现类

```java
public class Rice implements Food {
    public String showName() {
        return "米饭";
    }
}
```
```java
public class Noodles implements Food {
    public String showName() {
        return "面条";
    }
}
```

定义条件类

```java
public class RiceCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("people").equals("南方人");
    }
}
```
```java
public class NoodlesCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("people").equals("北方人");
    }
}
```

定义java配置类

```java
@Configuration
public class JavaConfig {
    @Bean("food")
    @Conditional(RiceCondition.class)
    Food rice() {
        return new Rice();
    }

    @Bean("food")
    @Conditional(NoodlesCondition.class)
    Food noodles() {
        return new Noodles();
    }
}
```
这个配置类，大家重点注意两个地方：
* 两个 Bean 的名字都为 food，这不是巧合，而是有意取的。两个 Bean 的返回值都为其父类对象 Food。
* 每个 Bean 上都多了 @Conditional 注解，当 @Conditional 注解中配置的条件类的 matches 方法返回值为 true 时，对应的 Bean 就会生效。


配置完成后，我们就可以在 main 方法中进行测试了：
```java
public class TestCondition {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().getSystemProperties().put("people", "南方人");
        ctx.register(JavaConfig.class);
        ctx.refresh();
        Food food = (Food) ctx.getBean("food");
        System.out.println(food.showName());
    }
}
```

注意：因为需要向环境中写入配置值，所以不可以使用Junit测试。

### SpringBoot中的条件注解升级版 @Profile

首先 Food、Rice 以及 Noodles 的定义不用变

直接在 Bean 定义时添加 @Profile 注解
```java
@Configuration
public class JavaConfig2 {
    @Bean("food")
    @Profile("南方人")
    Food rice() {
        return new Rice();
    }
    @Bean("food")
    @Profile("北方人")
    Food noodles() {
        return new Noodles();
    }
}
```
测试:
```java
public class TestCondition2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("南方人");
        ctx.register(JavaConfig2.class);
        ctx.refresh();
        Food food = (Food) ctx.getBean("food");
        System.out.println(food.showName());
    }
}
```


### 总结
@Profile 注解自动帮我们实现了条件注解中我们写的那一套东西。

@Profile 虽然方便，但是不够灵活，因为具体的判断逻辑不是我们自己实现的。而 @Conditional 则比较灵活。

两个例子向大家展示了条件注解在 Spring 中的使用，它的一个核心思想就是当满足某种条件的时候，某个 Bean 才会生效，而正是这一特性，支撑起了 Spring Boot 的自动化配置。
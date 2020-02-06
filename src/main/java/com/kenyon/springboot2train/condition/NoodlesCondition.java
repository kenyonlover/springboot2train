package com.kenyon.springboot2train.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NoodlesCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 为了让环境启动时不需要设置people属性，返回固定值true
//        return context.getEnvironment().getProperty("people").equals("北方人");
        return false;
    }
}
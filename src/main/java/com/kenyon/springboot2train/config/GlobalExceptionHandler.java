package com.kenyon.springboot2train.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        String msg = "服务器内部错误：" + e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器内部错误";
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("status", 500);
        mv.addObject("message", msg);
        mv.setViewName("error");
        return mv;
    }
}

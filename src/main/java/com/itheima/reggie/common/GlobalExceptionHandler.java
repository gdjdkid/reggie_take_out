/*
 * ----------------------------------------------------------------------------------------------------------
 * Copyright (c) 2021-2023 AIA . All Rights Reserved.
 * ----------------------------------------------------------------------------------------------------------
 * Project Name: COAST_<Detail_Module_Name>
 * @Author: Roy
 * @Version: 1.0
 * @Date: Created in 2023-11-10
 * @Description: xxxxxxxxxxxxxxxxxxxxxxxxx
 * ----------------------------------------------------------------------------------------------------------
 * JIRA No               Programmer                  Date                  Description
 * ----------------------------------------------------------------------------------------------------------
 * xxxxxxxxx             xxxxxxxxxxxx                xxxxxxxx              xxxxxxxxxxxxx
 * ----------------------------------------------------------------------------------------------------------
 */

package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * @Author Roy
 * @Date 2023/11/10 23:39
 * @Version 1.0
 **/
//拦截一些类上面的加了这些注解“@RestController,@Controller”的controller
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody   //因为要返回JSON形式的信息
@Slf4j       //方便输出日志
public class GlobalExceptionHandler {


    /**
     * @param
     * @return com.itheima.reggie.common.R<java.lang.String>
     * @author Roy
     * @Description 异常处理方法，如果遇到SQLIntegrityConstraintViolationException这种异常就会在这个方法处理
     * @Date 2023/11/10 23:53
     **/
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());
        return R.error("失败了");
    }

}

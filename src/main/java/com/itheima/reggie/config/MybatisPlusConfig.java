/*
 * ----------------------------------------------------------------------------------------------------------
 * Copyright (c) 2021-2023 AIA . All Rights Reserved.
 * ----------------------------------------------------------------------------------------------------------
 * Project Name: COAST_<Detail_Module_Name>
 * @Author: Roy
 * @Version: 1.0
 * @Date: Created in 2023-11-12
 * @Description: xxxxxxxxxxxxxxxxxxxxxxxxx
 * ----------------------------------------------------------------------------------------------------------
 * JIRA No               Programmer                  Date                  Description
 * ----------------------------------------------------------------------------------------------------------
 * xxxxxxxxx             xxxxxxxxxxxx                xxxxxxxx              xxxxxxxxxxxxx
 * ----------------------------------------------------------------------------------------------------------
 */

package com.itheima.reggie.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description 配置MP的分页插件
 * @Author Roy
 * @Date 2023/11/12 22:44
 * @Version 1.0
 **/
@Configuration
public class MybatisPlusConfig {

    //拦截器
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());  //PaginationInnerInterceptor分页拦截器
        return mybatisPlusInterceptor;
    }
}

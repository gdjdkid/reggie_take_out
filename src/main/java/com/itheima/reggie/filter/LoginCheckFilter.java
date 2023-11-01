package com.itheima.reggie.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径比较  路径匹配器,支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @return void
     * @author Roy
     * @Description TODO
     * @Date 2023/10/31 23:40
     **/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();  //本次请求URI
        String[] urls = new String[]{               //规定放行的路径
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        boolean check = check(urls, requestURI);   // 判断本次请求URI是否需要放行


        log.info("拦截到请求：{}", request.getRequestURI());
        //先放行
        filterChain.doFilter(request, response);


    }

    /**
     * @param urls        规定放行的路径
     * @param requestURI   本次请求URI
     * @return boolean
     * @author Roy
     * @Description TODO
     * @Date 2023/11/1 23:52
     * 路径匹配，检查本次请求是否需要放行
     **/
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match){
                return true;   // 匹配成功
            }
        }
        return false;   //匹配不上
    }








}

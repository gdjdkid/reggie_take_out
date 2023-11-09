package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * 员工登录
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {


        String password = employee.getPassword();
        //对明文的密码进行MD5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件，等值查询   封装好了用户名
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        //getOne方法用于查询一条数据 用户名是唯一的
        Employee emp = employeeService.getOne(queryWrapper);
        //逻辑判断
        if (emp == null) {
            return R.error("登录失败！");  //原因：没有用户名
        }
        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败！");  //原因：密码不对
        }
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用！");  //原因：员工状态为禁用
        }
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);    //参数：从数据库查出来的对象
    }

    /**
     * 员工退出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }

    /**
     * @param employee
     * @return com.itheima.reggie.common.R<java.lang.String>
     * @author Roy
     * @Description 新增员工
     * @Date 2023/11/9 22:46
     **/
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}", employee.toString());

        //设置初始密码123456  需要MD5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //LocalDateTime.now() 获取当前系统时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //获得当前登录用户的ID
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功！");
    }


}

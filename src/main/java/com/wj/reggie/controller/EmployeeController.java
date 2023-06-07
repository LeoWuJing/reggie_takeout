package com.wj.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wj.reggie.common.R;
import com.wj.reggie.entity.Employee;
import com.wj.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        return employeeService.login(request, employee);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        return employeeService.save(request, employee);
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        return employeeService.page(page,pageSize,name);
    }

    @PutMapping
    public R<String>update(HttpServletRequest request,@RequestBody Employee employee){
        return employeeService.update(request, employee);
    }

    @GetMapping("/{id}")
    public R<Employee>getById(@PathVariable String id){
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("没有查询到该用户的信息");
    }
}

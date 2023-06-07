package com.wj.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.reggie.common.R;
import com.wj.reggie.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<Employee> {
    R<Employee> login(HttpServletRequest request, Employee employee);

    R<String> save(HttpServletRequest request, Employee employee);

    R<Page> page(int page, int size, String name);

    R<String> update(HttpServletRequest request, Employee employee);
}

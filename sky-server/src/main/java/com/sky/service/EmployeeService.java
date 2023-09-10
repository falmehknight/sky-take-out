package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return Employee
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);


    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void startOrStop(Integer status, Long id);

    Employee getById(long id);

    void update(EmployeeDTO employeeDTO);

    /**
     *
     * @Author TanYingHao
     * @Description 修改密码
     * @Date 16:20 2023/9/10
     * @Param [passwordEditDTO]
     * @return void
     **/
    void modifyPassword(PasswordEditDTO passwordEditDTO);
}

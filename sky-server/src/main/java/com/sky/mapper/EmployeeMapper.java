package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    /**
     *
     * @Author TanYingHao
     * @Description 插入一个员工
     * @Date 22:35 2023/8/25
     * @Param [employee]
     * @return void
     **/
    @Insert("insert into employee (name, username, password, phone, sex, id_number, create_time, update_time, " +
            "create_user, update_user,status)" + "values" + "(#{name}, #{username}, #{password}, #{phone}, #{sex}, " +
            "#{idNumber},#{createTime}, #{updateTime}, #{createUser}, #{updateUser},#{status})"
            )
    void insert(Employee employee);
    /**
     *
     * @Author TanYingHao
     * @Description 分页查询
     * @Date 22:36 2023/8/25
     * @Param [employeePageQueryDTO]
     * @return com.github.pagehelper.Page<com.sky.entity.Employee>
     **/
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
    /**
     *
     * @Author TanYingHao
     * @Description 根据主键动态修改属性
     * @Date 23:44 2023/8/25
     * @Param [employee]
     * @return void
     **/
    void update(Employee employee);
}

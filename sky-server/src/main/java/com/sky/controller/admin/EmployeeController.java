package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("用户登出")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping
    @ApiOperation("新增用户")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工:{}",employeeDTO.getId());
        employeeService.save(employeeDTO);
        return Result.success();
    }
    @GetMapping("/page")
    @ApiOperation("分页查询员工")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询，参数为：{}",employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     *
     * @Author TanYingHao
     * @Description 启用禁用员工账号
     * @Date 23:39 2023/8/25
     * @Param [status, id]
     * @return com.sky.result.Result
     **/
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result startOrStop(@PathVariable("status") Integer status, Long id){
        log.info("启用禁用员工账号:{}，{}",status,id);
        employeeService.startOrStop(status,id);
        return Result.success();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询员工
     * @Date 9:58 2023/8/26
     * @Param [id]
     * @return com.sky.result.Result<com.sky.entity.Employee>
     **/
    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工")
    public Result<Employee> getById(@PathVariable long id){
        Employee employee = employeeService.getById(id);
        return Result.success(employee) ;
    }
    /**
     *
     * @Author TanYingHao
     * @Description 更新员工信息
     * @Date 10:19 2023/8/26
     * @Param [employeeDTO]
     * @return com.sky.result.Result
     **/
    @PutMapping
    @ApiOperation("更新员工信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("更新员工信息:{}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改密码
     * @Date 16:19 2023/9/10
     * @Param [passwordEditDTO]
     * @return com.sky.result.Result
     **/
    @PutMapping("/editPassword")
    @ApiOperation("修改密码")
    public Result modifyPassword(@RequestBody PasswordEditDTO passwordEditDTO) {
        passwordEditDTO.setEmpId(BaseContext.getCurrentId());
        log.info("修改员工密码：{}",passwordEditDTO.getEmpId());
        System.out.println(passwordEditDTO);
        employeeService.modifyPassword(passwordEditDTO);
        return Result.success();
    }

}

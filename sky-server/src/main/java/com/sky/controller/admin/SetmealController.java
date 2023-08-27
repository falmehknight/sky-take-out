package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SetmealController
 * @Description 套餐相关接口
 * @Author 谭颍豪
 * @Date 2023/8/27 17:10
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(tags = "套餐相关接口")
@RequestMapping("/admin/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
    /**
     *
     * @Author TanYingHao
     * @Description 新增套餐
     * @Date 19:25 2023/8/27
     * @Param [setmealDTO]
     * @return com.sky.result.Result
     **/
    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐:{}",setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 分页查询
     * @Date 19:29 2023/8/27
     * @Param [setmealPageQueryDTO]
     * @return com.sky.result.Result<com.sky.result.PageResult>
     **/
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询:{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("根据id查询套餐：{}",id);
        SetmealVO setmealVO = setmealService.getById(id);
        return Result.success(setmealVO);
    }




}

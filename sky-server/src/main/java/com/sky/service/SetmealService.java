package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

public interface SetmealService {
    /**
     *
     * @Author TanYingHao
     * @Description 新增套餐
     * @Date 17:20 2023/8/27
     * @Param [setmealDTO]
     * @return void
     **/
    void save(SetmealDTO setmealDTO);
    /**
     *
     * @Author TanYingHao
     * @Description 分页查询
     * @Date 19:29 2023/8/27
     * @Param [setmealPageQueryDTO]
     * @return com.sky.result.PageResult
     **/
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}

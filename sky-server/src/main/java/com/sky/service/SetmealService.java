package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

public interface SetmealService {
    /**
     *
     * @Author TanYingHao
     * @Description 新增套餐
     * @Date 17:20 2023/8/27
     * @Param [setmealDTO]
     * @return void
     **/
    void saveWithDish(SetmealDTO setmealDTO);
    /**
     *
     * @Author TanYingHao
     * @Description 分页查询
     * @Date 19:29 2023/8/27
     * @Param [setmealPageQueryDTO]
     * @return com.sky.result.PageResult
     **/
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询套餐
     * @Date 19:56 2023/8/27
     * @Param [id]
     * @return com.sky.vo.SetmealVO
     **/
    SetmealVO getById(Long id);
    /**
     *
     * @Author TanYingHao
     * @Description 修改套餐
     * @Date 22:37 2023/8/27
     * @Param [setmealDTO]
     * @return void
     **/
    void update(SetmealDTO setmealDTO);
}

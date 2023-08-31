package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

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
    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的id数组去批量删除套餐
     * @Date 23:50 2023/8/27
     * @Param [ids]
     * @return void
     **/
    void deleteByIds(List<Long> ids);
    /**
     *
     * @Author TanYingHao
     * @Description 修改套餐停售或在售
     * @Date 0:01 2023/8/28
     * @Param [status, id]
     * @return void
     **/
    void updateSetmealStatus(Integer status, Long id);


   /**
    *
    * @Author TanYingHao
    * @Description 条件查询
    * @Date 20:17 2023/8/30
    * @Param [setmeal]
    * @return java.util.List<Setmeal>
    **/
    List<Setmeal> list(Setmeal setmeal);

  /**
   *
   * @Author TanYingHao
   * @Description 根据id查询菜品选项
   * @Date 20:17 2023/8/30
   * @Param [id]
   * @return java.util.List<com.sky.vo.DishItemVO>
   **/
    List<DishItemVO> getDishItemById(Long id);
}

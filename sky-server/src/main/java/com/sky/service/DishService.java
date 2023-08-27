package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DishService {


    void saveWithFlavors(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);


    DishVO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的id以及status改变菜品的停售或者起售
     * @Date 16:49 2023/8/27
     * @Param [status, id]
     * @return void
     **/
    void updateWithStatus(Integer status, Long id);
    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的分类id查询菜品
     * @Date 19:13 2023/8/27
     * @Param [categoryId]
     * @return java.util.List<com.sky.entity.Dish>
     **/
    List<Dish> getByCateGoryId(Long categoryId);
}

package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

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
}

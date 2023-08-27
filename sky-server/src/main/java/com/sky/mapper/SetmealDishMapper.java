package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {


    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
    /**
     *
     * @Author TanYingHao
     * @Description 新增一条套餐的详细信息
     * @Date 17:42 2023/8/27
     * @Param [dish]
     * @return void
     **/
    @Insert("insert into setmeal_dish (setmeal_id, dish_id, name, price, copies) VALUES (#{setmealId}, #{dishId}, " +
            "#{name}, #{price}, #{copies})")
    void save(SetmealDish dish);
}

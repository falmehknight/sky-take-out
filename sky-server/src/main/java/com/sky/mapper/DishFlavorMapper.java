package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName DishFlavorMapper
 * @Description 菜品口味相关
 * @Author 谭颍豪
 * @Date 2023/8/26 21:57
 * @Version 1.0
 **/
@Mapper
public interface DishFlavorMapper {
    /**
     *
     * @Author TanYingHao
     * @Description 批量插入到口味表
     * @Date 22:06 2023/8/26
     * @Param [flavors]
     * @return void
     **/
    void insertBatch(List<DishFlavor> flavors);

    /**
     *
     * @Author TanYingHao
     * @Description 根据dishId删除口味表中数据
     * @Date 10:10 2023/8/27
     * @Param [dishId]
     * @return void
     **/
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     *
     * @Author TanYingHao
     * @Description 根据dishId查询口味表数据
     * @Date 10:11 2023/8/27
     * @Param [dishId]
     * @return java.util.List<com.sky.entity.DishFlavor>
     **/
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}

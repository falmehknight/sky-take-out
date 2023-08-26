package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

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
}

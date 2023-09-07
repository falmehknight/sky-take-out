package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);
    /**
     *
     * @Author TanYingHao
     * @Description 插入菜品
     * @Date 21:55 2023/8/26
     * @Param [dish]
     * @return void
     **/
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询菜品
     * @Date 9:57 2023/8/27
     * @Param [dishPageQueryDTO]
     * @return com.github.pagehelper.Page<com.sky.vo.DishVO>
     **/
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询菜品
     * @Date 9:57 2023/8/27
     * @Param [id]
     * @return com.sky.entity.Dish
     **/
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     *
     * @Author TanYingHao
     * @Description 根据id删除菜品
     * @Date 9:56 2023/8/27
     * @Param [id]
     * @return void
     **/
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     *
     * @Author TanYingHao
     * @Description 更新菜品信息
     * @Date 9:56 2023/8/27
     * @Param [dish]
     * @return void
     **/
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     *
     * @Author TanYingHao
     * @Description 根据categoryId查询菜品
     * @Date 19:15 2023/8/27
     * @Param [categoryId]
     * @return java.util.List<com.sky.entity.Dish>
     **/
    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> getByCategoryId(Long categoryId);

    /**
     * 根据条件统计菜品数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}

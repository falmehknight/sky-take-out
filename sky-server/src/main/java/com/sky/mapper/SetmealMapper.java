package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     *
     * @Author TanYingHao
     * @Description 新增套餐,这里要回传id，排错排了好久
     * @Date 17:27 2023/8/27
     * @Param [setmeal]
     * @return void
     **/
    @AutoFill(OperationType.INSERT)
    void save(Setmeal setmeal);

    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询套餐
     * @Date 19:59 2023/8/27
     * @Param [id]
     * @return com.sky.entity.Setmeal
     **/
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);

    /**
     *
     * @Author TanYingHao
     * @Description 更新套餐
     * @Date 22:39 2023/8/27
     * @Param [setmeal]
     * @return void
     **/
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);
    /**
     *
     * @Author TanYingHao
     * @Description 删除套餐
     * @Date 23:56 2023/8/27
     * @Param [setmealId]
     * @return void
     **/
    @Delete("delete from setmeal where id = #{setmealId}")
    void deleteById(Long setmealId);
}

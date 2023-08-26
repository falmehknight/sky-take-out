package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     *
     * @Author TanYingHao
     * @Description 插入一个新分类
     * @Date 11:36 2023/8/26
     * @Param [category]
     * @return void
     **/
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user)" +
            " VALUES" + " (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, " +
            "#{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Category category);

    /**
     *
     * @Author TanYingHao
     * @Description 查询分页
     * @Date 12:21 2023/8/26
     * @Param [categoryPageQueryDTO]
     * @return com.github.pagehelper.Page<com.sky.entity.Category>
     **/
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
    /**
     *
     * @Author TanYingHao
     * @Description 删除分类
     * @Date 14:52 2023/8/26
     * @Param [id]
     * @return void
     **/
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);
    /**
     *
     * @Author TanYingHao
     * @Description 根据id修改分类
     * @Date 14:57 2023/8/26
     * @Param [category]
     * @return void
     **/
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);
    /**
     *
     * @Author TanYingHao
     * @Description 根据类型查询分类
     * @Date 15:05 2023/8/26
     * @Param [type]
     * @return java.util.List<com.sky.entity.Category>
     **/
    List<Category> list(Integer type);
}

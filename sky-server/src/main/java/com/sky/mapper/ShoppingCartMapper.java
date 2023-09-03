package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     *
     * @Author TanYingHao
     * @Description 动态查询购物车信息
     * @Date 23:54 2023/8/31
     * @Param [shoppingCart]
     * @return java.util.List<com.sky.entity.ShoppingCart>
     **/
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     *
     * @Author TanYingHao
     * @Description 根据id去修改购物车菜品数量数据
     * @Date 0:00 2023/9/1
     * @Param [shoppingCart1]
     * @return void
     **/
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart1);

    /**
     *
     * @Author TanYingHao
     * @Description 插入操作
     * @Date 0:12 2023/9/1
     * @Param [shoppingCart]
     * @return void
     **/
    @Insert("insert into shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time) VALUES " +
            "(#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount}, #{createTime})" )
    void insert(ShoppingCart shoppingCart);

    /**
     *
     * @Author TanYingHao
     * @Description 删除操作
     * @Date 23:18 2023/9/2
     * @Param [shoppingCart]
     * @return void
     **/
    void delete(ShoppingCart shoppingCart);
    /**
     *
     * @Author TanYingHao
     * @Description 批量插入购物车
     * @Date 18:43 2023/9/3
     * @Param [shoppingCartList]
     * @return void
     **/
    void insertBatch(List<ShoppingCart> shoppingCartList);
}

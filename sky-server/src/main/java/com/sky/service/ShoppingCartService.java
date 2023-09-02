package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;

import java.util.List;

public interface ShoppingCartService {

    /**
     *
     * @Author TanYingHao
     * @Description 添加购物车
     * @Date 23:50 2023/8/31
     * @Param [shoppingCartDTO]
     * @return void
     **/
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);
    /**
     *
     * @Author TanYingHao
     * @Description 查看购物车
     * @Date 23:09 2023/9/2
     * @Param []
     * @return com.sky.result.Result<java.util.List<com.sky.entity.ShoppingCart>>
     **/
    List<ShoppingCart> list();

    /**
     *
     * @Author TanYingHao
     * @Description 清空购物车
     * @Date 23:16 2023/9/2
     * @Param []
     * @return void
     **/
    void deleteAllShoppingCart();
    /**
     *
     * @Author TanYingHao
     * @Description 删除购物车中一个商品
     * @Date 23:24 2023/9/2
     * @Param [shoppingCartDTO]
     * @return void
     **/
    void deleteShoppingCart(ShoppingCartDTO shoppingCartDTO);
}

package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ShopCartController
 * @Description C端-购物车接口
 * @Author 谭颍豪
 * @Date 2023/8/31 23:41
 * @Version 1.0
 **/
@RequestMapping("/user/shoppingCart")
@Slf4j
@RestController("userShoppingCartController")
@Api("C端-购物车接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     *
     * @Author TanYingHao
     * @Description 添加购物车功能
     * @Date 23:00 2023/9/2
     * @Param [shoppingCartDTO]
     * @return com.sky.result.Result
     **/
    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public Result addShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 查看购物车功能
     * @Date 23:15 2023/9/2
     * @Param []
     * @return com.sky.result.Result<java.util.List<com.sky.entity.ShoppingCart>>
     **/
    @ApiOperation("查看购物车")
    @GetMapping("/list")
    public Result<List<ShoppingCart>> showShoppingCart() {
        List<ShoppingCart> list = shoppingCartService.list();
        return Result.success(list);
    }
    /**
     *
     * @Author TanYingHao
     * @Description 清空购物车
     * @Date 23:21 2023/9/2
     * @Param []
     * @return com.sky.result.Result
     **/
    @ApiOperation("清空购物车")
    @DeleteMapping("/clean")
    public Result deleteAllShoppingCart() {
        shoppingCartService.deleteAllShoppingCart();
        return Result.success();
    }

    @ApiOperation("删除购物车中一个商品")
    @PostMapping("/sub")
    public Result deleteShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.deleteShoppingCart(shoppingCartDTO);
        return Result.success();
    }


}

package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ShopController
 * @Description 商铺营业状态控制
 * @Author 谭颍豪
 * @Date 2023/8/27 15:30
 * @Version 1.0
 **/
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "商铺营业状态")
public class ShopController {

    private static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     *
     * @Author TanYingHao
     * @Description 设置商铺状态
     * @Date 15:33 2023/8/27
     * @Param [status]
     * @return com.sky.result.Result
     **/
    @PutMapping("/{status}")
    @ApiOperation("设置商铺状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置商铺的营业状态为：{}",status == 1 ? "营业中" : "打烊了");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取商铺状态
     * @Date 15:41 2023/8/27
     * @Param []
     * @return com.sky.result.Result<java.lang.Integer>
     **/
    @GetMapping("/status")
    @ApiOperation("查询商铺营业状态")
    public Result<Integer> getStatus() {
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取到商铺的营业状态为：{}",shopStatus == 1 ? "营业中" : "打烊了");
        return Result.success(shopStatus);
    }
}

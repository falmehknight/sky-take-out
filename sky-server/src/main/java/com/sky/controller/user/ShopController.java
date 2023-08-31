package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ShopController
 * @Description 用户端店铺营业状态
 * @Author 谭颍豪
 * @Date 2023/8/27 15:51
 * @Version 1.0
 **/
@RestController("userShopController")
@Api(tags = "商铺营业状态")
@Slf4j
@RequestMapping("/user/shop")
public class ShopController {

    private static final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation("查询商铺营业状态")
    public Result<Integer> getStatus() {
        log.info("用户进入查询店铺状态");
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取到商铺的营业状态为：{}",shopStatus == 1 ? "营业中" : "打烊了");
        return Result.success(shopStatus);
    }
}

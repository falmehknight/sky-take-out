package com.sky.controller.user;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description C端-订单接口
 * @Author 谭颍豪
 * @Date 2023/9/3 11:10
 * @Version 1.0
 **/
@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
@Api("C端-订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     *
     * @Author TanYingHao
     * @Description 用户下单
     * @Date 11:17 2023/9/3
     * @Param []
     * @return com.sky.result.Result<com.sky.vo.OrderSubmitVO>
     **/
    @ApiOperation("用户下单")
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submitOrder(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

}

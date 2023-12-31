package com.sky.controller.user;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 历史订单查询
     * @Date 16:51 2023/9/3
     * @Param [page, pageSize, status]
     * @return com.sky.result.Result<java.util.List<com.sky.result.PageResult>>
     **/
    @GetMapping("/historyOrders")
    @ApiOperation("历史订单查询")
    public Result<PageResult> showHistoryOrders(int page, int pageSize, Integer status) {
        log.info("进入历史订单查询,page:{},pageSize:{},status;{}",page, pageSize, status);
        PageResult pageResult = orderService.pageQuery4User(page, pageSize, status);
        return Result.success(pageResult);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查询订单详情
     * @Date 17:35 2023/9/3
     * @Param [id]
     * @return com.sky.result.Result<com.sky.vo.OrderVO>
     **/
    @GetMapping("/orderDetail/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderVO> queryOrderDetails(@PathVariable("id") Long id) {
        log.info("查询订单详情，订单id为:{}",id);
        OrderVO orderVO = orderService.queryOrderDetails(id);
        return Result.success(orderVO);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 取消订单
     * @Date 17:46 2023/9/3
     * @Param [id]
     * @return com.sky.result.Result
     **/
    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    public Result cancelOrder(@PathVariable("id") Long id) throws Exception {
        orderService.cancelOrder(id);
        return Result.success();
    }

    @ApiOperation("再来一单")
    @PostMapping("/repetition/{id}")
    public Result repetitionOrder(@PathVariable("id") Long id) {
        orderService.repetitionOrder(id);
        return Result.success();
    }

    @ApiOperation("催单")
    @GetMapping("/reminder/{id}")
    public Result reminder(@PathVariable("id") Long id) {
        orderService.reminder(id);
        return Result.success();
    }

}

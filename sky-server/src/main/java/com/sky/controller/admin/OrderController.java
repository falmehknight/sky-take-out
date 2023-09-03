package com.sky.controller.admin;

import com.github.pagehelper.PageHelper;
import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OrderController
 * @Description 订单管理接口
 * @Author 谭颍豪
 * @Date 2023/9/3 19:48
 * @Version 1.0
 **/
@RestController("adminOrderController")
@Slf4j
@Api("订单管理接口")
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     *
     * @Author TanYingHao
     * @Description 订单搜索
     * @Date 19:52 2023/9/3
     * @Param []
     * @return com.sky.result.Result<com.sky.result.PageResult>
     **/
    @ApiOperation("订单搜索")
    @GetMapping("/conditionSearch")
    public Result<PageResult> conditionOrderSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
         PageResult pageResult = orderService.conditionOrderSearch4Admin(ordersPageQueryDTO);
         return Result.success(pageResult);
    }
    /**
     *
     * @Author TanYingHao
     * @Description  各个状态的订单数量统计
     * @Date 20:18 2023/9/3
     * @Param []
     * @return com.sky.result.Result<com.sky.vo.OrderStatisticsVO>
     **/
    @ApiOperation("各个状态的订单数量统计")
    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> showStatistic() {
        OrderStatisticsVO orderStatisticsVO = orderService.showStatistic();
        return Result.success(orderStatisticsVO);
    }
    /**
     *
     * @Author TanYingHao
     * @Description 查看订单详情
     * @Date 20:34 2023/9/3
     * @Param [id]
     * @return com.sky.result.Result<com.sky.vo.OrderVO>
     **/
    @GetMapping("/details/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderVO> showOrderDetails(@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.queryOrderDetails(id);
        return Result.success(orderVO);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 接单
     * @Date 20:34 2023/9/3
     * @Param [id]
     * @return com.sky.result.Result
     **/
    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirmOrder(@RequestBody OrdersConfirmDTO confirmDTO) {
        orderService.confirmOrder(confirmDTO);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 拒单
     * @Date 20:43 2023/9/3
     * @Param [ordersCancelDTO]
     * @return com.sky.result.Result
     **/
    @PutMapping("/rejection")
    @ApiOperation("拒单")
    public Result rejectOrder(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        orderService.rejectOrder(ordersCancelDTO);
        return Result.success();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 取消订单
     * @Date 20:50 2023/9/3
     * @Param [ordersCancelDTO]
     * @return com.sky.result.Result
     **/
    @PutMapping("/cancel")
    @ApiOperation("取消订单")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        orderService.cancel(ordersCancelDTO);
        return Result.success();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 派送订单
     * @Date 20:52 2023/9/3
     * @Param [id]
     * @return com.sky.result.Result
     **/
    @PutMapping("/delivery/{id}")
    @ApiOperation("派送订单")
    public Result delivery(@PathVariable("id") Long id) {
        orderService.delivery(id);
        return Result.success();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 完成订单
     * @Date 20:53 2023/9/3
     * @Param [id]
     * @return com.sky.result.Result
     **/
    @PutMapping("/complete/{id}")
    @ApiOperation("完成订单")
    public Result complete(@PathVariable("id") Long id) {
        orderService.complete(id);
        return Result.success();
    }


}

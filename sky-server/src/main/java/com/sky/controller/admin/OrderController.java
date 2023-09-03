package com.sky.controller.admin;

import com.github.pagehelper.PageHelper;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

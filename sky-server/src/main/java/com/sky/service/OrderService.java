package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {

    /**
     *
     * @Author TanYingHao
     * @Description 用户下订单
     * @Date 11:21 2023/9/3
     * @Param [ordersSubmitDTO]
     * @return com.sky.vo.OrderSubmitVO
     **/
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     *
     * @Author TanYingHao
     * @Description 为用户查询特定的分页
     * @Date 16:54 2023/9/3
     * @Param [page, pageSize, status]
     * @return com.sky.result.PageResult
     **/
    PageResult pageQuery4User(int page, int pageSize, Integer status);

    /**
     *
     * @Author TanYingHao
     * @Description 根据订单id查询详细信息
     * @Date 17:38 2023/9/3
     * @Param [id]
     * @return com.sky.vo.OrderVO
     **/
    OrderVO queryOrderDetails(Long id);
    /**
     *
     * @Author TanYingHao
     * @Description 根据订单id取消订单
     * @Date 17:47 2023/9/3
     * @Param [id]
     * @return void
     **/
    void cancelOrder(Long id) throws Exception;
    /**
     *
     * @Author TanYingHao
     * @Description 再来一单
     * @Date 18:06 2023/9/3
     * @Param [id]
     * @return void
     **/
    void repetitionOrder(Long id);

    /**
     *
     * @Author TanYingHao
     * @Description 管理端订单搜索
     * @Date 19:55 2023/9/3
     * @Param [ordersPageQueryDTO]
     * @return com.sky.result.PageResult
     **/
    PageResult conditionOrderSearch4Admin(OrdersPageQueryDTO ordersPageQueryDTO);
    /**
     *
     * @Author TanYingHao
     * @Description 各个状态的订单数量统计
     * @Date 20:18 2023/9/3
     * @Param []
     * @return com.sky.vo.OrderStatisticsVO
     **/
    OrderStatisticsVO showStatistic();
    /**
     *
     * @Author TanYingHao
     * @Description 接单，更改订单状态
     * @Date 20:36 2023/9/3
     * @Param [OrdersConfirmDTO]
     * @return void
     **/
    void confirmOrder(OrdersConfirmDTO ordersConfirmDTO);
    /**
     *
     * @Author TanYingHao
     * @Description 拒单
     * @Date 20:44 2023/9/3
     * @Param [ordersCancelDTO]
     * @return void
     **/
    void rejectOrder(OrdersCancelDTO ordersCancelDTO) throws Exception;
    /**
     *
     * @Author TanYingHao
     * @Description 取消订单
     * @Date 20:50 2023/9/3
     * @Param [ordersCancelDTO]
     * @return void
     **/
    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;
    /**
     *
     * @Author TanYingHao
     * @Description 订单派送
     * @Date 20:52 2023/9/3
     * @Param [id]
     * @return void
     **/
    void delivery(Long id);
    /**
     *
     * @Author TanYingHao
     * @Description 完成订单
     * @Date 20:53 2023/9/3
     * @Param [id]
     * @return void
     **/
    void complete(Long id);
}

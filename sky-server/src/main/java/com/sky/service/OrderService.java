package com.sky.service;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;

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
}

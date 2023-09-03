package com.sky.service;

import com.sky.dto.OrdersSubmitDTO;
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
}

package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName OrderTask
 * @Description 订单相关定时任务
 * @Author 谭颍豪
 * @Date 2023/9/4 18:56
 * @Version 1.0
 **/
@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrdersMapper ordersMapper;
    /**
     *
     * @Author TanYingHao
     * @Description 每分钟触发一次更改超时订单状态
     * @Date 18:59 2023/9/4
     * @Param []
     * @return void
     **/
    @Scheduled(cron = "0 * * * * ? ") // 每分钟触发一次
    public void processTimeoutOrder() {
        log.info("定时处理超时订单：{}", LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15); // 超过15分钟还未支付的就取消
        List<Orders> ordersList = ordersMapper.getByStatusAndOrderTime(Orders.PENDING_PAYMENT,time);

        if(ordersList == null || ordersList.size() == 0) {
            return ;
        }
        for (Orders orders : ordersList) {
            orders.setStatus(Orders.CANCELLED);
            orders.setCancelTime(LocalDateTime.now());
            orders.setCancelReason("支付超时，订单自动取消");
            ordersMapper.update(orders);
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")//每天凌晨一点
    public void processDeliveryOrder(){
        log.info("定时处理派送中的订单：{}",LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> list = ordersMapper.getByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, time);

        if(list!=null&&list.size()>0){
            for (Orders orders : list) {
                orders.setStatus(Orders.COMPLETED);
                ordersMapper.update(orders);
            }
        }
    }


}

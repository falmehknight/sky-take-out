package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {
    /**
     *
     * @Author TanYingHao
     * @Description 插入订单数据
     * @Date 11:40 2023/9/3
     * @Param [orders]
     * @return void
     **/
    void insert(Orders orders);
}

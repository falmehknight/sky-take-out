package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName OrderDetailMapper
 * @Description 订单明细的mapper层
 * @Author 谭颍豪
 * @Date 2023/9/3 11:25
 * @Version 1.0
 **/
@Mapper
public interface OrderDetailMapper {

    /**
     *
     * @Author TanYingHao
     * @Description 批量插入订单明细表数据
     * @Date 11:54 2023/9/3
     * @Param [orderDetailList]
     * @return void
     **/
    void insertBatch(List<OrderDetail> orderDetailList);
}

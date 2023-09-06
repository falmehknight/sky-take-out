package com.sky.mapper;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
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

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询订单明细
     * @Date 17:19 2023/9/3
     * @Param [orderId]
     * @return java.util.List<com.sky.entity.OrderDetail>
     **/
    @Select("select * from order_detail where order_id = #{orderId}")
    List<OrderDetail> getByOrderId(Long orderId);
    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的时间查询top10的菜品名字以及销量
     * @Date 23:55 2023/9/6
     * @Param [beginTime, endTime]
     * @return java.util.List<com.sky.dto.GoodsSalesDTO>
     **/
    List<GoodsSalesDTO> getGoodsSalesDTOListByTime(LocalDateTime begin, LocalDateTime end);
}

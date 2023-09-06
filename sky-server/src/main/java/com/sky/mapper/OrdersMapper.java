package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询历史订单
     * @Date 17:04 2023/9/3
     * @Param [ordersPageQueryDTO]
     * @return com.github.pagehelper.Page<com.sky.entity.Orders>
     **/
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);
    /**
     *
     * @Author TanYingHao
     * @Description 根据订单id查询订单
     * @Date 17:40 2023/9/3
     * @Param [id]
     * @return com.sky.entity.Orders
     **/
    @Select("select * from orders where id = #{id}")
    Orders getByOrderId(Long id);
    /**
     *
     * @Author TanYingHao
     * @Description 查询给定状态的订单数
     * @Date 20:21 2023/9/3
     * @Param [toBeConfirmed]
     * @return java.lang.Integer
     **/
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);
    /**
     *
     * @Author TanYingHao
     * @Description 根据订单状态以及时间
     * @Date 19:07 2023/9/4
     * @Param [unPaid, minutes]
     * @return java.util.List<com.sky.entity.Orders>
     **/
    @Select("select * from orders where status = #{unPaid} and order_time < #{time}")
    List<Orders> getByStatusAndOrderTime(Integer unPaid, LocalDateTime time);
    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的起始时间以及状态查询总和营业额
     * @Date 19:11 2023/9/6
     * @Param [map]
     * @return java.lang.Double
     **/
    Double sumTurnOverByMap(Map map);
    /**
     *
     * @Author TanYingHao
     * @Description 根据map中传入的起始时间以及状态查询符合要求的订单总数
     * @Date 21:54 2023/9/6
     * @Param [map]
     * @return java.lang.Integer
     **/
    Integer getOrderCntByMap(Map map);
}

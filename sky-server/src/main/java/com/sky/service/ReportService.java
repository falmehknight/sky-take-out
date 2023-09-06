package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import java.time.LocalDate;

public interface ReportService {

    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的时间传出营业额
     * @Date 18:56 2023/9/6
     * @Param [begin, end]
     * @return com.sky.vo.TurnoverReportVO
     **/
    TurnoverReportVO getTurnoverStatistic(LocalDate begin, LocalDate end);
    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的时间统计用户数据
     * @Date 19:46 2023/9/6
     * @Param [begin, end]
     * @return com.sky.vo.UserReportVO
     **/
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);
    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的时间统计订单数据
     * @Date 21:45 2023/9/6
     * @Param [begin, end]
     * @return com.sky.vo.OrderReportVO
     **/
    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);
}

package com.sky.service;

import com.sky.vo.TurnoverReportVO;

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
    TurnoverReportVO turnoverStatistic(LocalDate begin, LocalDate end);
}

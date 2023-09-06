package com.sky.controller.admin;

import com.sky.dto.GoodsSalesDTO;
import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @ClassName ReportController
 * @Description 数据统计相关
 * @Author 谭颍豪
 * @Date 2023/9/6 18:45
 * @Version 1.0
 **/
@Api("数据统计相关接口")
@Slf4j
@RestController
@RequestMapping("/admin/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    /**
     *
     * @Author TanYingHao
     * @Description 营业额统计接口
     * @Date 18:50 2023/9/6
     * @Param [begin, end]
     * @return com.sky.result.Result<com.sky.vo.TurnoverReportVO>
     **/
    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额统计接口")
    public Result<TurnoverReportVO> turnoverStatistic(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("进入营业额统计接口，开始时间:{}，结束时间:{}",begin,end);
        return Result.success(reportService.getTurnoverStatistic(begin,end));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 用户统计接口
     * @Date 19:46 2023/9/6
     * @Param [begin, end]
     * @return com.sky.result.Result<com.sky.vo.UserReportVO>
     **/
    @GetMapping("/userStatistics")
    @ApiOperation("用户统计接口")
    public Result<UserReportVO> userStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("进入用户统计接口，开始时间:{}，结束时间:{}",begin,end);
        return Result.success(reportService.getUserStatistics(begin,end));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 订单统计接口
     * @Date 21:44 2023/9/6
     * @Param [begin, end]
     * @return com.sky.result.Result<com.sky.vo.OrderReportVO>
     **/
    @GetMapping("/ordersStatistics")
    @ApiOperation("订单统计接口")
    public Result<OrderReportVO> orderStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("进入订单统计接口，开始时间:{}，结束时间:{}",begin,end);
        return Result.success(reportService.getOrderStatistics(begin,end));
    }

    @GetMapping("/top10")
    @ApiOperation("查询销量排名top10接口")
    public Result<SalesTop10ReportVO> top10(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("进入查询销量排名top10接口，开始时间:{}，结束时间:{}",begin,end);
        return Result.success(reportService.getTop10(begin,end));
    }

}

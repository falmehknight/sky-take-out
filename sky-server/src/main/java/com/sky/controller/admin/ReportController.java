package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
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
        return Result.success(reportService.turnoverStatistic(begin,end));
    }
}

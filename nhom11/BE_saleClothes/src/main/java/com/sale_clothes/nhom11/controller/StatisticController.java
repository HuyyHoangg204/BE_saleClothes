package com.sale_clothes.nhom11.controller;


import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.dto.response.FullDailyResponse;
import com.sale_clothes.nhom11.dto.response.WeeklyAndMonthlyResponse;
import com.sale_clothes.nhom11.service.impl.StatisticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StatisticController {
    @Autowired
    private StatisticServiceImpl statisticService;

    protected List<FullDailyResponse> fullDailyResponsesGlobal;

    @GetMapping("/revenue-fullDaily")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<FullDailyResponse>> getAllDataFullDaily() {
        List<FullDailyResponse> fullDailyResponses = statisticService.getDataFullDaily();
        fullDailyResponsesGlobal = fullDailyResponses;
        return ApiResponse.<List<FullDailyResponse>>builder()
                .result(fullDailyResponses)
                .build();
    }

    @GetMapping("/revenue-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<WeeklyAndMonthlyResponse>> getDataWeekly() {
        List<WeeklyAndMonthlyResponse> weeklyAndMonthlyResponses = statisticService.summarizeByWeek(fullDailyResponsesGlobal);

        return ApiResponse.<List<WeeklyAndMonthlyResponse>>builder()
                .result(weeklyAndMonthlyResponses)
                .build();
    }

    @GetMapping("/revenue-monthly")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<WeeklyAndMonthlyResponse>> getDataMonthly() {
        List<WeeklyAndMonthlyResponse> weeklyAndMonthlyResponses = statisticService.getDataFullMonthly();

        return ApiResponse.<List<WeeklyAndMonthlyResponse>>builder()
                .result(weeklyAndMonthlyResponses)
                .build();
    }

    @GetMapping("/revenue-yearly")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<WeeklyAndMonthlyResponse>> getDataYearly() {
        List<WeeklyAndMonthlyResponse> weeklyAndMonthlyResponses = statisticService.getDataYearly();

        return ApiResponse.<List<WeeklyAndMonthlyResponse>>builder()
                .result(weeklyAndMonthlyResponses)
                .build();
    }
}

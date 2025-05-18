package com.sale_clothes.nhom11.service.impl;


import com.sale_clothes.nhom11.dto.response.FullDailyResponse;
import com.sale_clothes.nhom11.dto.response.WeeklyAndMonthlyResponse;
import com.sale_clothes.nhom11.entity.OrderStatusHistory;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.repository.DonDatHangRepository;
import com.sale_clothes.nhom11.repository.OrderStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl {
    @Autowired
    private OrderStatusHistoryRepository orderStatusHistoryRepository;

    @Autowired
    private DonDatHangRepository donDatHangRepository;

    // Get full data DayDaily
    public List<FullDailyResponse> getDataFullDaily() {
        List<FullDailyResponse> fullDailyResponses = new ArrayList<>();

        LocalDate today = LocalDate.now(); // ngày hôm nay
        YearMonth currentMonth = YearMonth.from(today); // tháng hiện tại

        int daysInMonth = currentMonth.lengthOfMonth(); // số ngày trong tháng

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = currentMonth.atDay(day);
            LocalDateTime startOfDay = currentDate.atStartOfDay();
            LocalDateTime endOfDay = currentDate.plusDays(1).atStartOfDay();

            // Định dạng ngày theo kiểu "dd/MM"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
            String formattedDate = currentDate.format(formatter);


            // Lấy danh sách lịch sử trạng thái đơn hàng
            List<OrderStatusHistory> histories = orderStatusHistoryRepository
                    .findAllByOrderDateAndOrderStatus(startOfDay, endOfDay, OrderStatus.DELIVERED);

            //Tính tổng doanh thu
           double revenue = histories.stream()
                   .mapToDouble(h -> h.getOrder().getTotalAmount())
                   .sum();

            // Thêm thông tin vào danh sách response
           fullDailyResponses.add(FullDailyResponse.builder()
                           .date(currentDate)
                           .revenue(revenue)
                           .name(formattedDate)
                   .build());

        }

        return fullDailyResponses;

    }

    // Get full data weekly
    public List<WeeklyAndMonthlyResponse> summarizeByWeek(List<FullDailyResponse> fullDailyList) {
        // Map tuần 1-4 với tổng revenue ban đầu là 0
        Map<Integer, Double> fixedWeeklyRevenue = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            fixedWeeklyRevenue.put(i, 0.0);
        }

        // Xác định tuần tương ứng dựa vào ngày trong tháng
        for (FullDailyResponse res : fullDailyList) {
            int dayOfMonth = res.getDate().getDayOfMonth(); // ngày trong tháng
            int weekNumber;

            if (dayOfMonth <= 7) {
                weekNumber = 1; // Ngày 1 đến 7 -> Tuần 1
            } else if (dayOfMonth <= 14) {
                weekNumber = 2; // Ngày 8 đến 14 -> Tuần 2
            } else if (dayOfMonth <= 21) {
                weekNumber = 3; // Ngày 15 đến 21 -> Tuần 3
            } else {
                weekNumber = 4; // Ngày 22 trở đi -> Tuần 4
            }


            // Lấy doanh thu hiện tại của tuần tương ứng từ map
            double currentRevenue = fixedWeeklyRevenue.get(weekNumber);

            // Cộng thêm doanh thu của ngày hiện tại vào tuần tương ứng
            fixedWeeklyRevenue.put(weekNumber, currentRevenue + res.getRevenue());
        }

        // Tạo danh sách kết quả trả về
        List<WeeklyAndMonthlyResponse> result = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            result.add(WeeklyAndMonthlyResponse.builder()
                    .name("Tuần " + i)
                    .revenue(fixedWeeklyRevenue.get(i))
                    .build());
        }

        return result;
    }

    //Get data  monthly
    public List<WeeklyAndMonthlyResponse> getDataFullMonthly() {
        List<WeeklyAndMonthlyResponse> weeklyAndMonthlyResponses = new ArrayList<>();
        int currentYear = LocalDate.now().getYear(); // Lấy năm hiện tại



        for(int month = 1; month <= 12; month ++) {


            YearMonth yearMonth = YearMonth.of(currentYear, month);
            // Đầu tháng: 01/01/2025 00:00
            LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();

            // Cuối tháng: 01/02/2025 00:00 (đầu tháng kế tiếp để làm điều kiện <)
            LocalDateTime endOfMonth = yearMonth.plusMonths(1).atDay(1).atStartOfDay();

            List<OrderStatusHistory> orderStatusHistories = orderStatusHistoryRepository.findAllByOrderMonthAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.DELIVERED);

            double revenue = orderStatusHistories.stream()
                    .mapToDouble(h -> h.getOrder().getTotalAmount())
                    .sum();

            weeklyAndMonthlyResponses.add(WeeklyAndMonthlyResponse.builder()
                    .name("T" + month)
                            .revenue(revenue)
                    .build());
        }
        return weeklyAndMonthlyResponses;
    }
    //get data yearly
    public List<WeeklyAndMonthlyResponse> getDataYearly() {
        List<WeeklyAndMonthlyResponse> yearlyResponses = new ArrayList<>();

        int currentYear = LocalDate.now().getYear();
        int startYear = currentYear - 2;
        int endYear = currentYear + 2;

        for (int year = startYear; year <= endYear; year++) {
            // Đầu năm
            LocalDateTime startOfYear = LocalDate.of(year, 1, 1).atStartOfDay();

            // Đầu năm kế tiếp
            LocalDateTime endOfYear = LocalDate.of(year + 1, 1, 1).atStartOfDay();

            List<OrderStatusHistory> orderStatusHistories =
                    orderStatusHistoryRepository.findAllByOrderMonthAndOrderStatus(startOfYear, endOfYear, OrderStatus.DELIVERED);

            double revenue = orderStatusHistories.stream()
                    .mapToDouble(h -> h.getOrder().getTotalAmount())
                    .sum();

            yearlyResponses.add(WeeklyAndMonthlyResponse.builder()
                    .name(String.valueOf(year))
                    .revenue(revenue)
                    .build());
        }

        return yearlyResponses;
    }


}

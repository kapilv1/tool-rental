
package com.example.toolrentalapi.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class SystemController {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MM/dd/yy");

    @GetMapping("/holidays/{year}")
    public Map<String, String> holidays(@PathVariable("year") int year) {
        Map<String, String> m = new HashMap<>();
        LocalDate july4 = LocalDate.of(year, Month.JULY, 4);
        DayOfWeek dow = july4.getDayOfWeek();
        LocalDate observed = (dow == DayOfWeek.SATURDAY) ? july4.minusDays(1) : (dow == DayOfWeek.SUNDAY) ? july4.plusDays(1) : july4;
        LocalDate labor = LocalDate.of(year, Month.SEPTEMBER, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        LocalDate christmas = LocalDate.of(year, Month.DECEMBER, 25).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        m.put("independenceDayObserved", observed.format(FMT));
        m.put("laborDay", labor.format(FMT));
        m.put("Christmas", christmas.format(FMT));
        return m;
    }

    @GetMapping("/health")
    public Map<String, String> health() { return Map.of("status", "UP"); }

    @GetMapping("/info")
    public Map<String, Object> info() { return Map.of("app", "Tool Rental API", "version", "1.0.0"); }
}

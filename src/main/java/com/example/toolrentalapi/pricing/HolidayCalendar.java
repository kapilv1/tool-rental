
package com.example.toolrentalapi.pricing;

import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

@Component
public class HolidayCalendar {
    public boolean isHoliday(LocalDate date) {
        return isIndependenceDayObserved(date) || isLaborDay(date);
    }
    private boolean isIndependenceDayObserved(LocalDate date) {
        if (date.getMonth() != Month.JULY) return false;
        LocalDate july4 = LocalDate.of(date.getYear(), Month.JULY, 4);
        DayOfWeek dow = july4.getDayOfWeek();
        LocalDate observed = (dow == DayOfWeek.SATURDAY) ? july4.minusDays(1)
                : (dow == DayOfWeek.SUNDAY) ? july4.plusDays(1) : july4;
        return date.equals(observed);
    }
    private boolean isLaborDay(LocalDate date) {
        if (date.getMonth() != Month.SEPTEMBER) return false;
        LocalDate firstMonday = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        return date.equals(firstMonday);
    }
    public boolean isWeekend(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
    }
}

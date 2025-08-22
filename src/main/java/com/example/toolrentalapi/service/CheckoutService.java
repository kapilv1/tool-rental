
package com.example.toolrentalapi.service;

import com.example.toolrentalapi.exception.ValidationException;
import com.example.toolrentalapi.model.RentalAgreement;
import com.example.toolrentalapi.model.Tool;
import com.example.toolrentalapi.model.ToolType;
import com.example.toolrentalapi.pricing.HolidayCalendar;
import com.example.toolrentalapi.repo.ToolCatalog;
import com.example.toolrentalapi.repo.AgreementRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CheckoutService {
    private final ToolCatalog catalog;
    private final HolidayCalendar calendar;
    private final AgreementRepo agreementRepo;

    public CheckoutService(ToolCatalog catalog, HolidayCalendar calendar, AgreementRepo agreementRepo) {
        this.catalog = catalog;
        this.calendar = calendar;
        this.agreementRepo = agreementRepo;
    }

    public RentalAgreement checkout(String toolCode,String customerName, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        validateInputs(rentalDays, discountPercent, checkoutDate);
        Tool tool = catalog.getByCode(toolCode);
        ToolType type = tool.getType();

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateChargeDays(type, checkoutDate.plusDays(1), dueDate);
        BigDecimal daily = type.getDailyCharge();

        BigDecimal preDiscount = daily.multiply(BigDecimal.valueOf(chargeDays)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discount = preDiscount
                .multiply(BigDecimal.valueOf(discountPercent))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal finalCharge = preDiscount.subtract(discount).setScale(2, RoundingMode.HALF_UP);

        RentalAgreement agreement = new RentalAgreement(
                tool.getCode(),
                customerName,
                type.getDisplayName(),
                tool.getBrand(),
                rentalDays,
                checkoutDate,
                dueDate,
                daily,
                chargeDays,
                preDiscount,
                discountPercent,
                discount,
                finalCharge
        );

        agreementRepo.save(agreement);
        return agreement;
    }

    private void validateInputs(int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) throw new ValidationException("Rental day count must be 1 or greater.");
        if (discountPercent < 0 || discountPercent > 100) throw new ValidationException("Discount percent must be between 0 and 100.");
        if (checkoutDate == null) throw new ValidationException("Checkout date is required.");
    }

    private int calculateChargeDays(ToolType type, LocalDate startInclusive, LocalDate endInclusive) {
        int count = 0;
        LocalDate d = startInclusive;
        while (!d.isAfter(endInclusive)) {
            boolean isHoliday = calendar.isHoliday(d);
            boolean isWeekend = calendar.isWeekend(d);
            boolean isWeekday = !isWeekend;
            boolean charge = true;
            if (isHoliday && !type.isChargeHoliday()) charge = false;
            else if (isWeekend && !type.isChargeWeekend()) charge = false;
            else if (isWeekday && !type.isChargeWeekday()) charge = false;
            if (charge) count++;
            d = d.plusDays(1);
        }
        return count;
    }
}


package com.example.toolrentalapi.model;

import com.example.toolrentalapi.util.Formatters;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class RentalAgreement {
    private final String id;
    private final String customerName;
    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final BigDecimal dailyRentalCharge;
    private final int chargeDays;
    private final BigDecimal preDiscountCharge;
    private final int discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MM/dd/yy");

    public RentalAgreement( String toolCode,String customerName, String toolType, String toolBrand, int rentalDays,
                           LocalDate checkoutDate, LocalDate dueDate, BigDecimal dailyRentalCharge,
                           int chargeDays, BigDecimal preDiscountCharge, int discountPercent,
                           BigDecimal discountAmount, BigDecimal finalCharge) {
        this.id = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Name: ").append(customerName).append('\n');
        sb.append("Tool code: ").append(toolCode).append('\n');
        sb.append("Tool type: ").append(toolType).append('\n');
        sb.append("Tool brand: ").append(toolBrand).append('\n');
        sb.append("Rental days: ").append(rentalDays).append('\n');
        sb.append("Checkout date: ").append(checkoutDate.format(DATE_FMT)).append('\n');
        sb.append("Due date: ").append(dueDate.format(DATE_FMT)).append('\n');
        sb.append("Daily rental charge: ").append(Formatters.currency(dailyRentalCharge)).append('\n');
        sb.append("Charge days: ").append(chargeDays).append('\n');
        sb.append("Pre-discount charge: ").append(Formatters.currency(preDiscountCharge)).append('\n');
        sb.append("Discount percent: ").append(Formatters.percent(discountPercent)).append('\n');
        sb.append("Discount amount: ").append(Formatters.currency(discountAmount)).append('\n');
        sb.append("Final charge: ").append(Formatters.currency(finalCharge)).append('\n');
        return sb.toString();
    }


    // Getters
    public String getId() { return id; }
    public String getToolCode() { return toolCode; }
    public String getToolType() { return toolType; }
    public String getToolBrand() { return toolBrand; }
    public int getRentalDays() { return rentalDays; }
    public java.time.LocalDate getCheckoutDate() { return checkoutDate; }
    public java.time.LocalDate getDueDate() { return dueDate; }
    public BigDecimal getDailyRentalCharge() { return dailyRentalCharge; }
    public int getChargeDays() { return chargeDays; }
    public BigDecimal getPreDiscountCharge() { return preDiscountCharge; }
    public int getDiscountPercent() { return discountPercent; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public BigDecimal getFinalCharge() { return finalCharge; }
    public String getCustomerName() { return customerName; }
}

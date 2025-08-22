
package com.example.toolrentalapi.dto;

import java.math.BigDecimal;

public class RentalAgreementResponse {
    public String id;
    public String customerName;
    public String toolCode;
    public String toolType;
    public String toolBrand;
    public int rentalDays;
    public String checkoutDate;
    public String dueDate;
    public BigDecimal dailyRentalCharge;
    public int chargeDays;
    public BigDecimal preDiscountCharge;
    public int discountPercent;
    public BigDecimal discountAmount;
    public BigDecimal finalCharge;
    public String printed;

    public RentalAgreementResponse() {}
}

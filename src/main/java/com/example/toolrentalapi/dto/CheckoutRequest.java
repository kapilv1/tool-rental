
package com.example.toolrentalapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CheckoutRequest {

    @NotBlank(message = "Customer Name is required")
    private String customerName;
    @NotBlank(message = "Tool code is required")
    private String toolCode;

    @Min(value = 1, message = "Rental day count must be 1 or greater")
    private int rentalDays;
    @Min(0) @Max(100)
    private int discountPercent;
    @NotNull(message = "Checkout date is required")
    private LocalDate checkoutDate;

    public String getToolCode() { return toolCode; }
    public void setToolCode(String toolCode) { this.toolCode = toolCode; }
    public int getRentalDays() { return rentalDays; }
    public void setRentalDays(int rentalDays) { this.rentalDays = rentalDays; }
    public int getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(int discountPercent) { this.discountPercent = discountPercent; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public void setCheckoutDate(LocalDate checkoutDate) { this.checkoutDate = checkoutDate; }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}


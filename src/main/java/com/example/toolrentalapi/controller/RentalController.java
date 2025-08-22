
package com.example.toolrentalapi.controller;

import com.example.toolrentalapi.dto.CheckoutRequest;
import com.example.toolrentalapi.dto.RentalAgreementResponse;
import com.example.toolrentalapi.model.RentalAgreement;
import com.example.toolrentalapi.service.CheckoutService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class RentalController {
    private final CheckoutService service;
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MM/dd/yy");

    public RentalController(CheckoutService service) { this.service = service; }

    @PostMapping("/checkout")
    public RentalAgreementResponse checkout(@Valid @RequestBody CheckoutRequest req) {
        RentalAgreement a = service.checkout(req.getToolCode(), req.getCustomerName(),req.getRentalDays(), req.getDiscountPercent(), req.getCheckoutDate());
        return toResponse(a);
    }

@GetMapping("/agreement/print")
public String printAgreement(@RequestParam("toolCode") String toolCode,
                             @RequestParam("customerName") String customerName,
                             @RequestParam("rentalDays") int rentalDays,
                             @RequestParam("discountPercent") int discountPercent,
                             @RequestParam("checkoutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkoutDate) {
    RentalAgreement a = service.checkout(toolCode, customerName,rentalDays, discountPercent, checkoutDate);
    return a.print();
}
    private RentalAgreementResponse toResponse(RentalAgreement a) {
        RentalAgreementResponse r = new RentalAgreementResponse();
        r.id = a.getId();
        r.customerName=a.getCustomerName();
        r.toolCode = a.getToolCode();
        r.toolType = a.getToolType();
        r.toolBrand = a.getToolBrand();
        r.rentalDays = a.getRentalDays();
        r.checkoutDate = a.getCheckoutDate().format(DATE_FMT);
        r.dueDate = a.getDueDate().format(DATE_FMT);
        r.dailyRentalCharge = a.getDailyRentalCharge();
        r.chargeDays = a.getChargeDays();
        r.preDiscountCharge = a.getPreDiscountCharge();
        r.discountPercent = a.getDiscountPercent();
        r.discountAmount = a.getDiscountAmount();
        r.finalCharge = a.getFinalCharge();
        r.printed = a.print();
        return r;
    }
}

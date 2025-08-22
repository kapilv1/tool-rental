
package com.example.toolrentalapi;

import com.example.toolrentalapi.pricing.HolidayCalendar;
import com.example.toolrentalapi.repo.AgreementRepo;
import com.example.toolrentalapi.repo.ToolCatalog;
import com.example.toolrentalapi.service.CheckoutService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AgreementRepoTest {
    @Test
    void agreementSavedAndRetrieved() {
        AgreementRepo repo = new AgreementRepo();
        CheckoutService s = new CheckoutService(new ToolCatalog(), new HolidayCalendar(), repo);
        com.example.toolrentalapi.model.RentalAgreement a = s.checkout("LADW", "TestName",3, 0, LocalDate.of(2020,7,2));
        assertNotNull(repo.findById(a.getId()).orElse(null));
    }
}

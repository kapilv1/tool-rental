
package com.example.toolrentalapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RentalControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper om;

    @Test
    void test1_invalidDiscount_should400() throws Exception {
        Map<String,Object> req = Map.of("toolCode","JAKR","rentalDays",5,"discountPercent",101,"checkoutDate","2015-09-03");
        mvc.perform(post("/api/checkout").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsString("Discount percent")));
    }

    @Test
    void test2_ladder_2020_july() throws Exception {
        Map<String,Object> req = Map.of("toolCode","LADW","rentalDays",3,"discountPercent",10,"checkoutDate","2020-07-02");
        mvc.perform(post("/api/checkout").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toolCode", is("LADW")))
                .andExpect(jsonPath("$.chargeDays", is(2)))
                .andExpect(jsonPath("$.preDiscountCharge", is(3.98)))
                .andExpect(jsonPath("$.discountAmount", is(0.40)))
                .andExpect(jsonPath("$.finalCharge", is(3.58)));
    }

    @Test
    void test3_chainsaw_2015_july() throws Exception {
        Map<String,Object> req = Map.of("toolCode","CHNS","rentalDays",5,"discountPercent",25,"checkoutDate","2015-07-02");
        mvc.perform(post("/api/checkout").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dueDate", is("07/07/15")))
                .andExpect(jsonPath("$.chargeDays", is(3)))
                .andExpect(jsonPath("$.preDiscountCharge", is(4.47)))
                .andExpect(jsonPath("$.discountAmount", is(1.12)))
                .andExpect(jsonPath("$.finalCharge", is(3.35)));
    }

    @Test
    void test4_jackhammer_dewalt_sept2015() throws Exception {
        Map<String,Object> req = Map.of("toolCode","JAKD","rentalDays",6,"discountPercent",0,"checkoutDate","2015-09-03");
        mvc.perform(post("/api/checkout").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dueDate", is("09/09/15")))
                .andExpect(jsonPath("$.chargeDays", is(3)))
                .andExpect(jsonPath("$.preDiscountCharge", is(8.97)))
                .andExpect(jsonPath("$.finalCharge", is(8.97)));
    }

    @Test
    void test5_jackhammer_ridgid_july2015() throws Exception {
        Map<String,Object> req = Map.of("toolCode","JAKR","rentalDays",9,"discountPercent",0,"checkoutDate","2015-07-02");
        mvc.perform(post("/api/checkout").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dueDate", is("07/11/15")))
                .andExpect(jsonPath("$.chargeDays", is(5)))
                .andExpect(jsonPath("$.preDiscountCharge", is(14.95)))
                .andExpect(jsonPath("$.finalCharge", is(14.95)));
    }

    @Test
    void test6_jackhammer_ridgid_july2020_halfOff() throws Exception {
        Map<String,Object> req = Map.of("toolCode","JAKR","rentalDays",4,"discountPercent",50,"checkoutDate","2020-07-02");
        mvc.perform(post("/api/checkout").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dueDate", is("07/06/20")))
                .andExpect(jsonPath("$.chargeDays", is(1)))
                .andExpect(jsonPath("$.preDiscountCharge", is(2.99)))
                .andExpect(jsonPath("$.discountAmount", is(1.50)))
                .andExpect(jsonPath("$.finalCharge", is(1.49)));
    }

    @Test
    void test_add_get_delete_tool() throws Exception {
        mvc.perform(post("/api/tools?code=TEST1&type=LADDER&brand=Acme")).andExpect(status().isOk());
        mvc.perform(get("/api/tools/TEST1")).andExpect(status().isOk()).andExpect(jsonPath("$.brand", is("Acme")));
        mvc.perform(delete("/api/tools/TEST1")).andExpect(status().isOk());
    }

    @Test
    void test_pricing_endpoint() throws Exception {
        mvc.perform(get("/api/pricing")).andExpect(status().isOk()).andExpect(jsonPath("$.LADDER.displayName", is("Ladder")));
    }

    @Test
    void test_system_health_info() throws Exception {
        mvc.perform(get("/api/system/health")).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("UP")));
        mvc.perform(get("/api/system/info")).andExpect(status().isOk()).andExpect(jsonPath("$.app", is("Tool Rental API")));
    }
}

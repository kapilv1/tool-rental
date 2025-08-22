
package com.example.toolrentalapi.controller;

import com.example.toolrentalapi.model.ToolType;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    @GetMapping
    public Map<String, Object> all() {
        return EnumSet.allOf(ToolType.class).stream().collect(Collectors.toMap(ToolType::name, t -> Map.of(
                "displayName", t.getDisplayName(),
                "dailyCharge", t.getDailyCharge(),
                "chargeWeekday", t.isChargeWeekday(),
                "chargeWeekend", t.isChargeWeekend(),
                "chargeHoliday", t.isChargeHoliday()
        )));
    }

    @GetMapping("/{type}")
    public Object forType(@PathVariable("type") String type) {
        ToolType tt = ToolType.valueOf(type.toUpperCase());
        return Map.of("displayName", tt.getDisplayName(), "dailyCharge", tt.getDailyCharge(),
                "chargeWeekday", tt.isChargeWeekday(), "chargeWeekend", tt.isChargeWeekend(), "chargeHoliday", tt.isChargeHoliday());
    }
}

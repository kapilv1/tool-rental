
package com.example.toolrentalapi.model;

import java.math.BigDecimal;

public enum ToolType {
    LADDER("Ladder", new BigDecimal("1.99"), true, true, false),
    CHAINSAW("Chainsaw", new BigDecimal("1.49"), true, false, true),
    JACKHAMMER("Jackhammer", new BigDecimal("2.99"), true, false, false);

    private final String displayName;
    private final BigDecimal dailyCharge;
    private final boolean chargeWeekday;
    private final boolean chargeWeekend;
    private final boolean chargeHoliday;

    ToolType(String displayName, BigDecimal dailyCharge, boolean chargeWeekday, boolean chargeWeekend, boolean chargeHoliday) {
        this.displayName = displayName;
        this.dailyCharge = dailyCharge;
        this.chargeWeekday = chargeWeekday;
        this.chargeWeekend = chargeWeekend;
        this.chargeHoliday = chargeHoliday;
    }
    public String getDisplayName() { return displayName; }
    public BigDecimal getDailyCharge() { return dailyCharge; }
    public boolean isChargeWeekday() { return chargeWeekday; }
    public boolean isChargeWeekend() { return chargeWeekend; }
    public boolean isChargeHoliday() { return chargeHoliday; }
}


package com.example.toolrentalapi.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Formatters {
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance(Locale.US);
    public static String currency(BigDecimal amount) { return CURRENCY.format(amount); }
    public static String percent(int pct) { return pct + "%"; }
}

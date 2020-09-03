package com.tax.salestax.model;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public enum TaxPercent {
    IMPORT_TAX(new BigDecimal(0.05).setScale(2, HALF_UP)), NORMAL_TAX(new BigDecimal(0.1).setScale(2, HALF_UP)), IMPORT_AND_NORMAL_TAX(new BigDecimal(0.15).setScale(2, HALF_UP));

    private BigDecimal value;

    TaxPercent(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

}

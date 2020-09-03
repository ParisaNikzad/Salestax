package com.tax.salestax.service;

import java.math.BigDecimal;

public interface CalculateTax {
    public BigDecimal applyTax(BigDecimal price, BigDecimal enum_tax);
}

package com.tax.salestax.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_UP;

public class Calculator implements CalculateTax{

    public BigDecimal applyQuantityOnPrice(BigDecimal price, int quantity){
        return price.multiply(new BigDecimal(quantity)).setScale(2, HALF_UP);
    }

    @Override
    public BigDecimal applyTax(BigDecimal price, BigDecimal enum_tax){
        return price.multiply(enum_tax).setScale(2, HALF_UP);
    }

    public BigDecimal applyRoundingRules(BigDecimal taxIncludedPrice){
        BigDecimal roundingUp = new BigDecimal(0.05).setScale(2, HALF_UP);
        BigDecimal divided = taxIncludedPrice.divide(roundingUp, 0, RoundingMode.UP);
        return divided.multiply(roundingUp);
    }

}

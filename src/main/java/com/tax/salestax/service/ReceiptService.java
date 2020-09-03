package com.tax.salestax.service;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class ReceiptService {

    public String createPurchaseLine(String PurchaseLine, BigDecimal taxedPrice){
        return PurchaseLine + taxedPrice.setScale(2, HALF_UP);
    }

}

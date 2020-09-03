package com.tax.salestax;

import com.tax.salestax.model.Receipt;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ReceiptTest {

    Receipt receipt = new Receipt();

    @Test
    public void getReceiptTestForSecondOutput(){
        ArrayList<String> purchases =  new ArrayList<String>() ;
        BigDecimal totalPrice= new BigDecimal(0);
        BigDecimal totalTax= new BigDecimal(0);
        receipt.addPurchase("1 imported box of chocolates: 10.50");
        receipt.addTotal(new BigDecimal(10.50));
        receipt.addTax(new BigDecimal(0.50));

        receipt.addPurchase("1 imported bottle of perfume: 54.65");
        receipt.addTotal(new BigDecimal(54.65));
        receipt.addTax(new BigDecimal(7.15));

        Assert.assertEquals("1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65 Total: 65.15", receipt.getReceipt());
    }

    @Test
    public void getReceiptTestForThirdOutput(){
        ArrayList<String> purchases =  new ArrayList<String>() ;
        BigDecimal totalPrice= new BigDecimal(0);
        BigDecimal totalTax= new BigDecimal(0);
        receipt.addPurchase("1 book: 12.49");
        receipt.addTotal(new BigDecimal(12.49));
        receipt.addTax(new BigDecimal(0));

        receipt.addPurchase("1 music CD: 16.49");
        receipt.addTotal(new BigDecimal(16.49));
        receipt.addTax(new BigDecimal(1.5));

        receipt.addPurchase("1 chocolate bar: 0.85");
        receipt.addTotal(new BigDecimal(0.85));
        receipt.addTax(new BigDecimal(0));

        Assert.assertEquals("1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50 Total: 29.83", receipt.getReceipt());
    }




}

package com.tax.salestax.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import static java.math.RoundingMode.HALF_UP;

public class Receipt {

    private static Receipt receipt;

    /**
     * Singleton Class
     *
     * @return Cart instance
     */
    public static Receipt getInstances(){
        if(receipt == null){
            receipt = new Receipt();
        }
        return receipt;
    }

    private ArrayList<String> purchases =  new ArrayList<String>() ;

    private BigDecimal totalPrice= new BigDecimal(0);

    private BigDecimal totalTax= new BigDecimal(0);

    public void addPurchase(String purchase){
        this.purchases.add(purchase);
    }

    public void addTotal(BigDecimal taxedPrice){
        this.totalPrice = this.totalPrice.add(taxedPrice).setScale(2, HALF_UP);
    }

    public void addTax(BigDecimal taxedPrice){
        this.totalTax = this.totalTax.add(taxedPrice).setScale(2, HALF_UP);
    }

    public String getReceipt(){
        StringBuffer receipt = new StringBuffer();
        Iterator iterator = purchases.iterator();
        while(iterator.hasNext()) {
            receipt.append(iterator.next());
            receipt.append("\n");
        }
        receipt.append("Sales Taxes: " + this.totalTax.setScale(2, HALF_UP));
        receipt.append(" Total: " + this.totalPrice.setScale(2, HALF_UP));
        return receipt.toString();
    }
}

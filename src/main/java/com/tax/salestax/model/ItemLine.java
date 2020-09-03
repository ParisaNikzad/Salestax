package com.tax.salestax.model;

import java.math.BigDecimal;

public class ItemLine {

    private int quantity;
    private boolean importTax;
    private boolean normalTax;
    private BigDecimal price;
    private String purchaseQuery;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isImportTax() {
        return importTax;
    }

    public void setImportTax(boolean importTax) {
        this.importTax = importTax;
    }

    public boolean isNormalTax() {
        return normalTax;
    }

    public void setNormalTax(boolean normalTax) {
        this.normalTax = normalTax;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPurchaseQuery() {
        return purchaseQuery;
    }

    public void setPurchaseQuery(String purchaseQuery) {
        this.purchaseQuery = purchaseQuery;
    }

    // only used for writing into the Log
    @Override
    public String toString() {
        return "Item= {" +
                "quantity: " + quantity +
                ", importTax: " + importTax +
                ", normalTax: " + normalTax +
                ", price: " + price +
                '}';
    }

}

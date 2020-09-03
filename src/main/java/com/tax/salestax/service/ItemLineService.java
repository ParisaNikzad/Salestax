package com.tax.salestax.service;

import com.tax.salestax.model.ItemLine;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.math.RoundingMode.HALF_UP;

public class ItemLineService {

    /**
     * To read command and check the importTax and NormalTax
     * @param commandString - the input command
     * @return boolean if the execution is success or not
     */
    public ItemLine createItem(String commandString) throws IllegalArgumentException {

        ItemLine itemLine = new ItemLine();
        StringTokenizer defaultTokenizer = new StringTokenizer(commandString);

        int quantity = 0;
        String token;
        boolean importTax = false;
        boolean normalTax= true;
        BigDecimal price = new BigDecimal(0);
        StringBuilder purchaseString = new StringBuilder();

        //exempted items from regular tax could have benefit from an API that provide all the items
        String[] exemptedItems = {"book", "books" ,"chocolate", "chocolates", "pill", "pills"};


        while (defaultTokenizer.hasMoreTokens())
        {
            token = defaultTokenizer.nextToken().toLowerCase();
            // find quantity
            if(quantity==0 && token.matches("\\d+"))
                quantity = Integer.parseInt(token);

            // find price of item
            else if(price.compareTo(BigDecimal.ZERO) == 0 && token.matches("\\d*\\.\\d+"))
                price = new BigDecimal(token).setScale(2, HALF_UP);

            //check import tax
            else if(token.equals("imported"))
                importTax = true;

            else{
                // check normal tax
                if(Arrays.stream(exemptedItems).anyMatch(token::equals))
                    normalTax = false;

                // preparing item to be written into receipt
                if(!token.equalsIgnoreCase("at"))
                    purchaseString.append(" " + token);
            }
        }

        // preparing item to be written into receipt
        if(importTax)
            purchaseString.insert(0, String.valueOf(quantity)+ " imported");
        else
            purchaseString.insert(0, String.valueOf(quantity));

        purchaseString.append(": ");

        //check the quantity in the command
        if(quantity == 0) throw new IllegalArgumentException("Quantity is not accepted.");

        // check price in the command
        if(price.compareTo(BigDecimal.ZERO) == 0) throw new IllegalArgumentException("Price has to be a positive decimal number.");

        //create item
        itemLine.setQuantity(quantity);
        itemLine.setImportTax(importTax);
        itemLine.setNormalTax(normalTax);
        itemLine.setPrice(price);
        itemLine.setPurchaseQuery(purchaseString.toString());

        return itemLine;
    }

}

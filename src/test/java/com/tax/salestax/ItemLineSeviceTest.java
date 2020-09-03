package com.tax.salestax;

import com.tax.salestax.model.ItemLine;
import com.tax.salestax.service.ItemLineService;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;


public class ItemLineSeviceTest {

    ItemLine itemLine = new ItemLine();
    ItemLineService itemLineService = new ItemLineService();

    @Test
    public void validInputTest() throws IllegalArgumentException {

        itemLine = itemLineService.createItem("1 book at 9.9");
        Assert.assertEquals(1 , itemLine.getQuantity());
        Assert.assertEquals(new BigDecimal(9.9).setScale(2, HALF_UP), itemLine.getPrice());
        Assert.assertEquals("1 book: ", itemLine.getPurchaseQuery());
        Assert.assertFalse(itemLine.isNormalTax());
        Assert.assertFalse(itemLine.isImportTax());
    }

    @Test
    public void testImportedItemRecognition() throws IllegalArgumentException {

        itemLine = itemLineService.createItem("2 book imported at 3.45");
        Assert.assertEquals(2 , itemLine.getQuantity());
        Assert.assertEquals(new BigDecimal(3.45).setScale(2, HALF_UP), itemLine.getPrice());
        Assert.assertEquals("2 imported book: ", itemLine.getPurchaseQuery());
        Assert.assertFalse(itemLine.isNormalTax());
        Assert.assertTrue(itemLine.isImportTax());
    }

    @Test
    public void testNormalTax() throws IllegalArgumentException {

        itemLine = itemLineService.createItem("2 perfume imported at 7.45");
        Assert.assertEquals(2 , itemLine.getQuantity());
        Assert.assertEquals(new BigDecimal(7.45).setScale(2, HALF_UP), itemLine.getPrice());
        Assert.assertEquals("2 imported perfume: ", itemLine.getPurchaseQuery());
        Assert.assertTrue(itemLine.isNormalTax());
        Assert.assertTrue(itemLine.isImportTax());
    }

    @Test
    public void testImportedTax() throws IllegalArgumentException {

        itemLine = itemLineService.createItem("3 box of chocolate at 11.55");
        Assert.assertEquals(3 , itemLine.getQuantity());
        Assert.assertEquals(new BigDecimal(11.55).setScale(2, HALF_UP), itemLine.getPrice());
        Assert.assertEquals("3 box of chocolate: ", itemLine.getPurchaseQuery());
        Assert.assertFalse(itemLine.isNormalTax());
        Assert.assertFalse(itemLine.isImportTax());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testWithNoQuantity() throws IllegalArgumentException {
        try
        {
            itemLine = itemLineService.createItem("book at 9.9");
        }
        catch(IllegalArgumentException re)
        {
            String message = "Quantity is not accepted.";
            Assert.assertEquals(message, re.getMessage());
            throw re;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithZeroQuantity() throws IllegalArgumentException {

        try
        {
            itemLine = itemLineService.createItem("0 book at 7.15");
        }
        catch(IllegalArgumentException re)
        {
            String message = "Quantity is not accepted.";
            Assert.assertEquals(message, re.getMessage());
            throw re;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNegativeQuantity() throws IllegalArgumentException {
        try
        {
            itemLine = itemLineService.createItem("-1 book at 9.9");
        }
        catch(IllegalArgumentException re)
        {
            String message = "Quantity is not accepted.";
            Assert.assertEquals(message, re.getMessage());
            throw re;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNoPrice() throws IllegalArgumentException {
        try
        {
            itemLine = itemLineService.createItem("1 book");
        }
        catch(IllegalArgumentException re)
        {
            String message = "Price has to be a positive decimal number.";
            Assert.assertEquals(message, re.getMessage());
            throw re;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithZeroPrice() throws IllegalArgumentException {
        try
        {
            itemLine = itemLineService.createItem("1 book at 0");
        }
        catch(IllegalArgumentException re)
        {
            String message = "Price has to be a positive decimal number.";
            Assert.assertEquals(message, re.getMessage());
            throw re;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNegativePrice() throws IllegalArgumentException {
        try
        {
            itemLine = itemLineService.createItem("1 book at -1.1");
        }
        catch(IllegalArgumentException re)
        {
            String message = "Price has to be a positive decimal number.";
            Assert.assertEquals(message, re.getMessage());
            throw re;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecimalNumberAsQuantity() throws IllegalArgumentException {
        try
        {
            itemLine = itemLineService.createItem("2.2 book at 9.9");
        }
        catch(IllegalArgumentException re)
        {
            String message = "Quantity is not accepted.";
            Assert.assertEquals(message, re.getMessage());
            throw re;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntegerNumberAsPrice() throws IllegalArgumentException {
        try
        {
            itemLine = itemLineService.createItem("1 book 5");
        }
        catch(IllegalArgumentException re)
        {
            String message = "Price has to be a positive decimal number.";
            Assert.assertEquals(message, re.getMessage());
            throw re;
        }

    }
}

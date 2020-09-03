package com.tax.salestax;

import com.tax.salestax.service.Calculator;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tax.salestax.model.TaxPercent.IMPORT_AND_NORMAL_TAX;
import static com.tax.salestax.model.TaxPercent.IMPORT_TAX;
import static com.tax.salestax.model.TaxPercent.NORMAL_TAX;
import static java.math.RoundingMode.HALF_UP;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void validateGetPriceMultiplyQuantity(){
        Assert.assertEquals(new BigDecimal(19.80).setScale(2, HALF_UP), calculator.applyQuantityOnPrice(new BigDecimal(9.9).setScale(2, HALF_UP), 2));
    }

    @Test
    public void validateImportAndNormalTaxCalculation(){
        Assert.assertEquals(new BigDecimal(7.13).setScale(2, HALF_UP), calculator.applyTax(new BigDecimal(47.5).setScale(2, HALF_UP), IMPORT_AND_NORMAL_TAX.getValue()));
    }

    @Test
    public void validateImportTaxCalculation(){
        Assert.assertEquals(new BigDecimal(0.63).setScale(2, HALF_UP), calculator.applyTax(new BigDecimal(12.5).setScale(2, HALF_UP), IMPORT_TAX.getValue()));
    }

    @Test
    public void validateNormalTaxCalculation(){
        Assert.assertEquals(new BigDecimal(1.26).setScale(2, HALF_UP), calculator.applyTax(new BigDecimal(12.55).setScale(2, HALF_UP), NORMAL_TAX.getValue()));
    }

    @Test
    public void validateApplyRoundingRulesForNotRoundValue(){
        Assert.assertEquals(new BigDecimal(1.05).setScale(2, HALF_UP), calculator.applyRoundingRules(new BigDecimal(1.03).setScale(2, HALF_UP)));
    }

    @Test
    public void validateApplyRoundingRulesForRoundValue(){
        Assert.assertEquals(new BigDecimal(1.05).setScale(2, HALF_UP), calculator.applyRoundingRules(new BigDecimal(1.05).setScale(2, HALF_UP)));
    }

}

package com.tax.salestax.service;

import com.tax.salestax.model.ItemLine;
import com.tax.salestax.model.Receipt;
import com.tax.salestax.config.Log;
import java.math.BigDecimal;

import static com.tax.salestax.model.TaxPercent.*;
import static java.math.RoundingMode.HALF_UP;

/**
 *
 * @author parisanikzad
 */

public class CommandExecutor {
	private static CommandExecutor commandExecutor;
	Log logWrap = Log.getInstance();
	ItemLineService itemLineService = new ItemLineService();
	Calculator calculator = new Calculator();
	Receipt receipt;
	ReceiptService receiptService = new ReceiptService();


	/**
	 * Singleton Class
	 * 
	 * @return CommandExecutor instance
	 */
	public static CommandExecutor getInstance() {
		if (commandExecutor == null) {
			commandExecutor = new CommandExecutor();
		}
		return commandExecutor;
	}

	/**
	 * the main function to process the commands
	 * @param commandString - the input command
	 * @return boolean if the execution is success or not
	 */
	public boolean execute(String commandString) throws IllegalArgumentException {

		ItemLine itemLine = itemLineService.createItem(commandString);
		BigDecimal priceMultiplyQuantity = calculator.applyQuantityOnPrice(itemLine.getPrice(), itemLine.getQuantity());
		BigDecimal itemTax = new BigDecimal(0);

		if(itemLine.isImportTax() && itemLine.isNormalTax()){
			itemTax = calculator.applyTax(priceMultiplyQuantity, IMPORT_AND_NORMAL_TAX.getValue());
		}else if(itemLine.isImportTax()) {
			itemTax = calculator.applyTax(priceMultiplyQuantity, IMPORT_TAX.getValue());
		}else if(itemLine.isNormalTax()) {
			itemTax = calculator.applyTax(priceMultiplyQuantity, NORMAL_TAX.getValue());
		}

		BigDecimal roundedItemTax = calculator.applyRoundingRules(itemTax);

		BigDecimal taxedPrice = priceMultiplyQuantity.add(roundedItemTax ).setScale(2, HALF_UP);
		//writing into log.txt
		logWrap.aLogger.severe(itemLine.toString());
		logWrap.aLogger.severe("Price Multiply Quantity: " + priceMultiplyQuantity.toString());
		logWrap.aLogger.severe("Price with Tax: " + taxedPrice.toString());
		logWrap.aLogger.severe("Tax amount: "+ roundedItemTax.toString());

		receipt = Receipt.getInstances();
		receipt.addPurchase(receiptService.createPurchaseLine(itemLine.getPurchaseQuery(), taxedPrice));
		receipt.addTotal(taxedPrice);
		receipt.addTax(roundedItemTax);
		return true;
	}

}

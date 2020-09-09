package com.tax.salestax;

import com.tax.salestax.model.Receipt;
import com.tax.salestax.service.CommandExecutor;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 *
 * @author parisanikzad
 */

public class SalestaxApplication {

	public static void main(String[] args) throws Exception{

		CommandExecutor commandExecutor = CommandExecutor.getInstance();
		Receipt receipt = Receipt.getInstances();

		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
			while (true) {
				String commandText = bufferedReader.readLine();
				if (commandText.isEmpty() || "exit".equalsIgnoreCase(commandText)) {
					System.out.println(receipt.getReceipt());
					break;
				} else {
					// if execution is not success then exit
					boolean executionSuccess = commandExecutor.execute(commandText);
					if(!executionSuccess) {
						break;
					}
				}
			}
		}

	}

}

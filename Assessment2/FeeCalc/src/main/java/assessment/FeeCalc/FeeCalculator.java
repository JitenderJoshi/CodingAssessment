package assessment.FeeCalc;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Jitender_Joshi
 *
 */
public class FeeCalculator {

	private String inputType;
	private String inputFilePath;

	private List<Transaction> transactions = null;

	public FeeCalculator(String[] args) {
		this.inputType = args[0];
		this.inputFilePath = args[1];
	}

	public void loadTransactions() {
		// The code currently handle only csv format input but can be extended for other input types
		if(this.inputType.equalsIgnoreCase("CSV")) {
			InputParser parser = new CSVInputParser();
			transactions = parser.parseInput(inputFilePath);
		}
	}

	/*
	 * Intra-day transactions are the ones where security is bought & sold on the same day.
	 * Intra-day transactions will have two transactions having same Client Id, Security Id, & Transaction Date 
	 * but opposite Transaction Type i.e. one transaction would be ‘Sell’ ‘& other would be ‘Buy’.
	 * Each ‘intra-day transaction should be charged $10 for both the Buy & Sell legs.
	 * 
	 */
	public void intradayProcessing() {
		for(int index = 0; index < transactions.size() ; index++) {
			Transaction txn1 = transactions.get(index);

			String txntype1 = txn1.getTransactionType();

			//only in case it is buy or sell transaction it can be intraday tranasction
			if(txntype1.equalsIgnoreCase("BUY") || txntype1.equalsIgnoreCase("SELL")) {
				String clientId1 = txn1.getClientId();
				String securityid1 = txn1.getSecurityId();
				Date txndate1 = txn1.getTransactionDate();

				//Now search if this txn is intraday by finding same Client Id, Security Id, & Transaction Date but opposite Transaction Type
				for(int furtherIndex = index+1; furtherIndex < transactions.size() ; furtherIndex++) {
					Transaction txn2 = transactions.get(furtherIndex);

					if(txn2.getClientId().equalsIgnoreCase(clientId1) && 
							txn2.getSecurityId().equalsIgnoreCase(securityid1) && 
							txn2.getTransactionDate().equals(txndate1)) {
						String txntype2 = txn2.getTransactionType();
						
						if((txntype1.equalsIgnoreCase("BUY") && txntype2.equalsIgnoreCase("SELL")) ||
								(txntype1.equalsIgnoreCase("SELL") && txntype2.equalsIgnoreCase("BUY"))) {
							
							//Set the processing fee as $10 for intrday transaction and update the list
							txn1.setProcessingFee(10);
							txn2.setProcessingFee(10);
							
							transactions.set(index, txn1);
							transactions.set(furtherIndex, txn2);
						}
					}
				}
			}
		}
	}

	/*
	 * Fixed fee is charged for normal transactions
	 * $500 for a transaction with high priority 
	 * $100 for a transaction with normal priority and Transaction Type is Sell and Withdraw 
	 * $50 for a transaction with normal priority and Transaction Type Code is Buy and Deposit
	 *  
	 */
	public void normalProcessing() {
		for(int index = 0; index < transactions.size() ; index++) {
			Transaction txn = transactions.get(index);

			//if processing fee is already calculated, that means it was intraday transaction. ignore that.
			if(txn.getProcessingFee() > 0) {
				continue;
			}

			//high priority txn calculate
			if(txn.isPriorityFlag()) {
				txn.setProcessingFee(500);
			}
			else {
				String txntype = txn.getTransactionType();
				
				if(txntype.equalsIgnoreCase("SELL") || txntype.equalsIgnoreCase("WITHDRAW")) {
					txn.setProcessingFee(100);					
				}
				else if(txntype.equalsIgnoreCase("BUY") || txntype.equalsIgnoreCase("DEPOSIT")) {
					txn.setProcessingFee(50);					
				}
			}
			
			//update the list
			transactions.set(index, txn);
		}
	}

	/*
	 * the processing fee should be grouped by client Id, transaction type, Transaction date, & priority flag
	 */
	public void getSummary() {
		List<Transaction> sortedtransactions = transactions.stream()
				.sorted(new TransactionSort()).collect(Collectors.toList());
		
		System.out.println("Client Id | Transaction Type | Transaction Date | Priority | Processing Fee");
		for(Transaction txn : sortedtransactions) {
			System.out.println(txn.printReport());			
		}		
	}

}

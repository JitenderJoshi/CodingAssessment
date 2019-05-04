package assessment.FeeCalc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Object class to represent a single transaction
 * 
 * @author Jitender_Joshi
 *
 */
public class Transaction {
	private String transactionId;
	
	private String clientId;
	
	private String securityId;
	
	private String transactionType;
	
	private Date transactionDate;
	
	private double marketValue;
	
	private boolean priorityFlag;
	
	private double processingFee;

	public Transaction(String[] txnTokens) throws ParseException {
		this.transactionId = txnTokens[0];
		this.clientId = txnTokens[1];
		this.securityId = txnTokens[2];
		this.transactionType = txnTokens[3];
		this.transactionDate = new SimpleDateFormat("MM/dd/yyyy").parse(txnTokens[4]);
		this.marketValue = Double.parseDouble(txnTokens[5]);
		this.priorityFlag = (txnTokens[6].equalsIgnoreCase("Y")) ? true : false;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

	public boolean isPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(boolean priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public double getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(double processingFee) {
		this.processingFee = processingFee;
	}

	@Override
	public String toString() {
		return this.transactionId + " " +
				this.clientId + " " +
				this.securityId + " " +
				this.transactionType + " " +
				this.transactionDate + " " +
				this.marketValue + " " +
				this.priorityFlag + " " +
				this.processingFee;
	}

	public String printReport() {
		return this.clientId + " | " +
		this.transactionType + " | " +
		this.transactionDate + " | " +
		this.priorityFlag + " | " +
		this.processingFee;
	}
}

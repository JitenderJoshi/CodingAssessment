package assessment.FeeCalc;

import java.util.Comparator;

public class TransactionSort implements Comparator<Transaction>{

	// Sort by client Id, transaction type, Transaction date, & priority flag
	@Override
	public int compare(Transaction t1, Transaction t2) {
		if(t1.getClientId().compareTo(t2.getClientId()) == 0) {
			if(t1.getTransactionType().compareTo(t2.getTransactionType()) == 0) {
				if(t1.getTransactionDate().compareTo(t2.getTransactionDate()) == 0) {
		            return Boolean.compare(t1.isPriorityFlag(),t2.isPriorityFlag());
				}
				else {
					return (t1.getTransactionDate().compareTo(t2.getTransactionDate()));
				}
			}
			else {
				return (t1.getTransactionType().compareTo(t2.getTransactionType()));
			}
		}
		else {
			return (t1.getClientId().compareTo(t2.getClientId()));
		}
	}

}

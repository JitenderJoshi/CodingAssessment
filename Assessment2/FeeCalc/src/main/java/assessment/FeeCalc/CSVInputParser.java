package assessment.FeeCalc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVInputParser to handle csv format input
 * 
 * @author Jitender_Joshi
 *
 */
public class CSVInputParser implements InputParser {

	public List<Transaction> parseInput(String filePath) {
		List<Transaction> trans = new ArrayList<>(); 
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			
			//First line of csv file contains the label so ignore that
			br.readLine();
			
			String line;
			while((line = br.readLine()) != null) {
				String [] txnTokens = line.split(",");
				Transaction txn = new Transaction(txnTokens);
				trans.add(txn);			
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return trans;
	}

}

package assessment.FeeCalc;

import java.util.List;

/**
 * Read the inputfile and store the records in a list of Transaction objects
 * 
 * @author Jitender_Joshi
 *
 */
public interface InputParser {
	
	public List<Transaction> parseInput(String filePath);

}

package assessment.FeeCalc;

/**
 * Fee Calculator developed as coding assessment. 
 * 
 * @author Jitender_Joshi
 *
 */
public class App 
{
	/*
	 * The program takes 2 input 
	 * 1st the Input Type - Currently only CSV is handled
	 * 2nd the Input File path
	 */
	public static void main( String[] args )
	{
		if(args.length < 2){
			System.out.println("Usage is - ");
			System.out.println("java -jar FeeCalc-<version>.jar <Input Type> <Input File Path>");
			System.out.println("Currently Supported Inut Type - CSV");
			System.out.println("Exitting......");
			System.exit(0);
		}
		
		FeeCalculator feecalc = new FeeCalculator(args);
		
		//Read the transactions into the system
		feecalc.loadTransactions();
		
		//processing rules over input transactions
		//Intraday Transactions processing
		feecalc.intradayProcessing();
		
		//Normal Transactions processing
		feecalc.normalProcessing();
		
		//Get Summary report
		feecalc.getSummary();
	}
}

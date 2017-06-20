package JUMO.assessment.KimAssessment;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Kim
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException{
    	
    	System.out.println("Start parsing csv file:");
    	List<Loan> list=CSVParser.parseLoans("Loans.csv");
		List<Result> results=Aggregation.aggregate(list);
		Aggregation.outputToFile("output.csv", results);
		System.out.println("Done");
    }
}

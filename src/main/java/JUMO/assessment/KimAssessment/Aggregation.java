package JUMO.assessment.KimAssessment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/**
 * 
 * @author Kim
 *
 */
public class Aggregation {
	/**
	 * I use a hashmap to group by Network, Product and Month tuple 
	 * @param list
	 * @return
	 */
	public static List<Result> aggregate( List<Loan> list ){

		HashMap<String,Result> map=new HashMap<String,Result>();
		for( Loan loan: list ){
			String key=loan.getNetwork()+"," + loan.getProduct() + "," + "'" + loan.getMonth() + "'";
			Result result=map.get( key );
			if( result==null ){			   
				result=new Result( key,0,0 );
			}
			result.incrementCount();
			result.addToTotal( loan.getAmount() );
			map.put( key, result );
		}
		Collection<Result> values=map.values();
		List<Result> results=new ArrayList<Result>( values );
		Collections.sort( results );
		return results;
	}

	public static void outputToFile( String filename, List<Result> list ) throws IOException{

		FileWriter fileWriter=null;
		PrintWriter printWriter=null;
		try {
			fileWriter=new FileWriter( new File( filename ));
			printWriter=new PrintWriter( fileWriter );
			String dictionary="Network,Date,Product,Amount,Count";
			printWriter.println( dictionary );
			for( Result result : list){
				System.out.println( result.toString() );		
				printWriter.println( result.toString() );
			}
		} catch (IOException e) {				
			e.printStackTrace();
		}finally{
			fileWriter.close();
			printWriter.close();
		}		
	}
}

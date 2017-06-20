package JUMO.assessment.KimAssessment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVParser {

	public static List<Loan> parseLoans( String fileName ){ 

		ArrayList<Loan> list = new ArrayList<Loan>();
		Scanner scanner = null;
		try {
			scanner = new Scanner( new FileReader( fileName ));
			String line=scanner.nextLine();

			while( scanner.hasNext() ){
				line=scanner.nextLine();
				String[] parts=line.split(",");
				Loan account=new Loan( parts[0],parts[1],parts[2],parts[3], Double.parseDouble(parts[4]));
				list.add(account);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if( scanner!=null ){
				scanner.close();
			}
		}
		return list;
	}	
}

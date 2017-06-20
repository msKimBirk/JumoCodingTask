package JUMO.assessment.KimAssessment;

public class Result implements Comparable<Object>{
	
	private String key;
	private double total;
	private int count;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public Result(String key, int total, int count ) {
		this.key=key;
		this.total=total;
		this.count=count;
	}
	
	public void incrementCount(){
		this.count++;
	}
	
	public void addToTotal( double amount ){
		this.total+=amount;
	}
	
	@Override
	public String toString(){
		return key+","+String.format( "%.2f",total ) +","+count;
	}

	public int compareTo(Object arg0) {
		return arg0.toString().compareTo( this.toString() );		
	}	
}

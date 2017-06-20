package JUMO.assessment.KimAssessment;
/**
 * 
 * @author Kim
 *
 */
public class Loan {

	private String MSISDN;
	private String network;
	private String date;
	private String product;
	private double amount;
	
	public Loan( String MSISDN, String network, String date, String product, double amount ){
		this.MSISDN=MSISDN;
		this.network=network;
		this.date=date;
		this.product=product;
		this.amount=amount;
	}
	
	public String getMSISDN() {
		return MSISDN;
	}
	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}	
	public String getMonth(){
		String[] parts=date.split("-");
		return parts[1];
	}
}

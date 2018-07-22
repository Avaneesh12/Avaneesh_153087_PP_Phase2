package payment.beans;

public class Customer 
{
	private String customerName;
	private String mobileNo;
	private Wallet wallet; // HAS-A relationship
	public Customer(String customerName, String mobileNo, Wallet wallet) {
		super();
		this.customerName = customerName;
		this.mobileNo = mobileNo;
		this.wallet = wallet;
	}
	
//	public Customer(String customerName, String mobileNo) {
//		super();
//		this.customerName = customerName;
//		this.mobileNo = mobileNo;
//	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", mobileNo=" + mobileNo + ", wallet=" + wallet + "]";
	}
	
	

}

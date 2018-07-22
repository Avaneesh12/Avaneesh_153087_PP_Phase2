package payment.repo;

import payment.beans.Customer;

public interface WalletRepo 
{
	
	public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
}

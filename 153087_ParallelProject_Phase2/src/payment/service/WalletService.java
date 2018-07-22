package payment.service;

import java.math.BigDecimal;

import payment.beans.Customer;
import payment.exception.InsufficientBalanceException;
import payment.exception.InvalidInputException;

public interface WalletService 
{
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) ;
	public Customer depositAmount(String mobileNo, BigDecimal amount);
	public Customer withdrawAmount( String mobileNo, BigDecimal amount) ;
	public Customer fundTransfer(String sourceMobileNo,String targetMobileNo, BigDecimal amount) ;
	public Customer showBalance( String mobileNo) ;

}

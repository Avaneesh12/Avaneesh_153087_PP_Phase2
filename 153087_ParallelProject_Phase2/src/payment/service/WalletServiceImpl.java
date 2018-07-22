package payment.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.activity.InvalidActivityException;

import payment.beans.Customer;
import payment.beans.Wallet;
import payment.exception.InsufficientBalanceException;
import payment.exception.InvalidInputException;
import payment.repo.WalletRepo;
import payment.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService{

	private WalletRepo walletRepo;
	
	
	public WalletServiceImpl(Map<String, Customer> data) {
		walletRepo = new WalletRepoImpl(data);
	}
	
	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) {
		Wallet wallet = new Wallet(amount);
		Customer customer = new Customer(name, mobileNo, wallet);
		
		if (name == null || mobileNo == null || amount == null)
			throw new NullPointerException();
		
		if (!isValidMobile(mobileNo))
			throw new InvalidInputException("Mobile no. should be of 10 digits");

		if (!isValidName(name))
			throw new InvalidInputException("First letter should be capital of name");

		if (!walletRepo.save(customer))
			throw new InvalidInputException(
					"Account already exists with above mobile number:" + customer.getMobileNo());

	
		System.out.println("Account has been successfully created with following details : ");
		System.out.println("Name            :  " + customer.getCustomerName());
		System.out.println("Mobile No.      :  " + customer.getMobileNo());
		System.out.println("Inital Balance  :  " + customer.getWallet().getBalance());
		
		boolean f = walletRepo.save(customer);
		if(f == false)
			throw new InvalidInputException("Mobile already exist.");
		
		return customer;
	}
	@Override
	public Customer showBalance(String mobileNo)  
	{
		Customer customer = walletRepo.findOne(mobileNo);
		if (customer == null)
			throw new InvalidInputException("Mobile number does not exist.");
		
		return customer;

	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer = walletRepo.findOne(mobileNo);
		if(mobileNo == null)
			throw new NullPointerException();
		if(customer == null)
			throw new InvalidInputException("Mobile Number does not exist. " + mobileNo);
		
		Wallet wallet = customer.getWallet();
		BigDecimal prevAmount = wallet.getBalance();
		wallet.setBalance(prevAmount.add(amount));
		customer.setWallet(wallet);
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer = walletRepo.findOne(mobileNo);
		if(mobileNo == null)
			throw new NullPointerException();
		if(customer == null)
			throw new InvalidInputException("Mobile number does not exist." + mobileNo);
		
		Wallet wallet = customer.getWallet();
		BigDecimal prevAmount = wallet.getBalance();
		if(amount.compareTo(prevAmount) == 1)
			throw new InsufficientBalanceException("Insufficient Balance.");
		wallet.setBalance(prevAmount.subtract(amount));
		customer.setWallet(wallet);
		return customer;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo,String targetMobileNo, BigDecimal amount) {
		if(sourceMobileNo.equals(targetMobileNo))
			throw new InvalidInputException("Mobile Number cant be same");
		Customer sourceCustomer = withdrawAmount(sourceMobileNo, amount);
		depositAmount(targetMobileNo, amount);
		System.out.println("Fund Transfer suuccessfully");
		
		return sourceCustomer;
	}

	public boolean isValidMobile(String mobile) {
		String pattern = "[1-9][0-9]{9}";
		if (mobile.matches(pattern))
			return true;

		return false;
	}

	public boolean isValidName(String name) {
		String pattern = "[A-Z][a-z]*";
		if (name.matches(pattern))
			return true;

		return false;
	}

}

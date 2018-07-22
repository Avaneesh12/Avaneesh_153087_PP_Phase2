package payment.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import payment.beans.Customer;
import payment.beans.Wallet;
import payment.exception.InsufficientBalanceException;
import payment.exception.InvalidInputException;
import payment.service.WalletService;
import payment.service.WalletServiceImpl;



public class TestPayment {

	WalletService service;
	
	@Before
	public void initData(){
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Dhoni", "9874563210",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Kohli", "8963201547",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Bhuvi", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9874563210", cust1);
		 data.put("8963201547", cust2);	
		 data.put("9922950519", cust3);	
			service= new WalletServiceImpl(data);
			
	}
	@Test(expected=NullPointerException.class)
	public void checkcreateAccount() throws InvalidInputException, InsufficientBalanceException{
		
		if(service.createAccount(null, null, null)==null);
		throw new NullPointerException();
	}
	
	
	
	
	
	@Test(expected=NullPointerException.class)
	public void testCreateAccountFailed() throws InvalidInputException, InsufficientBalanceException {
		Customer customer=service.createAccount(null, null, null);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateAccountFailed1() throws InvalidInputException, InsufficientBalanceException{
		Customer customer=service.createAccount("avaneesh", "9909000097", null);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateAccountFailed2() throws InvalidInputException, InsufficientBalanceException{		
		Customer customer=service.createAccount("avaneesh", null, new BigDecimal(0));
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateAccountFailed3() throws InvalidInputException, InsufficientBalanceException{		
		Customer customer=service.createAccount(null, "9999999999", new BigDecimal(0));
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccountFailed4() throws InvalidInputException, InsufficientBalanceException{		
		Customer customer=service.createAccount("avaneesh", "9922950519", new BigDecimal(0));
		
	}

	@Test(expected=InsufficientBalanceException.class)
	public void testWithdraw() throws InvalidInputException, InsufficientBalanceException {		
		
		service.withdrawAmount("9922950519", new BigDecimal(8000));
		
		
	}
	
	@Test
	public void testWithdraw1() throws InvalidInputException, InsufficientBalanceException{		
		
		service.withdrawAmount("9922950519", new BigDecimal(200));					
	}
	
	@Test
	public void testShowBalance() throws InvalidInputException, InsufficientBalanceException{		
		
		service.showBalance("9922950519");					
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDeposit() throws InvalidInputException, InsufficientBalanceException{		
		service.depositAmount("9929577597", new BigDecimal(21000));
		
	}
	
	@Test
	public void testDeposit1() throws InvalidInputException, InsufficientBalanceException{		
		Customer customer=service.depositAmount("9874563210", new BigDecimal(1000));
		assertEquals(new BigDecimal(10000),customer.getWallet().getBalance());
		
	}
	
	@Test
	public void testWithdraw2() throws InvalidInputException, InsufficientBalanceException{		
		Customer customer=service.withdrawAmount("9874563210", new BigDecimal(1000));
		assertEquals(new BigDecimal(8000),customer.getWallet().getBalance());
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer() {
		service.fundTransfer("992957443", "976876634", new BigDecimal(1000));
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer2() throws InvalidInputException, InsufficientBalanceException{
		service.fundTransfer("9922950519", "9922950519", new BigDecimal(1000));
	}
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer3() throws InvalidInputException, InsufficientBalanceException{
		service.fundTransfer("9922950519", "9874563210", new BigDecimal(10000));
	}
	
	@Test
	public void testFundTransfer4() throws InvalidInputException, InsufficientBalanceException{
		service.fundTransfer("9922950519", "9874563210", new BigDecimal(1000));
	}
	
	@Test(expected=NullPointerException.class)
	public void testFundTransfer5() throws InvalidInputException, InsufficientBalanceException{
		service.fundTransfer(null, "9874563210", new BigDecimal(1000));
	}
	
	@Test(expected=NullPointerException.class)
	public void testFundTransfer6() throws InvalidInputException, InsufficientBalanceException{
		service.fundTransfer("9874563210", null,  new BigDecimal(1000));
	}
	
	@Test(expected=NullPointerException.class)
	public void testFundTransfer7() throws InvalidInputException, InsufficientBalanceException{
		service.fundTransfer("9874563210", "9922950519",  null);
	}


}

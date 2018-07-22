package payment.pl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

//import com.cg.mypaymentapp.pl.Client;

import payment.beans.Customer;
import payment.service.WalletService;
import payment.service.WalletServiceImpl;

public class Client {

	private WalletService walletService;
	private HashMap <String, Customer> customerData = new HashMap<>();
	public Client()
	{
		walletService = new WalletServiceImpl(customerData);
	}

	Scanner sc = new Scanner(System.in);

	public void menu() {
		System.out.println("------Welcome to Wallet Application-----");
		System.out.println("(1) Create Account");
		System.out.println("(2) Show Balance");
		System.out.println("(3) Deposit Amount");
		System.out.println("(4) Withdraw Amount");
		System.out.println("(5) Fund Transfer");
		System.out.println("(6) Exit");
		System.out.println();

		System.out.print("Enter your choice : ");
		int choice = sc.nextInt();
		System.out.println();
		Scanner input = new Scanner(System.in);
		switch (choice) {
		case 1:
			System.out.print("Enter your name : ");
			String name = input.nextLine();
			
			System.out.print("Enter your Phone Number : ");
			String mobile = input.next();
			
			System.out.print("Enter the initial amount : ");
			BigDecimal amount = input.nextBigDecimal();
			try
			{
				Customer customer = walletService.createAccount(name, mobile, amount);
			}
			catch (Exception e) 
			{
				System.err.println(e);
			}
			
			break;
			
		case 2:
			System.out.print("Enter your Mobile Number : ");
			String mobileNo = input.next();
			try
			{
				Customer customer = walletService.showBalance(mobileNo);

				System.out.println("Your current balance is: " + customer.getWallet().getBalance());
			}
			catch(Exception e)
			{
				System.err.println(e);
			}

			break;
			
		case 3:
			System.out.print("Enter your Mobile Number : ");
			String phoneNo = input.next();
			
			System.out.println("Enter the amount to deposit :");
			BigDecimal deposit = input.nextBigDecimal();
			try
			{
				Customer customer = walletService.depositAmount(phoneNo, deposit);

				System.out.println("Your updated balance is: " + customer.getWallet().getBalance());
			}
			catch(Exception e)
			{
				System.err.println(e);
			}

			break;
			
		case 4:
			System.out.print("Enter your Mobile Number : ");
			String phone= input.next();
			
			System.out.println("Enter the amount to deposit :");
			BigDecimal withdraw = input.nextBigDecimal();
			try
			{
				Customer customer = walletService.withdrawAmount(phone, withdraw);

				System.out.println("Your updated balance is: " + customer.getWallet().getBalance());
			}
			catch(Exception e)
			{
				System.err.println(e);
			}
			break;
			
		case 5:
			System.out.print("Enter source Mobile Number : ");
			String source= input.next();
			
			System.out.print("Enter receiver Mobile Number : ");
			String receiver= input.next();
			
			System.out.println("Enter amount to transfer :");
			BigDecimal transfer = input.nextBigDecimal();
			
			try
			{
				Customer customer = walletService.fundTransfer(source, receiver, transfer);
				
				System.out.println("Receiver updated balance is: " + customer.getWallet().getBalance());
			}
			catch (Exception e) {
				System.err.println();
			}
			
			break;
			
		case 6:
			System.out.println("Thank you for using the application.");
			System.exit(0);
			break;
			
		default:
			System.out.println("Invalid choice.");
			break;
		}

	}

	public static void main(String[] args) {
		
		Client client = new Client();

		while (true) {
			client.menu();
		}
	}

}

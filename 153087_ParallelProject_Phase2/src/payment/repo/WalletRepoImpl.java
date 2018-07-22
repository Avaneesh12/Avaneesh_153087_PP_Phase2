package payment.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import payment.beans.Wallet;

import payment.util.DBUtil;

import payment.beans.Customer;

public class WalletRepoImpl implements WalletRepo {

	private HashMap<String, Customer> customerData;

	public WalletRepoImpl(Map<String, Customer> customerData) {
		this.customerData = (HashMap<String, Customer>) customerData;
	}

	@Override
	public Customer findOne(String mobileNo) {
		
		Customer customer;
		try(Connection con = DBUtil.getConnection() )
		{
			PreparedStatement pstm = con.prepareStatement("select * from Customer where phone_number = ?");
			pstm.setString(1, mobileNo);
			ResultSet res = pstm.executeQuery();
			if (res.next() == false) {
				return null;
			}
			Wallet wallet = new Wallet(res.getBigDecimal(3));
			customer = new Customer(res.getString(2), res.getString(1), wallet);

		}
		catch (Exception e) {
			return null;
		}
		
		return customer;
	}

	@Override
	public boolean save(Customer customer) {
		try (Connection con = DBUtil.getConnection()) {
			// generate empId
			//Statement stm = con.createStatement();
			PreparedStatement pstm = con.prepareStatement("insert into Customer values(?,?,?)");

			
			pstm.setString(1, customer.getMobileNo());
			pstm.setString(2, customer.getName());
			pstm.setBigDecimal(3, customer.getWallet().getBalance());

			pstm.execute();
		} catch (Exception e) {
			
			return false;
		}

		return true;
			
		}
		

		customerData.put(customer.getMobileNo(), customer);
		return true;
	}

}
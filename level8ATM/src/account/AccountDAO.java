package account;

import java.util.ArrayList;

public class AccountDAO {
	
	ArrayList<Account>accList=new ArrayList<Account>();
	final int CLIENT_LIMIT=3;
	
	public void init() {
		accList.add(new Account(1001, "test01", "1111-1111-1111", 8000));
		accList.add(new Account(1002, "test02", "2222-2222-2222", 5000));
		accList.add(new Account(1001, "test01", "3333-3333-3333", 11000));
		accList.add(new Account(1003, "test03", "4444-4444-4444", 9000));
		accList.add(new Account(1001, "test01", "5555-5555-5555", 5400));
		accList.add(new Account(1002, "test02", "6666-6666-6666", 1000));
		accList.add(new Account(1003, "test03", "7777-7777-7777", 1000));
		accList.add(new Account(1004, "test04", "8888-8888-8888", 1000));
	}
	public AccountDAO() {
		// TODO Auto-generated constructor stub
	}
	

}

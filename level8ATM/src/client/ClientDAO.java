package client;

import java.util.ArrayList;

public class ClientDAO {
	
	ArrayList<Client>cliList=new ArrayList<Client>();
	
	public void init() {
		cliList.add(new Client(1001, "test01", "1111", "박연미"));
		cliList.add(new Client(1002, "test02", "2222", "이영희"));
		cliList.add(new Client(1003, "test03", "3333", "신민아"));
		cliList.add(new Client(1004, "test04", "4444", "최상민"));
	}
	

}

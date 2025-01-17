package account;

public class Account {
	
	private int clientNo;
	private String clientId;
	private String accNumber;
	private int money;
	
	public Account(int clientNo, String clientId, String accNumber, int money) {
		this.clientNo = clientNo;
		this.clientId = clientId;
		this.accNumber = accNumber;
		this.money = money;
	}

	public int getClientNo() {
		return clientNo;
	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}

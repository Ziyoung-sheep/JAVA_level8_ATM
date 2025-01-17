package controller;

import account.AccountDAO;
import client.ClientDAO;

public class Controlloer {
	
	private void init() {
		ClientDAO cliDAO=new ClientDAO();
		AccountDAO accDAO=new AccountDAO();
		cliDAO.init();
		accDAO.init();
	}
	
	
	private void printMainMenu() {
		System.out.println("=======딸기뱅크=======");
		System.out.println("[1] 관리자모드");
		System.out.println("[2] 사용자모드");
		System.out.println("[0] 종료");
	}
	
	
	
	
	
	
	
	
	public void run() {
		init();
	}

}

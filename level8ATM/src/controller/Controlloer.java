package controller;

import account.AccountDAO;
import client.ClientDAO;
import utiles.Utiles;

public class Controlloer {
	
	private void init() {
		ClientDAO cliDAO=new ClientDAO();
		AccountDAO accDAO=new AccountDAO();
		cliDAO.init();
		accDAO.init();
	}
	
	private void printMainMenu() {
		System.out.println("=======딸기뱅크=======");
		System.out.println("[1] 관리자 모드");
		System.out.println("[2] 사용자 모드");
		System.out.println("[0] 종료");
	}
	
	private void printManegerMenu() {
		System.out.println("-------관리자모드-------");
		System.out.println("[1] 회원 목록");
		System.out.println("[2] 회원 수정");
		System.out.println("[3] 회원 삭제");
		System.out.println("[4] 데이터 저장");
		System.out.println("[5] 데이터 불러오기");
		System.out.println("[0] 뒤로가기");
	}
	
	private void runManeger() {
		while(true) {
			printManegerMenu();
			try {
				int sel=Utiles.inputIntValse("메뉴선택", 0, 5);
				if (sel==0) {
					break;
				}else if(sel==1) {
					//회원 목록 프린트
				}else if(sel==2) {
					//회원 수정
				}else if(sel==3) {
					//회원 삭제
				}else if(sel==4) {
					//데이터 저장
				}else {
					//데이터 불러오기
				}
			} catch (Exception e) {
				System.err.println("입력 오류");
			}
		}
	}
	
	private void printClientMenu() {
		System.out.println("-------사용자모드-------");
		System.out.println("[1] 회원 가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 뒤로가기");
	}
	
	private void runClient() {
		while(true) {
			printClientMenu();
			try {
				int sel=Utiles.inputIntValse("메뉴선택", 0, 2);
				if (sel==0) {
					break;
				}else if(sel==1) {
					//회원가입
				}else {
					//로그인 시도
					runLogin();
				}
			} catch (Exception e) {
				System.err.println("입력오류");
			}
		}
	}
	
	private void printLoginMenu(String log) {
		System.out.printf("-------%s님 로그인 상태-------",log);
		System.out.println("[1] 계좌 추가");
		System.out.println("[2] 계좌 삭제");
		System.out.println("[3] 입금");
		System.out.println("[4] 출금");
		System.out.println("[5] 이체");
		System.out.println("[6] 탈퇴");
		System.out.println("[7] 마이페이지");
		System.out.println("[0] 로그아웃");
	}
	
	private void runLogin() {
		printLoginMenu(null);
		try {
			int sel=Utiles.inputIntValse("메뉴선택", 0, 7);
		} catch (Exception e) {
			System.err.println("입력오류");
		}
	}
	
	public void run() {
		init();
		while(true){
			printManegerMenu();
			int sel=Utiles.inputIntValse("메뉴선택", 0, 2);
			
			if (sel==0) {
				System.out.println("====================");
				System.err.println("프로그램 종료");
				break;
			}else if(sel==1) {
				runManeger();
			}else {
				runClient();
			}
		}
	}
}

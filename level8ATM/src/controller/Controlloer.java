package controller;

import java.util.ArrayList;

import account.Account;
import account.AccountDAO;
import client.ClientDAO;
import utiles.Utiles;

public class Controlloer {
	
	ClientDAO cliDAO=new ClientDAO();
	AccountDAO accDAO=new AccountDAO();
	int start;
	int end;
	final int accNum=3;
	
	private void init() {
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
				start=0;
				end=5;
				int sel=Utiles.getInstance().inputInt("메뉴선택", start, end);
				if (sel==0) {
					break;
				}else if(sel==1) {
					//회원 목록 프린트
					cliDAO.printClientList();
				}else if(sel==2) {
					//회원 수정
					cliDAO.fixClient();
				}else if(sel==3) {
					//회원 삭제
					cliDAO.delClient();
				}else if(sel==4) {
					//데이터 저장
					cliDAO.saveDataClient();
					accDAO.saveDataAccount();
				}else if(sel==5) {
					//데이터 불러오기
					cliDAO.readDataClient();
					accDAO.readDataAccount();
				}else {
					System.err.println("메뉴 입력 오류");
				}
			} catch (Exception e) {
				Utiles.getInstance().getSc().nextLine();
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
				start=0;
				end=2;
				int sel=Utiles.getInstance().inputInt("메뉴선택", start, end);
				if (sel==0) {
					break;
				}else if(sel==1) {
					//회원가입
					cliDAO.newClient();
				}else if(sel==2){
					//로그인 시도
					int pass=cliDAO.tryLogin();
					if (pass!=-1) {
						runLogin(pass);
					}
				}else {
					System.err.println("메뉴 입력 오류");
				}
			} catch (Exception e) {
				Utiles.getInstance().getSc().nextLine();
				System.err.println("입력오류");
			}
		}
	}
	
	private void printLoginMenu(String log) {
		System.out.printf("-------%s님 로그인 상태-------\n",log);
		System.out.println("[1] 계좌 추가");
		System.out.println("[2] 계좌 삭제");
		System.out.println("[3] 입금");
		System.out.println("[4] 출금");
		System.out.println("[5] 이체");
		System.out.println("[6] 탈퇴");
		System.out.println("[7] 마이페이지");
		System.out.println("[0] 로그아웃");
	}
	
	private void runLogin(int idx) {
		while (true) {
			printLoginMenu(cliDAO.inIdxOutId(idx));
			int loginNum=cliDAO.intIdxOutNum(idx);
			int[]myAccList=accDAO.makeMyAccList(loginNum);
			try {
				start=0;
				end=7;
				int sel=Utiles.getInstance().inputInt("메뉴선택", start, end);
				if (sel==0) {
					System.out.println("로그아웃 완료");
					break;
				}else if(sel==1) {
					if (myAccList.length==accNum) {
						System.err.println("더이상 계좌를 생성할 수 없습니다.");
					}else {
						String myID=cliDAO.inIdxOutId(idx);
						accDAO.addAcc(loginNum, myID);
					}
				}else if(sel==2) {
					//계좌삭제
					if (myAccList.length==0) {
						System.err.println("삭제할 계좌가 없습니다.");
					}else {
						accDAO.printMyAcc(myAccList);
						int pickAcc=Utiles.getInstance().inputInt("삭제할 계좌", 1, myAccList.length);
						accDAO.delAcc(pickAcc, myAccList);
					}
				}else if(sel==3) {
					//입금
					String acc=Utiles.getInstance().inputAccNum("입금할 계좌(0000-0000-0000의 형식에 맞춰 입력하세요)");
					if (acc!=null) {
						int accIdx=accDAO.checkMyAcc(acc, myAccList);
						if (accIdx==-1) {
							System.err.println("본인 계좌가 아닙니다.");
						}else {
							accDAO.inMoney(accIdx, acc);
						}
					}
				}else if(sel==4) {
					//출금
					String acc=Utiles.getInstance().inputAccNum("출금할 계좌(0000-0000-0000의 형식에 맞춰 입력하세요)");
					if (acc!=null) {
						int accIdx=accDAO.checkMyAcc(acc, myAccList);
						if (accIdx==-1) {
							System.err.println("본인 계좌가 아닙니다.");
						}else {
							accDAO.outMoney(accIdx, acc);
						}
					}
				}else if(sel==5) {
					//이체
					String myAcc=Utiles.getInstance().inputAccNum("출금할 계좌(0000-0000-0000의 형식에 맞춰 입력하세요)");
					if (myAcc!=null) {
						int myAccIdx=accDAO.checkMyAcc(myAcc, myAccList);
						if (myAccIdx==-1) {
							System.err.println("본인 계좌가 아닙니다.");
						}else {
							String yourAcc=Utiles.getInstance().inputAccNum("이체할 계좌(0000-0000-0000의 형식에 맞춰 입력하세요)");
							if (yourAcc!=null) {
								if (yourAcc.equals(myAcc)) {
									System.err.println("같은 계좌에는 이체할 수 없습니다.");
								}else {
									int yourAccIdx=accDAO.findAcc(yourAcc);
									if (yourAccIdx==-1) {
										System.err.println("없는 계좌입니다.");
									}else {
										accDAO.spandMoney(myAccIdx, yourAccIdx, myAcc, yourAcc);
									}
								}
							}
						}
					}
				}else if(sel==6) {
					//탈퇴
					String pw=Utiles.getInstance().inputString("PW");
					if (cliDAO.checkPw(idx, pw)) {
						cliDAO.removeMyInfo(idx);
						accDAO.removeMyAcc(myAccList);
						System.out.println("탈퇴되었습니다.");
						break;
					}else {
						System.err.println("PW 오류");
					}
				}else if(sel==7) {
					//마이페이지
					accDAO.printMyAcc(myAccList);
				}else {
					System.err.println("메뉴 입력오류");
				}
			} catch (Exception e) {
				Utiles.getInstance().getSc().nextLine();
				System.err.println("입력오류");
			}
		}
	}
	
	public void run() {
		init();
		while(true){
			printMainMenu();
			try {
				start=0;
				end=2;
				int sel=Utiles.getInstance().inputInt("메뉴선택", 0, 2);
				if (sel==0) {
					System.out.println("====================");
					System.err.println("프로그램 종료");
					break;
				}else if(sel==1) {
					runManeger();
				}else if(sel==2) {
					runClient();
				}else {
					System.err.println("메뉴 입력 오류");
				}
			} catch (Exception e) {
				Utiles.getInstance().getSc().nextLine();
				System.err.println("입력오류");
			}
		}
	}
}

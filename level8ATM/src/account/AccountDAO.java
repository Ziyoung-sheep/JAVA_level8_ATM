package account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import utiles.Utiles;

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
	
	public int[] makeMyAccList(int num) {
		int[]myAccIdx=new int[0];
		for (int i = 0; i < accList.size(); i++) {
			if (accList.get(i).getClientNo()==num) {
				int[]temp=myAccIdx;
				myAccIdx=new int[myAccIdx.length+1];
				for (int j = 0; j < temp.length; j++) {
					myAccIdx[j]=temp[j];
				}
				myAccIdx[myAccIdx.length-1]=i;
			}
		}
		return myAccIdx;
	}
	
	public void printMyAcc(int[]myAccList) {
		for (int i = 0; i < myAccList.length; i++) {
			System.out.println((i+1)+". "+accList.get(myAccList[i]).toString());
		}
	}
	
	private String makeNewAccNum() {
		String temp="";
		for (int i = 0; i < 12; i++) {
//			0 1 2 3 / 4 5 6 7 / 8 9 10 11
			temp+=Utiles.getInstance().getRd().nextInt(10);
			if (i%4==3&&i!=12) {
				temp+="-";
			}
		}
		return temp;
	}
	
	public void addAcc(int num, String id) {
		String newAcc=makeNewAccNum();
		accList.add(new Account(num, id, newAcc, 0));
		System.out.println("새로운 계좌가 생성되었습니다.");
		System.out.printf("새 계좌의 번호는 %s입니다.\n", newAcc);
	}
	
	public void delAcc(int pick, int[]myAccList) {
		accList.remove(myAccList[pick-1]);
		int[]temp=myAccList;
		myAccList=new int[myAccList.length-1];
		//사용자 선택 2
		//인덱스는 1
		//i가 1보다 작으면 그대로 넣고 1보다 크면 뒤에거 하나씩 땡겨넣기
		for (int i = 0; i < myAccList.length; i++) {
			myAccList[i]=i<(pick-1)?temp[i]:temp[i+1];
		}
		System.out.println("계좌삭제 완료");
	}
	
	public int checkMyAcc(String acc, int[]myAccList) {
		int accIdx=-1;
		for (int i = 0; i < myAccList.length; i++) {
			if (accList.get(myAccList[i]).getAccNumber().equals(acc)) {
				accIdx=i;
				break;
			}
		}
		return accIdx;
	}
	
	public void inMoney(int idx, String acc) {
		int money=Utiles.getInstance().inputInt("입금액");
		System.out.printf("%s에 %d원 입금했습니다.\n", acc, money);
		accList.get(idx).setMoney(accList.get(idx).getMoney()+money);
		System.out.printf("총 잔액 %d원\n", accList.get(idx).getMoney());
	}
	
	public void outMoney(int idx, String acc) {
		int money=Utiles.getInstance().inputInt("출금액");
		if (money>accList.get(idx).getMoney()) {
			System.err.println("계좌에 잔액이 부족합니다.");
		}else {
			System.out.printf("%s에 %d원 출금했습니다.\n", acc, money);
			accList.get(idx).setMoney(accList.get(idx).getMoney()-money);
			System.out.printf("총 잔액 %d원\n", accList.get(idx).getMoney());
		}
	}
	
	public int findAcc(String acc) {
		int idx=-1;
		for (int i = 0; i < accList.size(); i++) {
			if (accList.get(i).getAccNumber().equals(acc)) {
				idx=i;
				break;
			}
		}
		return idx;
	}
	
	public void spandMoney(int myIdx, int yourIdx, String myAcc, String yourAcc) {
		int money=Utiles.getInstance().inputInt("이체액");
		if (money>accList.get(myIdx).getMoney()) {
			System.err.println("계좌에 잔액이 부족합니다.");
		}else {
			System.out.printf("%s에서 %s으로 %d원을 이체하였습니다.\n",myAcc, yourAcc, money);
			accList.get(myIdx).setMoney(accList.get(myIdx).getMoney()-money);
			accList.get(yourIdx).setMoney(accList.get(yourIdx).getMoney()+money);
			System.out.printf("내 계좌 잔액 %d원\n",accList.get(myIdx).getMoney());
		}
	}
	
	public void removeMyAcc(int[]myAccList) {
		int restMoney=0;
		for (int i = 0; i < myAccList.length; i++) {
			restMoney+=accList.get(myAccList[i]).getMoney();
			accList.remove(myAccList[i]);
		}
		System.out.printf("예금 %d원이 출금됩니다.\n",restMoney);
	}
	
	public void saveDataAccount() {
		String data="";
		for (int i = 0; i < accList.size(); i++) {
			int no=accList.get(i).getClientNo();
			String id=accList.get(i).getClientId();
			String accNum=accList.get(i).getAccNumber();
			int money=accList.get(i).getMoney();
			data+=no+"\t"+id+"\t"+accNum+"\t"+money+"\n";
		}
		try (FileWriter fw=new FileWriter(Utiles.getFilePath2())){
			fw.write(data);
			System.out.println("저장 성공");
		} catch (Exception e) {
			System.err.println("저장 실패");
			e.printStackTrace();
		}
	}
	
	public void readDataAccount() {
		try (FileReader fr=new FileReader(Utiles.getFilePath2());
				BufferedReader br=new BufferedReader(fr)){
			while(true) {
				String temp=br.readLine();
				if (temp==null) {
					break;
				}
				System.out.println(temp);
			}
		} catch (Exception e) {
			System.err.println("읽기 오류");
		}
	}

}

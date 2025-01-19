package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import utiles.Utiles;

public class ClientDAO {
	
	static ArrayList<Client>cliList=new ArrayList<Client>();
	private int listNum;
	
	public void init() {
		cliList.add(new Client(1001, "test01", "1111", "박연미"));
		cliList.add(new Client(1002, "test02", "2222", "이영희"));
		cliList.add(new Client(1003, "test03", "3333", "신민아"));
		cliList.add(new Client(1004, "test04", "4444", "최상민"));
		listNum=1005;
	}
	
	//클라이언트 넘버 넣으면 아이디 뽑아주는 메소드
	public static String inNumOutId(int num) {
		String Id="";
		for (int i = 0; i < cliList.size() ; i++) {
			if (cliList.get(i).getClientNo()==num) {
				Id=cliList.get(i).getId();
				break;
			}
		}
		return Id;
	}
	
	public void printClientList() {
		for (int i = 0; i < cliList.size(); i++) {
			System.out.println(i+1+". "+cliList.get(i).toString());
		}
	}
	
	private int findIdx(int num) {
		int idx=-1;
		for (int i = 0; i < cliList.size(); i++) {
			if (cliList.get(i).getClientNo()==num) {
				idx=i;
				break;
			}
		}
		return idx;
	}
	
	private int findIdx(String id) {
		int idx=-1;
		for (int i = 0; i < cliList.size(); i++) {
			if (cliList.get(i).getId().equals(id)) {
				idx=i;
				break;
			}
		}
		return idx;
	}
	
	public String inIdxOutId(int idx) {
		return cliList.get(idx).getId();
	}
	
	public int intIdxOutNum(int idx) {
		return cliList.get(idx).getClientNo();
	}
	
	public void fixClient() {
		try {
			int idx=findIdx(Utiles.getInstance().inputInt("회원번호 입력"));
			if (idx==-1) {
				System.err.println("해당 번호의 회원이 없습니다.");
			}else {
				String newId=Utiles.getInstance().inputString("새로운 ID");
				String newPw=Utiles.getInstance().inputString("새로운 PW");
				String newName=Utiles.getInstance().inputString("새로운 이름");
				fixList(idx, newId, newPw, newName);
			}
		} catch (Exception e) {
			System.err.println("번호 입력 오류");
		}
	}
	
	private void fixList(int idx, String newId, String newPw, String newName) {
		cliList.get(idx).setId(newId);
		cliList.get(idx).setPw(newPw);
		cliList.get(idx).setName(newName);
	}
	
	public void delClient() {
		try {
			int idx=findIdx(Utiles.getInstance().inputInt("회원번호 입력"));
			if (idx==-1) {
				System.err.println("해당 번호의 회원이 없습니다.");
			}else {
				delList(idx);
			}
		} catch (Exception e) {
			System.err.println("번호 입력 오류");
		}
	}
	
	private void delList(int idx) {
		cliList.remove(idx);
	}
	
	public void newClient() {
		String id=Utiles.getInstance().inputString("ID");
		int idx=findIdx(id);
		if (idx!=-1) {
			System.err.println("이미 있는 아이디입니다.");
		}else {
			String pw=Utiles.getInstance().inputString("pw");
			String name=Utiles.getInstance().inputString("name");
			addList(id, pw, name);
		}
	}
	
	private void addList(String id, String pw, String name) {
		cliList.add(new Client(listNum, id, pw, name));
		listNum++;
	}
	
	public int tryLogin() {
		int loginIdx=-1;
		String id=Utiles.getInstance().inputString("ID");
		String pw=Utiles.getInstance().inputString("pw");
		
		int idx=findIdx(id);
		if (idx==-1) {
			System.err.println("ID가 없습니다.");
		}else {
			if (cliList.get(idx).getPw().equals(pw)) {
				System.out.println("로그인 성공");
				loginIdx=idx;
			}else {
				System.err.println("로그인 실패. PW를 확인하세요.");
			}
		}
		return loginIdx;
	}
	
	public boolean checkPw(int idx, String pw) {
		if (cliList.get(idx).getPw().equals(pw)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void removeMyInfo(int idx) {
		cliList.remove(idx);
	}
	
	public void saveDataClient() {
		String data="";
		for (int i = 0; i < cliList.size(); i++) {
			int no=cliList.get(i).getClientNo();
			String id=cliList.get(i).getId();
			String name=cliList.get(i).getName();
			data+=no+"\t"+id+"\t"+name+"\n";
		}
		try (FileWriter fw=new FileWriter(Utiles.getFilePath1())){
			fw.write(data);
			System.out.println("저장 성공");
		} catch (Exception e) {
			System.err.println("저장 실패");
		}
	}
	
	public void readDataClient() {
		try (FileReader fr=new FileReader(Utiles.getFilePath1());
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

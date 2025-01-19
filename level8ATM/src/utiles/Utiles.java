package utiles;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.utils;

public class Utiles {
	
	private Utiles() {};
	private static Utiles instance;
	private static final String CUR_PATH=System.getProperty("user.dir")+"\\src\\"+Utiles.class.getPackageName()+"\\";
	private static final String fileName1="Client.txt";
	private static final String fileName2="Account.txt";
	private static String filePath1=CUR_PATH+fileName1;
	private static String filePath2=CUR_PATH+fileName2;
	
	public static String getFilePath1() {
		return filePath1;
	}

	public static String getFilePath2() {
		return filePath2;
	}

	public static Utiles getInstance() {
		if(instance == null) instance = new Utiles();
		return instance;
	}
	
	private Scanner sc=new Scanner(System.in);
	private Random rd=new Random();

	public Scanner getSc() {
		return sc;
	}
	
	public Random getRd() {
		return rd;
	}

	public int inputInt(String msg, int start, int end) {
		System.out.printf("%s(%d-%d)>>", msg, start, end);
		int valse=getSc().nextInt();
		sc.nextLine();
		return valse;
	}
	
	public int inputInt(String msg) {
		System.out.printf("%s>>", msg);
		int valse=getSc().nextInt();
		sc.nextLine();
		return valse;
	}
	
	public String inputString(String msg) {
		System.out.printf("%s>>",msg);
		String valse=getSc().nextLine();
		return valse;
	}
	
	public String inputAccNum(String msg) {
		System.out.printf("%s>>",msg);
		String acc=getSc().nextLine();
		String accPattern="^\\d{4}-\\d{4}-\\d{4}";
		Pattern pattern=Pattern.compile(accPattern); //우리가 검증할 패턴값,형태
		Matcher matcher=pattern.matcher(acc);//검증받을 문자열
		if (matcher.matches()) {
			return acc;
		}else {
			System.err.println("계좌번호가 틀렸습니다. 다시 확인해주세요.");
			return null;
		}
	}

}

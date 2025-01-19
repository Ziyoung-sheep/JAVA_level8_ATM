package utiles;

import java.util.Scanner;

public class Utiles {
	
	private static Scanner sc=new Scanner(System.in);
	
	public static int inputIntValse(String msg, int start, int end) {
		System.out.printf("%s(%d-%d)>>", msg, start, end);
		int valse=sc.nextInt();
		sc.nextLine();
		return valse;
	}
	
	public static int inputIntValse(String msg) {
		System.out.printf("%s>>", msg);
		int valse=sc.nextInt();
		sc.nextLine();
		return valse;
	}
	
	public static String inputStringValse(String msg) {
		System.out.printf("%s>>",msg);
		String valse=sc.nextLine();
		return valse;
	}


}

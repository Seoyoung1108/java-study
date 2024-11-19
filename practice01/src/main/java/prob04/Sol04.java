package prob04;

import java.util.Scanner;

public class Sol04 {

	public static void main(String[] args) {
		
		/* 코드 작성 */
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("문자열을 입력하세요: ");
		String str = scanner.nextLine();
		
		for(int i=0;i<str.length();i++) {
			System.out.println(str.substring(0,i+1));
		}
		scanner.close();
	}
}
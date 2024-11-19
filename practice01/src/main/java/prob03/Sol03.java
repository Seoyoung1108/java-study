package prob03;

import java.util.Scanner;

public class Sol03 {
	
	public static void main(String[] args) {

		/* 코드 작성 */
		
		while(true) {
			int answer = 0;
			Scanner scanner = new Scanner(System.in);
			System.out.print("수를 입력하세요: ");
			int number = scanner.nextInt();
			if(number%2==0) {
				for(int i=2; i<=number ; i=i+2) {
					answer+=i;
				}
			}else {
				for(int i=1; i<=number ; i=i+2) {
					answer+=i;
				}
			}
			System.out.println("결과값: "+answer);
		}
	}
}

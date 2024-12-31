package prob01;

import java.util.Scanner;

public class Sol01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// final: 변수 정의 끝, 대입 불가(상수)
		// 클래스에 final이 붙은 경우: 클래스 확장X, 메소드에 붙은 경우: 오버라이드X
		// 배열 선언 1 - int[] as = {10,20,30,40};
		final int[] MONEYS = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};

		/* 코드 작성 */
		System.out.print("금액: ");
		int number = scanner.nextInt();
		
		for(int i=0; i<MONEYS.length; i++) {
			int money = MONEYS[i];
			int answer = number/money;
			number %= money;
			System.out.println(money+"원: "+answer+"개");
		}
		
		scanner.close();
 	}
}
package prob02;

import java.util.Scanner;

public class Sol02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// 배열 선언 2 - int[] as = new int[사이즈];, 배열은 객체다.
		// (배열 선언 3) - int[] as = new int[]{10,20,30,40};
		int[] intArray = new int[5];
		double sum = 0;

		/* 코드 작성 */
		System.out.println("5개의 숫자를 입력하세요.");
		for(int i=0; i<5;i++) {
			int number = scanner.nextInt();
			intArray[i]=number;
		}
		for(int i=0; i<5;i++) {
			sum+=intArray[i];
		}
		System.out.println("평균은 "+sum/5+"입니다.");
		
		scanner.close();
	}
}

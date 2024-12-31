package exception;

public class UncheckedException {
	public static void main(String[] args) {
		int a[] = {1,2,3,4,5};
		
		for(int i=0;i<=a.length;i++) {
			System.out.println(a[i]);
			//이런건 try-catch 쓸 수 없다.
		}
	}
}

package exception;

import java.io.IOException;

public class MyClassTest {

	public static void main(String[] args) {
		try {
			new MyClass().danger();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error: "+e);
		}catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error: "+e);
		}
		
	}

}
package thread;

public class ThreadEx01 {

	public static void main(String[] args) {
		new AlphabetThread().start();
		
		for(int i=1;i<=9;i++) {
			System.out.print(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//for(char c='a';c<='z';c++) {
		//	System.out.print(c);
		//}

	}

}

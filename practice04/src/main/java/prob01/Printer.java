package prob01;

public class Printer {
	/*
	public void println(int i) {
		System.out.println(i);
	}
	
	public void println(boolean i) {
		System.out.println(i);
	}
	
	public void println(double i) {
		System.out.println(i);
	}
	
	public void println(String i) {
		System.out.println(i);
	}
	*/
	
	// 제너릭 메소드
	public <T> void println(T t) {
		System.out.println(t);
	}
	
	public <T> void println(T... ts) {
		for(T t:ts) {
			System.out.println(t+" ");
		}
		System.out.println("\n");
	}


	public int sum(Integer... nums) {
		// TODO Auto-generated method stub
		int s =0;
		for(Integer n:nums) {
			s+=n;
		}
		return s;
	}
}

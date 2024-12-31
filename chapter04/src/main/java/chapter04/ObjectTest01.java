package chapter04;

public class ObjectTest01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point point = new Point();
		point.getClass();
		
		Class klass = point.getClass(); // reflection
		System.out.println(klass);  // toString 호출
		
		System.out.println(point.hashCode()); // address 기반의 해싱값, int임. 암호화x
											  // address는 아님, reference 값도 아님
		
		System.out.println(point.toString()); // getClass().toString()+'@'+hashCode
		System.out.println(point);

	}

}

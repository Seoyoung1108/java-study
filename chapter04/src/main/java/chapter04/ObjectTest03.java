package chapter04;

public class ObjectTest03 {

	public static void main(String[] args) {
		// String 사용 시 literal로 쓰자. ex) Integer i=10;
		String s1 = new String("hello");
		String s2 = new String("hello");
		
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1.hashCode()+":"+s2.hashCode()); // hashcode 같게 생성(내용 기반으로 override 되어있음.)
		System.out.println(System.identityHashCode(s1)+":"+System.identityHashCode(s2)); // 주소 기반의 Object 타입 해시값
		
		String s3="hello";
		String s4="hello";
		
		System.out.println(s3==s4);
		System.out.println(s3.equals(s4));
		System.out.println(s3.hashCode()+":"+s4.hashCode());
		System.out.println(System.identityHashCode(s3)+":"+System.identityHashCode(s4)); // 주소 기반의 Object 타입 해시값
	}

}

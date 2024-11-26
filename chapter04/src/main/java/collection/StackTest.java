package collection;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		// Stack 자료형은 Vector로 구현되어있음.
		Stack<String> s = new Stack<>();
		
		s.push("둘리");
		s.push("마이콜");
		s.push("또치");

		while(!s.empty()) {
			String str = s.pop();
			System.out.println(str);
		}
		
		// 오류
		// s.pop();
		
		s.push("둘리");
		s.push("마이콜");
		s.push("또치");
		
		System.out.println(s.pop());
		System.out.println(s.peek()); // pop은 안 하고 맨 위에 뭐가 있는지 보여줌.
		System.out.println(s.pop());
	}

}

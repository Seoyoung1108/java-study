package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> m = new HashMap<>();
		
		m.put("one", 1); // auto boxing
		m.put("two", 2);
		m.put("three", 3);
		
		int i = m.get("one"); // auto unboxing
		int j = m.get(new String("two")); // 동일
		System.out.println(i+":"+j);
		
		m.put("three", 3333);
		System.out.println(m.get("three")); // value 변경
		
		// 순회
		Set<String> s = m.keySet();
		for(String key:s) {
			System.out.println(m.get(key));
		}
	}

}

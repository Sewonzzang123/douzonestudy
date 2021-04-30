package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		Set<String> s = new HashSet<String>();
		String s1 = new String("도우너");
		String s2 = new String("도우너");
		
		s.add("둘리");
		s.add("마이콜");
		s.add("희동이");
		s.add(s1);
		
		//순회를 해서 가져와야 됨
		//list와 다른게 set 안에 있냐 없냐
		System.out.println(s.size());
		System.out.println(s.contains(s2));
		
		// 순회
		Iterator<String> it = s.iterator();
		while(it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
	}

}

package chapter04;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		Set<Rect> set = new HashSet<>();
		
		Rect r1 = new Rect(10,20);
		Rect r2 = new Rect(10,20);
		
		set.add(r1);
		set.add(r2);
		
		//Rect클래스의 hashCode()를 주석처리 하였을 때, r1,r2모두 set에 들어가는게 보임
		for(Rect rect: set) {
			System.out.println(rect);
		}
	}

}

package chapter04;

public class ObjectTest03 {

	public static void main(String[] args) {
		String s1 = new String("ABC");
		String s2 = new String("ABC");
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1.hashCode() + ":"+ s2.hashCode());
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		
		System.out.println("==============================");
		
		String s3 = "ABC";
		String s4 = "ABC";
		
		System.out.println(s3 == s4); //이건 왜 true???? >>> 
		System.out.println(s3.equals(s4));
		System.out.println(s3.hashCode() + ":"+ s4.hashCode());
		System.out.println(System.identityHashCode(s3));
		System.out.println(System.identityHashCode(s4));
		
		Integer i = 10;
		Integer j = 10;
		//불변 객체 => 값 변경하는 함수가 없음
		
	}

}

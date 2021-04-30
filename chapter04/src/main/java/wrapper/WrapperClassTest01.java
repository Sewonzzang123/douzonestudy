package wrapper;

public class WrapperClassTest01 {

	public static void main(String[] args) {
		Integer i = new Integer(10);
		Character c = new Character('c');
		Boolean b = new Boolean(true);
		
		//Auto Boxing
		Integer j1 = 10;
		Integer j2 = 10;		
		System.out.println(j1==j2);
		System.out.println(j1==i);
		
		//Auto Unboxing
		//int j3 = j1.intValue();
		int j3 = j1;
		
		
	}

}

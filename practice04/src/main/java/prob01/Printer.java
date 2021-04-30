package prob01;

public class Printer {
	
//	public void println(int i) {
//		System.out.println(i);
//	}
//	public void println(boolean bool) {
//		System.out.println(bool);
//	}
//	public void println(double dou) {
//		System.out.println(dou);
//	}
//	public void println(String str) {
//		System.out.println(str);
//	}
	
	//제너릭을 써보자 <T>: return type x
	public <T> void println(T t) {
		System.out.println(t);
	}
	
	public <T> void println(T...ts) {
		for(T t: ts) {
		System.out.print(t);
		System.out.print(" ");
		}
		System.out.print("\n");
	}
	
	public int sum(Integer...nums) {
		Integer sum = 0;
		for(Integer i : nums) {
			sum += i;
		}
		return sum;
	}
}

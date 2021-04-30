package chapter03;

public class MyClass {
	private static MyClass instance;
	private MyClass() {
	}
	
	//singleton +Factory Method
	//singleton: 객체 단 하나를 유지
	public static MyClass getInstance() {
		if(instance == null) {
			instance = new MyClass();
		}	
		return instance;
	}
}

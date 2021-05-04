package network.thread;

public class ThreadEx02 {

	public static void main(String[] args) {
		//main이 종료된다 하더라도 다른 thread가 계속 있으면 계속됨 !
		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabetThread();
		
		thread2.start();
		thread1.start();

	}

}

package network.thread;

public class ThreadEx01 {

	public static void main(String[] args) {
//		for(int i=1; i<9; i++) {
//			System.out.print(i);
//		}
		//선점하려면 잠 재우기 sleep();
		new DigitThread().start();
		
		for(char c='a'; c<='z'; c++) {
			System.out.print(c);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

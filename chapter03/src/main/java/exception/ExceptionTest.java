package exception;

/**
 * @author Sewonzzang123
 * try~catch~finally 구문 만들기 연습
 */
public class ExceptionTest {

	public static void main(String[] args) {
		int a= 10;
		//double b = 100/a;
		int c = 10 - a;
		
		System.out.println("Some more codes ...1");	
		
		try {			
			System.out.println("Some more codes ...2");
			//예외가 없다면 1 2 3 4 5 순으로 출력됨
			int result = (1+2+3)/c;			
			System.out.println("Some more codes ...3");
		}catch(ArithmeticException e) {
			/* 예외 처리
			 * catch문을 안적어 놓으면 나쁜 코드가 된다 !
			 * 1. 사과 */
				System.out.println("미안합니다.");
			// 2. 로깅
				System.out.println("error: "+e);
			// 3. 정상 종료 -> finally까지 실행됨!
				return;
		}finally {
			System.out.println("Some more codes ..final(자원정리)");
		}
		
		System.out.println("Some more codes ...5");
		

	}

}

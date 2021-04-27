package prob05;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		
		while( true ) {
			
			/* 게임 작성 */
			System.out.println("수를 결정합니다. 맞추어 보세요");
			boolean correct = false;
			
			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt( 100 ) + 1;
			//System.out.println(correctNumber);
			
			/*사용자 숫자 입력*/
			int minNumber = 1;
			int maxNumber = 100;
			int cnt = 0;
			System.out.println(minNumber+"~"+maxNumber);
			
			while(!correct) {
				cnt++;
				System.out.print(cnt +">>");
				int number = scanner.nextInt();
			if(number == correctNumber) {
				System.out.println("맞췄습니다.");
				correct = true;
			}else if(number > correctNumber){
				System.out.println("더 낮게");
				maxNumber = number;
				System.out.println(minNumber+"~"+maxNumber);				
			}else{
				System.out.println("더 높게");
				minNumber = number;
				System.out.println(minNumber+"~"+maxNumber);
				}
			}
			
			//새 게임 여부 확인하기
			System.out.print( "다시 하겠습니까(y/n)>>" );
			String answer = scanner.next();
			if( "y".equals( answer ) == false ) {
				break;
			}
		}
		
		scanner.close();
	}

}
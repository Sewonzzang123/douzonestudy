package prob01;

import java.util.Scanner;

public class GugudanTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Gugudan[] gugus = makeNineGugu();
		Gugudan quiz = gugus[(int)(Math.random()*9)];
		System.out.println(quiz.getLeft()+" x "+quiz.getRight()+" = ?");
		
		
		for(int i=1; i<4; i++) {
			for(int j=1; j<4; j++) {
				System.out.print(gugus[(i*j)-1].multi()+" ");			
			}
			System.out.println();
		}
		
		System.out.print("answer: ");
		int answer = scanner.nextInt();
		if(quiz.result() == answer) {
			System.out.println("정답");
		}else {
			System.out.println("오답");
		}
		
	}
	
	public static Gugudan[] makeNineGugu() {		
		Gugudan[] gugus = new Gugudan[8];
		boolean max = false;
		
		while(!max) {
			int i=1;
			Gugudan gugu = new Gugudan
					((int)(Math.random()*9)+1,(int)(Math.random()*9)+1);
			for(int j=0; j<gugus.length; j++) {				
					if(gugus[j].equals(gugu)) {
						j--;
					}else {
						gugus[i] = gugu;
						i++;
						if(i==gugus.length) {
							max = true;
							break;
						}
					}
				
			}
			
			
			
		}
		
		return gugus;
	}

}

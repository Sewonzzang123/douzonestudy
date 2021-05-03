package prob01;

import java.util.Scanner;

public class GugudanTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Gugudan[] gugus = makeNineGugu();
		Gugudan quiz = gugus[(int)(Math.random()*9)];
		System.out.println(quiz.getLeft()+" x "+quiz.getRight()+" = ?");
		
		for(int k=0; k<9; k++) {
			System.out.println(gugus[k].result());
		}
		for(int i=1; i<4; i++) {
			for(int j=3; j>0; j--) {
				System.out.print(gugus[(i*3)-j].multi()+" ");
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
		Gugudan[] gugus = new Gugudan[9];
		boolean max = false;
		int i=0;
		while(!max) {		
			boolean equals = true;
			Gugudan gugu = null;
			while(equals) {
				gugu = new Gugudan
					((int)(Math.random()*9)+1,(int)(Math.random()*9)+1);
				//겹치는거 없게 비교하기
				for(int j=0; j<gugus.length; j++) {
					//0자리에 없으면 0바로 채우기
					if(gugus[j] == null) {
						System.out.println("not equals");
						equals = false;
						break;
					}else if((gugus[j] != null)&&(gugus[j].equals(gugu))) {
						System.out.println("equals");
						equals = true;
						break;
					}
				}
			}			
			if(i<9) {
				gugus[i] = gugu;
				i++;				
			}else{
				max = true;
			}
		}
		
		return gugus;
	}

}

package string;

public class StringTest03 {

	public static void main(String[] args) {
		String s = "abcAbcaBcabCABC";
		
		System.out.println(s.length());
		System.out.println(s.charAt(2));
		System.out.println(s.indexOf("abC"));
		System.out.println(s.indexOf("abC",3));
		System.out.println(s.substring(3));
		System.out.println(s.substring(3,5));
		
		String s2 = "    ab         cd   ";
		String s3 = "efg,hijk,lmn,opqr";
		
		String s4 = s2.concat(s3);
		System.out.println(s4);
		
		//trim이 왜 있을까??
		//
		System.out.println("========"+s2.trim()+"===========");
		System.out.println(s2.replaceAll(" ", ""));
		
		//정규 표현식 사용 예
		//웹에서 가져오면 다 String형태야 
		String p = "123456";
		
		if(p.matches("\\d+")) {
			int price = Integer.parseInt(p);
			System.out.println(price);
		}else {
			System.out.println("유효한 금액이 아닙니다.");
		}
		
		System.out.println("======split=======");
		String[] tokens = s3.split(",");
		for(String token: tokens) {
			System.out.println(token);
		}
	
		//정규 표현식도 되네??
		String[] tokens2 = s3.split(" ");
		for(String token: tokens2) {
			System.out.println(token);
		}
		
		System.out.println("======StringBuffer=======");
		//StringBuffer
		StringBuffer str = new StringBuffer("");
		str.append("Hello ");
		str.append("world!");
		str.append("java");
		str.append(1.8);
		System.out.println(str);
		
		String s5 = str.toString();
		System.out.println(s5);
		
		//String s6 = "Hello "+"world!"+"java"+1.8;
		//메소드 chain
		String sb1 = new StringBuffer("Hello ").append("world!")
					.append("java").append(1.8).toString();
		System.out.println(sb1);
		
		//	주의!
		//	문자열 + 연산을 하지 말아야 할 때
		//	new 때문에 시간이 오래 걸리게 됨
		String s6 = "";
		for(int i=0; i<1000000; i++) {
			//s6 += i;
			//s6 =  new StringBuffer(s6).append(i).toString();
		}
		
		//==>
		StringBuffer sb2 = new StringBuffer("");
		for(int i=0; i<1000000; i++) {
			sb2.append(i);
		}
		String s7 = sb2.toString();
		System.out.println(s7.length());
	}

}

package io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PhoneList02 {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			// 파일 (정보)
			File file = new File("phone.txt");

			if (!file.exists()) {
				System.out.println("file not found");
				return;
			}

			System.out.println("==========파일 정보============");
			System.out.println(file.getAbsolutePath());// 원 주소
			System.out.println(file.length() + "bytes");// 길이
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));
			System.out.println("==========전화 번호============");
			
			//scanner을 쓰겠다
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String name = scanner.next();
				String phone1 = scanner.next();
				String phone2 = scanner.next();
				String phone3 = scanner.next();
				
				System.out.println(name +": "+phone1+"-"+phone2+"-"+phone3);
			}
		} catch (IOException e) {
			// filenotfoundException이 ioexception의 자식이기 때문에 io만해도 오류x
			System.out.println("error: " + e);
		} finally {
			if(scanner != null) {
				scanner.close();
			}
		}
	}

}

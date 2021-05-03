package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {
		BufferedReader br = null;
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
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//timestamp
//		String lastModifiedDate = sdf.format(new Date(file.lastModified()));
//		String lastModifiedDate = sdf.format(new Date(file.lastModified()));
//		System.out.println(lastModifiedDate);
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));
			System.out.println("==========전화 번호============");

			// 1.기반 스트림
			FileInputStream fis = new FileInputStream(file);

			// 2.보조 스트림1(byte->char)
			InputStreamReader isr = new InputStreamReader(fis);

			// 3.보조 스트림2(char|char|char|\n->"charcharchar")
			br = new BufferedReader(isr);

			// 4.처리
			String line = null;
			while((line = br.readLine())!=null) {
//				System.out.println(line);
				//tab, space로 쪼개겠다
				StringTokenizer st = new StringTokenizer(line, "\t ");
				
				int index = 0;
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					
					if(index == 0) { //이름
						System.out.print(token+":");
					}else if(index == 1){	//전화번호 1
						System.out.print(token+"-");
					}else if(index ==2) {	//전화번호 2
						System.out.print(token+"-");
					}else {					//전화번호 3
						System.out.println(token);
					}
					index++;
				}
			}
		} catch (IOException e) {
			// filenotfoundException이 ioexception의 자식이기 때문에 io만해도 오류x
			System.out.println("error: " + e);
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

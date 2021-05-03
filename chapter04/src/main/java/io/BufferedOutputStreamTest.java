package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {

	public static void main(String[] args) {
		BufferedOutputStream bos = null;
		try {
			FileOutputStream fis = new FileOutputStream("test.txt");
			bos = new BufferedOutputStream(fis);
			//for(int i = 'a'; i < 'z'; i++)
			//ascii 'a':97 'z':122
			for(int i = 'a'; i <= 'z'; i++) {
				bos.write(i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("cannot open: "+e);
		} catch (IOException e) {
			System.out.println("error: "+e);
		} finally {
			try {
				if(bos != null) {
					bos.close();
				}		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

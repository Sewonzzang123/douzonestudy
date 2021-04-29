package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 
 * @author Sewonzzang123
 * 중요한 줄은 세줄인데
 * fis = new FileInputStream("hello.txt");
 * int data = fis.read();
 * fis.close();
 * 
 * spring가면 runtime exception, aop사용해서 간편해짐 !
 */
public class FileTest {

	public static void main(String[] args) {
		FileInputStream fis = null;
		//boilerplate code
		try {
			fis = new FileInputStream("hello.txt");
			int data = fis.read();
			System.out.println(data);
		} catch (FileNotFoundException e) {
			System.out.println("error: "+e);
		} catch (IOException e) {
			System.out.println("error: "+e);
		}finally {
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("error: "+e);
			}
		}
	}
}

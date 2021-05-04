package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		// 시스템에서 자기자신을 localhost라고 한다.
		// 밖에 나가지 않고 안에서(tcp/ip) 돌게 하는 가상의 주소 127.0.0.1
		//C:\Windows\System32\drivers\etc
		
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			//컴퓨터 이름
			String hostName = inetAddress.getHostName();
			System.out.println(hostName);
			
			//ip
			String hostAddress = inetAddress.getHostAddress();
			System.out.println(hostAddress);
			
			//byte단위로
			byte[] byteAddress = inetAddress.getAddress();
			for(byte address:byteAddress) {
				System.out.print(address&0x000000ff);
				System.out.print(".");
			}
			System.out.println();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		

	}

}

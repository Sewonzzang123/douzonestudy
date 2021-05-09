package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import echo.EchoServer;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = ChatServer.PORT;

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;
		String nickname=null;
		try {
			// 1. Scanner 생성
			scanner = new Scanner(System.in);

			// 2. 소켓 생성
			socket = new Socket();

			// 3.서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

			// 4. IO Stream 받아오기
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			// 5. join 프로토콜
			System.out.print("닉네임>>");	
			nickname = scanner.nextLine();
			printWriter.println("join:" + nickname);		
			String line = bufferedReader.readLine();
			// ack ok가 오면			
			if( "join:ok".equals( line ) ) {
				System.out.println( "입장하였습니다. 즐거운 채팅 되세요" );
			}
			new ChatClientThread(socket).start();

			
			
			// 7. 키보드 입력 처리
			while (true) {
				if(scanner.hasNextLine() == false) {
					continue;
				}
				System.out.print(">>");
				String input = scanner.nextLine();

				if ("quit".equals(input) == true) {
					// 8. quit 프로토콜 처리
					printWriter.println("quit");					
					break;
				} 
				
				if("".equals(input)==false){
					// 9. 메시지 처리
					printWriter.println("message:"+input);					
				}
			}

		} catch (SocketException e) {
			log(nickname," :suddenly closed by server");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void log(String nickName,String log) {
		System.out.println("["+nickName+"]: " + log);
	}
}

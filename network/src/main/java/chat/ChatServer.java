package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatServer {
	public static final int PORT = 7000;

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		ServerSocket serverSocket = null;
		List<PrintWriter> listWriters = new ArrayList<PrintWriter>();
		
		try {
			// 1. 키보드 연결
			scanner = new Scanner(System.in);
			// 2. socket 생성
			serverSocket = new ServerSocket();

			// 3. 연결
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));

			String remoteHostAddress = InetAddress.getLocalHost().getHostAddress();
			log("connected by client[" + remoteHostAddress + ":" + PORT + "]");

			while (true) {
				socket = serverSocket.accept();
				Thread thread = new ChatServerThread(socket);
				thread.start();
				
				// 4. reader/writer 생성
//				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
//				// 버퍼가 꽉 차있지 않더라도 flush 해주는 것
//				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"),
//						true);

				// 5. join 프로토콜
//				System.out.print("닉네임>>");
//				String nickname = scanner.nextLine();
//				printWriter.println("join:" + nickname);
//				String line = br.readLine();
//				// ack ok가 오면
//				printWriter.flush();

				// 6. ChatClientReceiveThread 시작				
//				new ChatClientThread(socket).start();
				
				// 7. 키보드 입력 처리
//				while (true) {
//					System.out.print(">>");
//					String input = scanner.nextLine();
//
//					if ("quit".equals(input) == true) {
//						// 8. quit 프로토콜 처리
//						break;
//					} else {
//						// 9. 메시지 처리
//
//					}
//				}
			}
		} catch (IOException ex) {
			log("error:" + ex);
		} finally {
			// 10. 자원정리
			
		}

	}

	public static void log(String message) {
		System.out.println(message);

	}

}

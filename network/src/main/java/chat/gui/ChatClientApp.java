package chat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import chat.ChatServer;

public class ChatClientApp {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = ChatServer.PORT;

	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);
		Socket socket = null;
		try {
		// 1. create socket
		socket = new Socket();
		// 2. connect to server
		socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

		// 3. create IO stream
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

		while (true) {

			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();

			if (name.isEmpty() == false) {
				// ack보내야해
				printWriter.println("join:" + name);
				break;
			}

			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
			// 4. join 프로토콜
			String line = bufferedReader.readLine();
			if ("join:ok".equals(line)) {
				new ChatWindow(name,socket).show();
			}
			
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

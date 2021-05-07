package chat;

import java.io.IOException;
import java.io.Writer;
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
		List<Writer> listWriters = new ArrayList<Writer>();

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
				Thread thread = new ChatServerThread(socket,listWriters);
				thread.start();
			}
		} catch (IOException ex) {
			log("error:" + ex);
		} finally {
			try {
				// 10. 자원정리
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void log(String message) {
		System.out.println(message);

	}

}

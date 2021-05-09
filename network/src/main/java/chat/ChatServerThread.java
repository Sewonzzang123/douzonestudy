package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServerThread extends Thread {
	// 연결된 클라이언트의 닉네임을 저장하고 있어야 한다.
	// 1. Remote Host Information
	private String nickName;
	private Socket socket;
	private List<Writer> listWriters;
	
	
	public ChatServerThread(Socket socket) {
		this.socket = socket;
	}

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader=null;
		Writer printWriter=null;
		try {		
			ChatServer.consoleLog(
					"connected by client[" +
					remoteSocketAddress.getAddress().getHostAddress() + ":" + 
					remoteSocketAddress.getPort() +
					"]" );
			
			
			// 2. 스트림 얻기
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			// 3. 요청 처리
			while (true) {
				String request = bufferedReader.readLine();
				if (request == null) {
					ChatServer.log("클라이언트로부터 연결 끊김");
					break;
				}
				// 4. 프로토콜 분석
				String[] tokens = request.split(":");
				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], printWriter);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(printWriter);
				} else {
					ChatServer.log("에러: 알수 없는 요청(" + tokens[0] + ")");
				}
			}
		}catch(SocketException e) {
			doQuit(printWriter);
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			doQuit(printWriter);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void doQuit(Writer writer) {
		removeWriter(writer);
		if(name != null) {
			String data = nickName + "님이 퇴장 하였습니다.";
			broadcast(data);
		}
		
	}

	private void doMessage(String message) {
		/* 잘 구현해보기 */
		String data = nickName+":"+message;
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		/*
		 * 잘 구현해보기 현재 스레드의 writer를 Writer pool에서 제거한 후 브로드캐스팅 한다.
		 */
		synchronized (listWriters) {
				listWriters.remove(writer);
		}
	}

	

	private void doJoin(String nickName, Writer writer) throws UnsupportedEncodingException, IOException {
		PrintWriter printWriter = (PrintWriter)writer;

		this.nickName = nickName;

		String data = nickName + " 님이 참여하였습니다.";
		broadcast(data);

		/* writer pool에 저장 */		
		addWriter(writer);
		printWriter.println("join:ok");// ack
		

	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
			}
		}
	}

	private void log(String message) {
		System.out.println(nickName + ": " + message);

	}

}

package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class TCPClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {
		Socket socket = null;
		try {

			// 1. 소켓 생성
			socket = new Socket();

			// 1-1. 소켓 버퍼사이즈 확인
			int receiveBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			System.out.println("[clinet] " + receiveBufferSize + " : " + sendBufferSize);

			// 1-2. 버퍼 사이즈 변경
			socket.setReceiveBufferSize(1024 * 10); // 10k
			socket.setSendBufferSize(1024 * 10); // 10k

			receiveBufferSize = socket.getReceiveBufferSize();
			sendBufferSize = socket.getSendBufferSize();
			System.out.println("[clinet] " + receiveBufferSize + " : " + sendBufferSize);

			// 1-3. so(cket)_nodelay(Nagle Algorithm off): 대용량 파일을 빨리 보내기 위함
			socket.setTcpNoDelay(true);

			// 1-4.so_timeout : 데이터 읽기에 타임아웃 설정(잘쓰지않아)
			socket.setSoTimeout(3000);

			// 2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[client] connected by server[" + SERVER_IP + ":" + SERVER_PORT + "]");

			// 3. IO Stream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// 4. 쓰기
			String data = "hello World\n";
			os.write(data.getBytes("utf-8"));

			// 5. 읽기
			byte[] buffer = new byte[255];
			int readByteCount = is.read(buffer);// blocking
			if (readByteCount == -1) {
				System.out.println("[client] closed by server");
				return;
			}

			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received : " + data);

		} catch (SocketTimeoutException e) {
			System.out.println("[client] timeout");
		} catch (SocketException e) {
			System.out.println("[client] suddenly closed by server");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

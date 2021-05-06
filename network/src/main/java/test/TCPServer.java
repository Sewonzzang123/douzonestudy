package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null; // close를 위해

		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(Binding)
			// Socket에 InetSocketAddress(IPAddress + Port)
			// IPAddress : 0.0.0.0 :모든 IP 연결 허용
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000));

			// 3. accept
			// 클라이언트의 연결 요청을 기다린다.
			Socket socket = serverSocket.accept(); //Blocking

			// remotesocketAddress는 cilent에서 생성된 socket
			// 다운캐스팅
			InetSocketAddress inetRemoteScoketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

			String remoteHostAddress = inetRemoteScoketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteScoketAddress.getPort();

			System.out.println(
					"[server] connected by client[" + 
							remoteHostAddress + ":" + 
							remoteHostPort + "]");
			
			try {
				//socket별로 ioException을 나눌 필요가 있다
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				while(true) {
					// 4. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); //blocking
					
					if(readByteCount == -1) {
						//client가 정상적으로 종료 : close신호 4개 왔다갔다 해야 함
						System.out.println("[server] closed by client");
						break;
					}
					
					//byte->String
					String data = new String(buffer,0,readByteCount,"utf-8");
					System.out.println("[server] received: "+ data);
					
					// 5. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
										
				}
			}catch(SocketException e) {
				System.out.println("[server] suddenly closed by client");
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(socket != null && socket.isClosed() ==false) {
						socket.close();
					}		
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

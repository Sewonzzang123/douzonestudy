package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServerReceiveThread extends Thread {
	private Socket socket;
	
	public EchoServerReceiveThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// remotesocketAddress는 cilent에서 생성된 socket
					// 다운캐스팅
					InetSocketAddress inetRemoteScoketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

					String remoteHostAddress = inetRemoteScoketAddress.getAddress().getHostAddress();
					int remoteHostPort = inetRemoteScoketAddress.getPort();

					EchoServer2.log("connected by client[" + remoteHostAddress + ":" + remoteHostPort + "]");

					try {
						// 3. IO Stream 받아오기
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
						//버퍼가 꽉 차있지 않더라도 flush 해주는 것
						PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true); 
						
						while (true) {
							// 4. 데이터 읽기
							String data = br.readLine();
							if(data == null) {
								EchoServer2.log("colsed by client");
								break;
							}
							EchoServer2.log("received: " + data);

							// 5. 데이터 쓰기 (개행이 붙어야 Client에서 끊음)
							pw.println(data);

						}
					} catch (SocketException e) {
						EchoServer2.log("suddenly closed by client");
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

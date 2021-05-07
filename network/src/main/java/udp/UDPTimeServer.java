package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {
	public static final int PORT = 7777;
	public static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		DatagramSocket socket = null;
		try {
			// 데이터 수신
			socket = new DatagramSocket(PORT);
			while (true) {

				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);// blocking

				byte[] receiveData = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(receiveData, 0, length, "utf-8");

				System.out.println("[server] receive: " + message);
				byte[] sendData;
				if ("".equals(message)) {
					// 3.시간 데이터 송신
					String data = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
					sendData = data.getBytes();
				} else {
					// 3.아니면 그냥 데이터 송신
					sendData = message.getBytes("utf-8");
				}
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(),
						receivePacket.getPort());
				socket.send(sendPacket);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}

}

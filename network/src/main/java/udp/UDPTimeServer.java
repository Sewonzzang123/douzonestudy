package udp;

import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {

	public static void main(String[] args) {
		
		//데이터 수신
		DatagramPacket receivePakcet = new DatagramPacket(null, 0);
		//socket.receive(receivePakcet); //blocking
		//데이터 송신
		new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		

	}

}

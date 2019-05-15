package pct;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {

	public static void main(String[] args) throws Exception{
		
		int port = 5000;
		
		String group = "230.0.0.0";
		
		MulticastSocket s = new MulticastSocket(port);
		
		s.joinGroup(InetAddress.getByName(group));
		
		byte buf[] = new byte[1024];
		DatagramPacket pack = new DatagramPacket(buf, buf.length);
		
		s.receive(pack);
		
		System.out.println("Received data from: "+pack.getAddress().toString() + ":"+pack.getPort()+" with length: "+pack.getLength());
		System.out.println();
					
		s.leaveGroup(InetAddress.getByName(group));
		s.close();
	}
}

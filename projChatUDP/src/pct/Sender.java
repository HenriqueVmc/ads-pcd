package pct;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sender {

	public static void main(String[] args) throws Exception{
		int port = 5000;
		String group = "230.0.0.0";
		
		MulticastSocket s = new MulticastSocket(port);
		
		byte buf[] = new byte[10];
		
		for (int i = 0; i < buf.length; i++)
			buf[i] = (byte)i;
		
		DatagramPacket pack = new DatagramPacket(buf, buf.length, InetAddress.getByName(group), port);
				
		s.send(pack);
		s.close();	
	}	
}

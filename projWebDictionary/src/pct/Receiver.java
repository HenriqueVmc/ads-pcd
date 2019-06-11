package pct;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Receiver implements Runnable {

	private MulticastSocket socket;
	private String group;

	private IBuffer cacheTraducoes;

	public Receiver(MulticastSocket socket, IBuffer cacheTraducoes/*, String group */) {
		this.socket = socket;
		this.cacheTraducoes = cacheTraducoes;
		this.group = group;
	}

	@Override
	public void run() {

		ExecutorService application = Executors.newFixedThreadPool(20);

		while (true) {
			try {

				socket.joinGroup(InetAddress.getByName(group));

				byte buf[] = new byte[3072];
				DatagramPacket pack = new DatagramPacket(buf, buf.length);

				socket.receive(pack);

				String IP = pack.getAddress().toString();
				int porta = pack.getPort();

				String msg = new String(pack.getData(), pack.getOffset(), pack.getLength());

				// Log:
				System.out.println("\nGrupo: " + group + " | End.: " + IP + ":" + porta + " | Msg.: " + msg);

				application.execute(new TradutorService(msg, cacheTraducoes));

				socket.leaveGroup(InetAddress.getByName(group));

			} catch (Exception w) {
				socket.close();
			}
		}
	}
}
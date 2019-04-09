package pct;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ViewHttpRequest {

	public static void main(String[] args) {

		ServerSocket server;
		DataInputStream is;
		DataOutputStream os;

		boolean begin = true;
		
		try {			
			server = new ServerSocket(8080);

			int cont = 0;
			
			while (begin) {

				System.out.println("\nAguardado conexão");
				Socket client = server.accept();
				is = new DataInputStream(client.getInputStream());
				os = new DataOutputStream(client.getOutputStream());																
						
				while (is.available() > 0){
					System.out.println(is.readLine());
				}
				// --- OR ---
				//int msg;
				//
				//while ((msg = is.read()) != -1)
				//{
				//	System.out.print((char) msg);
				//}
				
				String response = "HTTP/1.1 404 Not Found\n"+
						"Date: Sun, 18 Oct 2012 10:36:20 GMT\n"+
						"Server: Apache/2.2.14 (Win32)\n"+
						"Content-Length: 230\n"+
						"Connection: Closed\n"+
						"Content-Type: text/html; charset=iso-8859-1"+
						"\n\n"+
						"<!DOCTYPE HTML>\n"+
						"<html>\n"+
						"<head>\n"+
						   "<title>404 Not Found</title>\n"+
						"</head>\n"+
						"<body>\n"+
						   "<h1>Not Found("+(cont++)+")</h1>\n"+
						   "<p>The requested URL /t.html was not found on this server.</p>\n"+
						"</body>\n"+
						"</html>\n";
				
				os.writeBytes(response);
				
				is.close();
				os.close();

				client.close();	
			}	
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

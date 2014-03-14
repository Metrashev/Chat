package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	final static int PORT = 8001;
	
	public static void main(String [] args) throws IOException{
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server has been started...");
		try{
			while(true){
				Socket socket = s.accept();
				System.out.println("Incomming request:");
				try{
					new ServeClient(socket);
					
				}catch (IOException ioe){
					if(socket != null){
						socket.close();
					}
				}
			}
		}finally {
			if (s != null) {
				s.close();
			}
		}
		
	}
}

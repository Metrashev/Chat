package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServeClient extends Thread{
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket; 
	
	ServeClient(Socket s) throws IOException{
		socket = s; 
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		start();
	}
	public void run(){
		try{
			while (true){
				String inStr = in.readLine();
				
				if(inStr.equals("---END---")){
					break;
				}
				System.out.println("Client: "+ inStr);
				out.println("Server: "+ inStr);
			}
			System.out.println("Clossing connection");
		}catch (IOException ioe){
			System.out.println("Connection error");
		}finally {
			try{
				socket.close();
			}catch(IOException ioe) {
				System.out.println("Can't close sockets");
			}
		}
	}
}

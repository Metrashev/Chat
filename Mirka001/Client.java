package Mirka001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client extends Thread{
	static final int PORT = 8001;
	Socket socket = null;
	private String inText; //***********
	
	Client(String outText){
		try {
			InetAddress ip = InetAddress.getByName("localhost");
			System.out.println("Client IP: " + ip);
			socket = new Socket(ip, PORT);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			out.println(outText);
			inText = in.readLine(); //***********
//			getInText();
			out.println("---END---");
		} catch (IOException e) {
			if (socket == null){
				System.out.println("Server not responding");
			}else{
				System.out.println("Communication error");
			}
		} finally {
			if (socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Can't close secket");
			}
			}
		}
	}
//	void getInText() throws IOException{ //***********
//		//System.out.println(inText);
//		Center2Sheet C2S = new Center2Sheet();
//		C2S.store(inText);
//	}
	

	
}

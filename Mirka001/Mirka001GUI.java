package Mirka001;

import java.io.IOException;

import javax.swing.JFrame;

public class Mirka001GUI {
	
	public static void main(String [] args) throws IOException{
		

		JFrame frame = new JFrame("Mirka \"v001\"");
		frame.setSize(600, 500);
		frame.setLocation(500, 100);
		frame.setDefaultCloseOperation(3);
		BorderLayout1Sheet panel = new BorderLayout1Sheet();
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
}

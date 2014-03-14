package Mirka001;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Center2Sheet extends JPanel{
	
	private JTextField jtf;
	private JButton jbSend;
	private JTextArea jta;
	private File f1;
	private PrintWriter out; 
	
	

	Center2Sheet() throws IOException{  //IOException -> f1.createNewFile();
		f1 = new File("correspondence.txt");
		if(!f1.isFile()){
			f1.createNewFile();
			}
		
		setLayout(new BorderLayout(10, 10));
		
		NorthPanel north = new NorthPanel();
		CenterPanel center = new CenterPanel();
		SouthPanel south = new SouthPanel();
		
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
	}
	
	private class NorthPanel extends JPanel{
		
		
	//	ImageIcon connectIcon = new ImageIcon("connect.png");
	//	ImageIcon disconnectIcon = new ImageIcon("disconnect.png");
		
//		NorthPanel(){
//			setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
//			JLabel northLabel = new JLabel(" North Label ", disconnectIcon, JLabel.CENTER);
//			
//			add(northLabel);
//			add(new JButton(" 1 "));
//			add(new JButton(" 2 "));
//			add(new JButton(" .. "));
//		}
	}
	
	private class CenterPanel extends JPanel{
		
		CenterPanel() throws IOException{
			setLayout(new BorderLayout(0,5));
	        
			jta = new JTextArea("");
			jta.setEditable(false);
			JScrollPane jsp = new JScrollPane(jta);
			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			add(jsp, BorderLayout.CENTER);
			jTextAreaFunc();

		}
	}
	private class SouthPanel extends JPanel{
		
		SouthPanel(){
			
			setLayout(new BorderLayout());
			jtf = new JTextField();
			jbSend = new JButton("send");
			
			add(jtf, BorderLayout.CENTER);
			add(jbSend, BorderLayout.EAST);
			jbSend.addActionListener(new ButtonListener());
			jtf.addKeyListener(new ButtonListener());
		}
			private class ButtonListener implements ActionListener, KeyListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						sendButtonFunc();
						jTextAreaFunc();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					 if (e.getKeyCode() == KeyEvent.VK_ENTER){
						 try {
								sendButtonFunc();
								jTextAreaFunc();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					}
					 if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
						 System.exit(0);
					 }					
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			}
			private void sendButtonFunc() throws IOException{
				String timeStamp = new SimpleDateFormat(" yyyy/MM/dd[HH:mm:ss] ").format(Calendar.getInstance().getTime());
				out = new PrintWriter(new BufferedWriter(new FileWriter(f1, true))); // този запис позволява добавянето на текст а не презапис.
				out.print(timeStamp);
				out.println(jtf.getText());
				out.close();
				new Client(timeStamp+" "+" "+jtf.getText());
				jtf.setText("");
			}
			
			}
		private void jTextAreaFunc() throws IOException{
			BufferedReader buffR = new BufferedReader(new FileReader(f1));
			StringBuilder sb = new StringBuilder();
			String line = buffR.readLine();
			while(line != null){
				sb.append(line);
				sb.append('\n');
				line = buffR.readLine();
			}
			jta.setText(sb.toString());
			buffR.close();

	}
		void store(String inText) throws IOException{
			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter(f1, true)));
			} catch (IOException e) {
			} 
			out.println(inText);
			out.close();
			jTextAreaFunc();
		}
}

package Mirka001;


import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JPanel;

public class BorderLayout1Sheet extends JPanel{

	
	BorderLayout1Sheet() throws IOException{ //IOEcxeption --> f1.createNewFile();
		setLayout(new BorderLayout(0,0));
		
		East2Sheet east = new East2Sheet();
		
		Center2Sheet center = new Center2Sheet();
		add(east, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
	}
}

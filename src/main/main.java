package main;

import java.awt.*;
import javax.swing.*; 

public class main {
	
	
	static private void make_full_screen(JFrame fr) {
		Dimension full_screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		fr.setSize(full_screen_size.width, full_screen_size.height);	
	}

	
	static private void initiate() {
		JFrame fr = new JFrame("Magnum Opus");
		//Closes on user exit
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Maybe use this instead of make_full_screen, for now leave it as is
		fr.pack();
		//Order of this has to be retained
		draw_character(fr);
		draw_start_screen(fr);
		fr.pack();
		fr.setVisible(true);
		fr.setLocation(0,0);
		make_full_screen(fr);
	}
	
	static private void draw_start_screen(JFrame fr) {
		//Dimension full_screen_size = Toolkit.getDefaultToolkit().getScreenSize(); , full_screen_size.width, full_screen_size.height
		image_panel panel = new image_panel("assets/images/forest_1.png",0,0);
		fr.getContentPane().add(panel);
	}
	
	static private void draw_character(JFrame fr) {
		image_panel panel = new image_panel("assets/images/mage_standing.png",0,10);
		fr.getContentPane().add(panel);
	}
	

	public static void main(String[] args) {
		initiate();
	}

}

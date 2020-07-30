package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class image_panel extends JPanel{
	Image image;
	
	public image_panel(String image_path) {
		this(new ImageIcon(image_path).getImage());
	}
	
	public image_panel(Image image) {
		this.image = image;
		Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics graphic) {
		graphic.drawImage(image, 0, 0, null);
	}
	

}

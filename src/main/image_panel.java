package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class image_panel extends JPanel{
	Image image;
	int width;
	int height;
	int x;
	int y;
	
	public image_panel(String image_path, int x, int y,int width, int height) {
		this(new ImageIcon(image_path).getImage());
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public image_panel(String image_path, int x, int y) {
		this(new ImageIcon(image_path).getImage());
		this.x = x;
		this.y = y;
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
		//The null parameter is called an observer that sounds important to call paintCOmponent again
		System.out.println(height + "," + width);
		if(height == 0 && width == 0) {
			System.out.println("height = 0 width = 0");
			graphic.drawImage(image, x, y, null, null);
		} else {
			graphic.drawImage(image, x, y, width, height, null);
		}
	}
	

}

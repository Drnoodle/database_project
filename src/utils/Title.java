package utils;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;




public class Title extends JPanel {

	
	private static final long serialVersionUID = 1L;
	

	String title;
	
	public Title(String str){
	this.setOpaque(false);
	this.setBorder(new EmptyBorder(0,0,30,0));
	this.title = str;

	}
	
	@Override
	public void paintComponent(Graphics g){

		Graphics2D g2d =  (Graphics2D)g;

		FontMetrics metrics =
				g2d.getFontMetrics(LocalFont.getFont(LocalFont.MONTSERRAT, 14));

		int height = metrics.getHeight();
		int width = metrics.stringWidth(title);
		this.setSize(width+40,height+40);

		g2d.setColor(new Color(0,0,0,5));
		Rectangle shade = new Rectangle(0,15,width+21,height-4);
		g2d.fill(shade);
		g2d.setColor(new Color(0,0,0,5));
		Rectangle shade2 = new Rectangle(0,15,width+22,height-3);
		g2d.fill(shade2);
		g2d.setColor(new Color(250,240,51));
		Rectangle rect = new Rectangle(0,15,width+20,height-5);
		g2d.fill(rect);
		
		
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setFont(LocalFont.getFont(LocalFont.MONTSERRAT, 14));
		g2d.setColor(Color.DARK_GRAY);
		g2d.drawString(title, 10, 25);
		
	}
	
	
}

package utils;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Simple Panel with a background image.
 */
public class BackgroundPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;
	
	
	private Image background;
	
	
	public BackgroundPanel(String imgSrc){

		try {
			this.background = ImageIO.read(new File(imgSrc));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(this.background.getWidth(this) <= 0 
				|| this.background.getHeight(this) <= 0){
			throw new IllegalArgumentException("image with null size");
		}		
	}

	
	@Override
	public void paintComponent(Graphics g){

		int imgWidth = background.getWidth(this);
		int imgHeight = background.getHeight(this);
		
		for(int x = 0; x < this.getWidth(); x += imgWidth){
			for(int y = 0; y < this.getHeight(); y += imgHeight){
				g.drawImage(background, x, y, imgWidth, imgHeight, this);
			}
		}

	}
	
	
	
	
	
}
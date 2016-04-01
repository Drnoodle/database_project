package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public enum LocalFont {
	
	BREE("BreeSerif-Regular.ttf"),
	HAMMERSMITHONE("HammersmithOne-Regular.ttf"),
	HIND("Hind-Medium.ttf"),
	MONTSERRAT("MontserratAlternates-Regular.ttf"),
	ROSARIO("Rosario-Regular.ttf"),
	CANDAL("Candal.ttf"),
	FRANCOISONE("FrancoisOne.ttf"),
	LOBSTER("Lobster-Regular.ttf"),
	PATUAONE("PatuaOne-Regular.ttf"),
	RIGHTEOUS("Righteous-Regular.ttf"),
	LATO("Lato-Regular.ttf"),
	ROBOTO_SLAB("RobotoSlab-Regular.ttf"),
	ROBOTO_SLAB_BOLD("RobotoSlab-Bold.ttf"),;
	
	private String name;
	
	private LocalFont(String value){
		this.name = value;
	}
	
	/**
	 * return the path of the font
	 */
	public String path(){
		return "font/"+name;
	}
	
	
	/**
	 * return the selected font
	 */
	public static Font getFont(LocalFont value, float size){

	    Font font = null;
	    
		try {
		
			InputStream is = Button.class.getResourceAsStream(value.path());
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
			      
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return font;
	}
	
}

package utils;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class Button extends JButton implements MouseListener{
	
	
	private static final long serialVersionUID = -127037380654037854L;

	
	private Callback callback = new Callback();
	
	
	public Button(String value){
		super(value);
		this.setBorderPainted(false); 
		this.setContentAreaFilled(false); 
		this.setFocusPainted(false); 
		this.setOpaque(false);
		this.addMouseListener(this);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	
	/**
	 * callback on clicked event
	 */
	public Callback clickedCallback() {
		return callback;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

		if(this.isEnabled()){
            System.out.println("clicked");
 			clickedCallback().runAll();
		}
		
	}

	
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	
	
	
}

package view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.BackgroundPanel;

public class ContentPane extends BackgroundPanel{
	
	

	private static final long serialVersionUID = 1L;

	private JComponent body;
	
	
	public ContentPane(){                		
		super("images/background.png");
		this.setLayout(new BorderLayout());
		
		this.body = new JPanel();
		this.add(body, BorderLayout.CENTER);
	 }   
	
	
	
	/**
	 * set the given component as body 
	 */
	public void setBody(JComponent newBody){
		
		this.remove(body);
		body = newBody; 
		this.add(body, BorderLayout.CENTER);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				revalidate();
				repaint();
			}
		});
	}

	
}

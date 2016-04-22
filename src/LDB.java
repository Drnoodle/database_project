import javax.swing.JFrame;

import controler.ContentPaneControler;

public class LDB {

	
	public static void main(String[] args){

		ContentPaneControler.getInstance().active();
		
		JFrame frame = new JFrame();
		frame.setContentPane(ContentPaneControler.getInstance().getComponent());
		frame.setSize(800,600);
		frame.setTitle("library database");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
}

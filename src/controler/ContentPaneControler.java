package controler;

import javax.swing.JComponent;

import view.ContentPane;

public class ContentPaneControler implements Controler{

	
	
	private static ContentPaneControler instance = new ContentPaneControler();
	
	private ContentPane contentPane = new ContentPane();
	
	private Controler displayedControler = null; 
	
	
	
	public ContentPaneControler(){
		
		if(instance != null){
			throw new IllegalAccessError();
		}
		
		
		// default : controler is active
		displayedControler = FormControler.getInstance(); 
		displayedControler.active(); 
		contentPane.setBody(displayedControler.getComponent());
		
	}
	
	
	
	public static ContentPaneControler getInstance(){
		return instance;
	}


	
	@Override
	public JComponent getComponent() {
		return contentPane;
	}



	@Override
	public void active() {
	}



	@Override
	public void inactive() {
	}

	
	public void display(Controler controler){
		
		displayedControler.inactive();
		displayedControler = controler; 
		displayedControler.active(); 
		contentPane.setBody(displayedControler.getComponent());
		
	}
	
	
}

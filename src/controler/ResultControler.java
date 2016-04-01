package controler;

import javax.swing.JComponent;

import view.BodyResult;


public class ResultControler implements Controler{

	
	
	private static ResultControler instance = new ResultControler();
	
	private BodyResult bodyResult = new BodyResult();

	
	public ResultControler(){
		
		if(instance != null){
			throw new IllegalAccessError();
		}
		
		// go to form if user close the table result
		bodyResult
		.clickCloseCallback()
		.addCallback(new Runnable(){
			@Override
			public void run() {
				ContentPaneControler
				.getInstance()
				.display(FormControler.getInstance());
			}});
	
	}
	
	
	
	public static ResultControler getInstance(){
		return instance;
	}


	
	@Override
	public JComponent getComponent() {
		return bodyResult;
	}



	@Override
	public void active() {
	}



	@Override
	public void inactive() {
	}

	
	public void setResult(String[][] table){
		bodyResult.setData(table);
	}

	
}

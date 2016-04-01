package controler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComponent;

import data_access.SqlExecutor;
import data_access.SqlFactory;
import view.BodyForm;
import view.BodyForm.AvailableRequest;

public class FormControler implements Controler{

	
	
	private static FormControler instance = new FormControler();

	private BodyForm formBody = new BodyForm();

	
	
	public FormControler(){
		
		if(instance != null){
			throw new IllegalAccessError();
		}
		
		
		formBody
		.clickAvailableRequestCallback()
		.addCallback(new Runnable(){
			@Override
			public void run() {
				
				// request asked by the user
				AvailableRequest request = formBody.lastRequestclicked();
				
				// ask sql factory to return an sql statement
				PreparedStatement statement = 
						SqlFactory.getStatement(request);
			
				try {
					
					String[][] result = 
							SqlExecutor
							.getInstance()
							.getData(statement);
					
					// result controler load data into his view
					ResultControler
					.getInstance()
					.setResult(result);
					
					// contentPane display the result view instead of this form
					ContentPaneControler
					.getInstance()
					.display(ResultControler.getInstance());
					
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
				
			}});
		
		
		
	}

	
	public static FormControler getInstance(){
		return instance;
	}



	@Override
	public JComponent getComponent() {	
		
		return formBody;
	}



	@Override
	public void active() {
	}



	@Override
	public void inactive() {
	}

	
	
	
	
}

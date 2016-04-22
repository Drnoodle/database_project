package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComponent;

import data_access.DBReader;
import import_csv.AbstractImport;
import utils.CsvFile;
import view.BodyForm;
import view.BodyForm.AvailableRequest;

public class FormControler implements Controler{

	
	
	private static FormControler instance = new FormControler();

	private BodyForm formBody = new BodyForm();

	private Connection conn; 
	
	
	
	public FormControler(){
		
		if(instance != null){
			throw new IllegalAccessError();
		}
		
		try {
			this.conn = DBReader.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// when user asks for a request on view, this runnable is called
		formBody
		.clickAvailableRequestCallback()
		.addCallback(new Runnable(){
			@Override
			public void run() {
				
				// request asked by the user
				AvailableRequest request = formBody.lastRequestclicked();
				
				// ask sql factory to return an sql statement
			
				try {
					PreparedStatement statement = DBReader.getStatement(request,conn);
					String[][] result = DBReader.getData(statement);
					
					// put the result of the request in the ResultControler
					ResultControler
					.getInstance()
					.setResult(result);
					
					// ask contentPaneControler to move to ResultControler's view
					ContentPaneControler
					.getInstance()
					.display(ResultControler.getInstance());
					
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
				
			}});
		
		
		// When user ask to import data this runnable is called
		formBody
		.clickImportcallback()
		.addCallback(new Runnable(){
			@Override
			public void run() {
				FormControler.this.importCsv();
			}
		});
		
		
		// when user has made is own querry on the view this runnable is called
		formBody
		.clickSqlText()
		.addCallback(new Runnable(){
			@Override
			public void run() {
				String req = formBody.sqlText(); 
				try {
					
					PreparedStatement stat = conn.prepareStatement(req);
					
					String[][] result = DBReader.getData(stat); 
					
					// put the result of the request in the ResultControler
					ResultControler
					.getInstance()
					.setResult(result);
					
					// ask contentPaneControler to move to ResultControler's view
					ContentPaneControler
					.getInstance()
					.display(ResultControler.getInstance());
					
					
				} catch (SQLException e) {
				} 
				formBody.flushSqlText();
				
				
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

	
	
	private void importCsv(){
		
		for(CsvFile file : CsvFile.values()){
			try {
				System.out.println("imports " + file );
				AbstractImport importCsv = AbstractImport.abstractImport(file, conn);
				
				importCsv.insert();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
}

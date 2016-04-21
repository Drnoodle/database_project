package controler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComponent;

import data_access.DBConnection;
import data_access.SqlFactory;
import import_csv.AbstractImport;
import utils.CsvFile;
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
					
					String[][] result = DBConnection.getData(statement);
					
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
		
		
		formBody
		.clickImportcallback()
		.addCallback(new Runnable(){
			@Override
			public void run() {
				FormControler.this.importCsv();
			}
		});
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
		
		DBConnection conn = new DBConnection();
		for(CsvFile file : CsvFile.values()){
			try {
				System.out.println("imports " + file );
				AbstractImport importCsv = AbstractImport.abstractImport(file, conn);
				importCsv.insertAll();
				importCsv.insertCommit();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
}

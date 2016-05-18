package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.directory.SearchResult;
import javax.swing.JComponent;

import data_access.DBConnection;
import data_access.DBMilstone2;
import data_access.DBSearch;
import import_csv.AbstractImport;
import library.*;
import utils.CsvFile;
import view.BodyForm;
import view.BodyForm.AvailableRequest;
import view.ContentPane;

public class FormControler implements Controler{

	
	
	private static FormControler instance = new FormControler();

	private BodyForm formBody = new BodyForm();

	private DBMilstone2 milestone2;

    private DBSearch search;

    private Connection conn;
	
	
	public FormControler(){
		
		if(instance != null){
			throw new IllegalAccessError();
		}
		
		try {
            this.conn = DBConnection.getConnection();
			this.milestone2 = new DBMilstone2(this.conn);
            this.search = new DBSearch(this.conn);
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
					PreparedStatement statement = milestone2.getStatement(request);
					String[][] result = DBConnection.getData(statement);
					
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
		
		
		// when user wants to perform a search querry
		formBody
		.searchBoxCallback()
		.addCallback(new Runnable(){
			@Override
			public void run() {

				String searchReq = formBody.searchText();
                BodyForm.SearchBoxSelection searchOn = formBody.searchSelection();

				try {

                    switch(searchOn){

                        case AUTHOR:
                            List<Author> authors = search.getAuthors(searchReq);
                            SearchResultControler
                                    .getInstance()
                                    .display(authors);
                            break;

                        case AWARD:
                            List<Award> awards = search.getAwards(searchReq);
                            SearchResultControler
                                    .getInstance()
                                    .display(awards);
                            break;


                        case PUBLISHER:
                            List<Publisher> publishers = search.getPublishers(searchReq);
                            SearchResultControler
                                    .getInstance()
                                    .display(publishers);
                            break;

                        case TITLE:
                            List<Title> titles = search.getTitles(searchReq);
                            SearchResultControler
                                    .getInstance()
                                    .display(titles);
                            break;

                    }

				} catch (SQLException e) {
                    e.printStackTrace();
				}

                ContentPaneControler
                        .getInstance()
                        .display(SearchResultControler.getInstance());

				formBody.flushSearchText();
				
				
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

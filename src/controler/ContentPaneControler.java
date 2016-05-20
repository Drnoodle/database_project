package controler;

import javax.swing.JComponent;

import data_access.DBConnection;
import data_access.DBMilstone2;
import data_access.DBSearch;
import library.Author;
import library.Award;
import library.Publisher;
import library.Title;
import view.ContentPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import view.ContentPane.AvailableRequest;

public class ContentPaneControler implements Controler{

	
	
	private static ContentPaneControler instance = new ContentPaneControler();
	
	private ContentPane contentPane = new ContentPane();

	private Controler displayedBody = null;



    private Connection conn;

    private DBSearch search;

    private DBMilstone2 milestone2;


    private TaskExecutor executor;
	
	private ContentPaneControler(){
		
		if(instance != null){
			throw new IllegalAccessError();
		}

        executor = new TaskExecutor();
		
		// default : controler is active
		displayedBody = AccueilControler.getInstance();
		displayedBody.active();
		contentPane.setBody(displayedBody.getComponent());


        try {
            this.conn = DBConnection.getConnection();
            this.milestone2 = new DBMilstone2(this.conn);
            this.search = new DBSearch(this.conn);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        // when user asks for a request on view, this runnable is called
        contentPane
                .newSearchQuerryCallback()
                .addCallback(new Runnable(){
                    @Override
                    public void run() {

                        displaySearchRequest();

                    }});


        // when user asks for a request on view, this runnable is called
        contentPane
                .newCustomQuerryCallback()
                .addCallback(new Runnable(){
                    @Override
                    public void run() {
                        if(contentPane.getCustomRequest() != AvailableRequest.NO_REQUEST_SELECTED) {
                            displayCustomRequest();
                        }
                    }});


        contentPane
                .insertDataCallback()
                .addCallback(new Runnable(){
                    @Override
                    public void run() {
                        display(InsertControler.getInstance());
                    }
                });


        contentPane
                .ldbButtonCallback()
                .addCallback(new Runnable(){
            @Override
            public void run() {
                display(AccueilControler.getInstance());
            }
        });
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
		
		displayedBody.inactive();
		displayedBody = controler;
		displayedBody.active();
		contentPane.setBody(displayedBody.getComponent());
		
	}



    private void displayCustomRequest(){


        // this code will not be executed by the ui thread
        executor.next(
                new Runnable(){
                    @Override
                    public void run() {

                        // request asked by the user
                        AvailableRequest request = contentPane.getCustomRequest();

                        if(request == null){
                            return;
                        }
                        // ask sql factory to return an sql statement

                        try {
                            PreparedStatement statement = milestone2.getStatement(request);
                            String[][] result = DBConnection.getData(statement);

                            // put the result of the request in the ResultControler
                            ResultControler
                                    .getInstance()
                                    .displayArray(result);

                            // ask contentPaneControler to move to ResultControler's view
                            ContentPaneControler
                                    .getInstance()
                                    .display(ResultControler.getInstance());


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    private void displaySearchRequest(){

        // this code will not be executed byx ui thread
        executor.next(new Runnable(){
            @Override
            public void run() {

                String searchReq = contentPane.searchText();
                ContentPane.SearchBoxSelection searchOn = contentPane.searchSelection();

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

                contentPane.flushSearchText();
            }
        });
    }
	
}

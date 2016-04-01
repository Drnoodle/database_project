package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import utils.Button;
import utils.Callback;
import utils.LocalFont;
import utils.Title;

public class BodyForm extends JPanel{

	
	private static final long serialVersionUID = 1L;

	

	public enum AvailableRequest{
		
		YEAR_TO_TOTAL_PUB("total publications by year"), 
		TEN_MOST_PUBLISHED_AUTHORS("ten most published authors"),
		YOUNGEST_AUTHOR("youngest author"),
		OLDEST_AUTHOR("oldest author"),
		COMIC_DETAIL("comics by total pages"),
		PUBLISHER_AVERAGE_NOVEL_PRICE("average price of novels by publisher"),
		AUTHOR_SCIENCE_FICTION(
				"author with the higgest number of "
				+ "titles tagged as science fiction"),
		THREE_MOST_POPULAR_TITLE("three most popular titles"); 
		
		private String desc; 
		
		AvailableRequest(String desc){
			this.desc = desc; 
		}
		
		public String description(){
			return desc;
		}
		
	};
	
	
	
	
	private Button importButton; 
	
	private JTextPane sqlUserText;
	
	private Button sqlUserButton;
	
	
	private Callback availableRequestClicked = new Callback();
	
	private AvailableRequest lastRequestClicked = null;
	
	
	public BodyForm(){
		
		this.setOpaque(false);
		BoxLayout bl = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		this.setLayout(bl);
		
		
		// button to import new data from csv
		JPanel importContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		importContainer.setOpaque(false);
		importContainer.setBorder(new EmptyBorder(0, 30, 0, 0));
		importButton = new Button("import from csv");
		importButton.setOpaque(true);
		importButton.setBackground(new Color(150,150,150));
		importButton.setForeground(new Color(225,225,225));
		importContainer.add(importButton);

		
		// personal sql text field for the user 
		JPanel sqlUserTextContainer = new JPanel(new BorderLayout());
		sqlUserTextContainer.setOpaque(false);
		sqlUserTextContainer.setBorder(new EmptyBorder(20, 30, 30, 30));
		sqlUserText = new JTextPane();
		sqlUserText.setBackground(new Color(245,245,245));
		sqlUserText.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,15),4));
		sqlUserText.setFont(LocalFont.getFont(LocalFont.BREE, 14));		
		sqlUserButton = new Button("go");
		sqlUserTextContainer.add(sqlUserText);
		sqlUserTextContainer.add(sqlUserButton, BorderLayout.SOUTH);
		
		
		
		// buttons for available request
		
		JPanel requestsPane = new JPanel();
		requestsPane.setOpaque(false);

		bl = new BoxLayout(requestsPane, BoxLayout.PAGE_AXIS);
		requestsPane.setLayout(bl);
		
		for(final AvailableRequest req : AvailableRequest.values()){
			
			Button b = new Button(req.description()); 
			b.setFont(LocalFont.getFont(LocalFont.BREE, 14));
			// on click : button b put his available request
			// into "lastRequestClicked" and run the availableRequestClicked
			// callback
			b.clickedCallback()
			.addCallback(new Runnable(){
				@Override
				public void run() {
					BodyForm.this.lastRequestClicked = req;
					BodyForm.this.availableRequestClicked.runAll();
				}
			});
			
			requestsPane.add(b);
			
		}
		JPanel requestsContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		requestsContainer.setOpaque(false);
		requestsContainer.add(requestsPane);

		
		this.add(importContainer);
		
		this.add(new Title("existing querries"));
		this.add(requestsContainer);
		
		this.add(new Title("execute your own querry"));
		this.add(sqlUserTextContainer);
		
	}
	
	
	
	
	
	public Callback clickAvailableRequestCallback(){
		return availableRequestClicked;
	}
	
	
	public Callback clickImportcallback(){
		return importButton.clickedCallback();
	}
	
	
	public Callback clickSqlText(){
		return sqlUserButton.clickedCallback();
	}
	
	
	public String sqlText(){
		return sqlUserText.getText();
	}
	
	public AvailableRequest lastRequestclicked(){
		return lastRequestClicked;
	}
	
	
}




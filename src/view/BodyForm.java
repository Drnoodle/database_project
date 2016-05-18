package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import utils.Button;
import utils.Callback;
import utils.LocalFont;
import utils.Title;

public class BodyForm extends JPanel{

	
	private static final long serialVersionUID = 1L;

    public enum SearchBoxSelection {
        AUTHOR,AWARD,PUBLISHER,TITLE
    }
	

	public enum AvailableRequest{
		
		YEAR_TO_TOTAL_PUB("total publications by year"), 
		TEN_MOST_PUBLISHED_AUTHORS("ten most published authors"),
		YOUNGEST_AUTHOR("youngest Author"),
		OLDEST_AUTHOR("oldest Author"),
		COMIC_DETAIL("comics by total pages"),
		PUBLISHER_AVERAGE_NOVEL_PRICE("average price of novels by publisher"),
		AUTHOR_SCIENCE_FICTION(
				"Author with the higgest number of "
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
	
	private JTextField searchPane;
	private JComboBox searchSelection;
	private Button searchButton;
	
	
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
		JPanel searchBoxContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchBoxContainer.setOpaque(false);

        JPanel sqlUserSearchBoxForm = new JPanel();
        sqlUserSearchBoxForm.setOpaque(false);
        BoxLayout box = new BoxLayout(sqlUserSearchBoxForm,BoxLayout.LINE_AXIS);
        searchSelection = new JComboBox();
        searchSelection.addItem("Author");
        searchSelection.addItem("Award");
        searchSelection.addItem("Publisher");
        searchSelection.addItem("Title");
        searchPane = new JTextField();
        searchPane.setSize(new Dimension(270,22));
        searchPane.setPreferredSize(new Dimension(400,20));
		searchButton = new Button("go");

        sqlUserSearchBoxForm.add(searchPane);
        sqlUserSearchBoxForm.add(searchSelection);
        sqlUserSearchBoxForm.add(searchButton);
        searchBoxContainer.add(sqlUserSearchBoxForm);


        // buttons for available request
		
		JPanel requestsPane = new JPanel();
		requestsPane.setOpaque(false);

		bl = new BoxLayout(requestsPane, BoxLayout.PAGE_AXIS);
		requestsPane.setLayout(bl);
		
		for(final AvailableRequest req : AvailableRequest.values()){
			
			Button b = new Button(req.description()); 
			b.setFont(LocalFont.getFont(LocalFont.HAMMERSMITHONE, 14));
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
		
		this.add(new Title("requests #milestone2"));
		this.add(requestsContainer);
		
		this.add(new Title("search box #milestone2"));
		this.add(searchBoxContainer);

        this.add(new Title("requests #milestone3"));
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        this.add(panel);

    }
	
	
	
	
	
	public Callback clickAvailableRequestCallback(){
		return availableRequestClicked;
	}
	
	
	public Callback clickImportcallback(){
		return importButton.clickedCallback();
	}
	
	
	public Callback searchBoxCallback(){
		return searchButton.clickedCallback();
	}
	
	
	public String searchText(){
		return searchPane.getText();
	}
	
	public SearchBoxSelection searchSelection(){
        return SearchBoxSelection.values()[searchSelection.getSelectedIndex()];
    }

	public void flushSearchText(){
		searchPane.setText("");
	}


	public AvailableRequest lastRequestclicked(){
		return lastRequestClicked;
	}
	
	
}




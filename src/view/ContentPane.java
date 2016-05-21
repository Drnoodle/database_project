package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import utils.*;
import utils.Button;

public class ContentPane extends JPanel{
	
	

	private static final long serialVersionUID = 1L;


	private JComponent body;

    private JPanel header;


    public enum SearchBoxSelection {
        AUTHOR,AWARD,PUBLISHER,TITLE
    }


    public enum AvailableRequest{
        NO_REQUEST_SELECTED("custom request"),
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

    }



    private JTextField searchPane;
    private JComboBox searchSelection;
    private CustomComboBox customQuerries;
    private Button searchButton;
    private Button insertButton;
    private Button ldbButton;


    public ContentPane(){

        this.setLayout(new BorderLayout());
		
		this.body = new JPanel(new BorderLayout());

        this.header = new JPanel();
        this.header.setBackground(new Color(50,50,50));

        /////         HEADER   FORM


        searchPane = new JTextField();
        searchPane.setBackground(new Color(65,65,65));
        searchPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        searchPane.setSize(new Dimension(270,22));
        searchPane.setPreferredSize(new Dimension(240,20));
        searchPane.setForeground(new Color(220,170,50));
        searchPane.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
        searchPane.setBorder(new EmptyBorder(0,10,0,0));


        searchButton = new Button(
                "Search",
                Color.WHITE,
                LocalFont.getFont(LocalFont.LOBSTER,13)
                );

        searchButton.setPreferredSize(new Dimension(80,30));
        searchButton.setBorder(new EmptyBorder(3,0,0,0));

        searchSelection = new CustomComboBox();
        searchSelection.setPreferredSize(new Dimension(110,30));
        searchSelection.setBorder(new EmptyBorder(0,0,5,0));
        searchSelection.addItem("Author");
        searchSelection.addItem("Award");
        searchSelection.addItem("Publisher");
        searchSelection.addItem("Title");

        JPanel customQuerriesContainer = new JPanel();
        customQuerriesContainer.setOpaque(false);

        customQuerries= new CustomComboBox();
        customQuerries.setPreferredSize(new Dimension(200,20));
        customQuerries.setBackground(Color.WHITE);
        customQuerries.setBorder(new EmptyBorder(0,0,0,0));
        customQuerries.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,14));

        for(AvailableRequest request : AvailableRequest.values()){
            customQuerries.addItem(request.description());
        }
        customQuerriesContainer.add(customQuerries);




        JPanel headerContainer = new JPanel();
        headerContainer.setOpaque(false);
        BoxLayout box = new BoxLayout(headerContainer, BoxLayout.LINE_AXIS);
        headerContainer.setLayout(box);
        headerContainer.add(searchPane);
        headerContainer.add(searchSelection);
        headerContainer.add(searchButton);

        header.setLayout(new BorderLayout());
        header.add(headerContainer, BorderLayout.EAST);
        ldbButton = new Button(
                "LibDBMS",
                new Color(100,100,100),
                LocalFont.getFont(LocalFont.FRANCOISONE,14)
                );

        header.add(ldbButton, BorderLayout.WEST);


        insertButton = new Button(
                "insert",
                Color.WHITE,
                LocalFont.getFont(LocalFont.FRANCOISONE,12)
                );

        insertButton.setOpaque(true);
        insertButton.setBackground(new Color(50,50,50));

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(50,50,50));

        JPanel footerContainer = new JPanel(new GridLayout(1,2));
        footerContainer.add(customQuerries);
        footerContainer.add(insertButton);
        footer.add(footerContainer);

        this.add(header, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.add(footer,BorderLayout.SOUTH);
	 }



	/**
	 * set the given component as body
	 */
	public void setBody(JComponent newBody){

		this.remove(body);
		body = newBody;
		this.add(body, BorderLayout.CENTER);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				revalidate();
				repaint();
			}
		});
	}




    public Callback newSearchQuerryCallback(){
        return searchButton.clickedCallback();
    }


    public Callback newCustomQuerryCallback(){
        return customQuerries.clickedCallback();
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


    public AvailableRequest getCustomRequest(){
        return AvailableRequest.values()[customQuerries.getSelectedIndex()];
    }


    public Callback insertDataCallback(){
        return insertButton.clickedCallback();
    }

    public Callback ldbButtonCallback(){
        return ldbButton.clickedCallback();
    }

}

package view;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import utils.BackgroundPanel;
import utils.Button;
import utils.Callback;

public class BodyResult extends JPanel{

	
	private static final long serialVersionUID = 1L;

	private Button closeButton; 
	private JPanel tablePanel;
	
	

	public BodyResult(){

		this.setLayout(new BorderLayout());
		
		closeButton = new Button("x");
		JPanel topContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topContainer.setOpaque(false);
		topContainer.add(closeButton); 
		
		tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);

        this.add(topContainer, BorderLayout.NORTH);
        this.setOpaque(false);
        this.setBackground(Color.CYAN);
		this.add(tablePanel);
		
	}
	
	
	
	public void setData(String[][] datas){


		if(datas.length <= 0){
		return;
		}
		
		int rowSize = datas.length; 
		int colSize = datas[0].length; 
		
		
		JScrollPane pane = new JScrollPane();
		pane.setBorder(new EmptyBorder(0,0,0,0));
		
		JPanel dataContainer = new BackgroundPanel("images/background.png");
        dataContainer.setOpaque(false);
		BoxLayout bl = new BoxLayout(dataContainer, BoxLayout.LINE_AXIS ); 
		dataContainer.setLayout(bl);
		
		for(int col = 0; col < colSize; col++){
			
			JPanel colContainer = new JPanel();
			colContainer.setOpaque(false);
			bl = new BoxLayout(colContainer, BoxLayout.PAGE_AXIS);
			colContainer.setLayout(bl);
			
			for(int row = 0; row < rowSize; row++){
				
				JLabel label = new JLabel(datas[row][col]);
				colContainer.add(label);
			}
			colContainer.setBorder(new EmptyBorder(0,0,0,40));
			
			dataContainer.add(colContainer);
		}


		this.tablePanel.removeAll();
		pane.setViewportView(dataContainer);
		this.tablePanel.add(pane);
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				BodyResult.this.repaint();
			}});
		
	}
	
	
	
	public Callback clickCloseCallback(){
		return closeButton.clickedCallback();
	}
	
	
	
}

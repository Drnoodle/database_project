package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.Button;
import utils.Callback;

public class BodyResult extends JPanel{

	
	private static final long serialVersionUID = 1L;

	private Button closeButton; 
	private JPanel tablePanel;
	
	

	public BodyResult(){
		
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		
		closeButton = new Button("x");
		JPanel topContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topContainer.setOpaque(false);
		topContainer.add(closeButton); 
		
		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);
		
		this.add(topContainer, BorderLayout.NORTH);
		this.add(tablePanel);
		
	}
	
	
	
	public void setData(String[][] datas){
		
		if(datas.length <= 0){
		return;
		}
		
		int rowSize = datas.length; 
		int colSize = datas[0].length; 
		
		
		
		GridLayout gl = new GridLayout(rowSize, colSize); 
		JPanel dataPanel = new JPanel(gl);
		dataPanel.setOpaque(false);
		
		for(int row = 0; row < colSize; row++){
			for(int col = 0; col < colSize; col++){
				JLabel label = new JLabel(datas[row][col]);
				dataPanel.add(label);
			}
		}
		
		this.tablePanel.removeAll();
		this.tablePanel.add(dataPanel);
		
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

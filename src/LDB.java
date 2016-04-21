import java.sql.SQLException;

import javax.swing.JFrame;

import controler.ContentPaneControler;
import data_access.CsvReader;
import data_access.DBConnection;
import import_csv.AbstractImport;
import utils.CsvFile;

public class LDB {

	
	public static void main(String[] args){

		ContentPaneControler.getInstance().active();
		
		JFrame frame = new JFrame();
		frame.setContentPane(ContentPaneControler.getInstance().getComponent());
		frame.setSize(800,600);
		frame.setTitle("library database");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
}

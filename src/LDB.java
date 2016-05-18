import javax.swing.*;

import controler.ContentPaneControler;
import data_access.DBConnection;
import data_access.DBSearch;
import library.Award;

import java.sql.Connection;
import java.sql.SQLException;

public class LDB {

	
	public static void main(String[] args){


		JFrame frame = new JFrame();
        frame.setSize(800,600);
        frame.setTitle("library database");
        frame.setContentPane(ContentPaneControler.getInstance().getComponent());
        ContentPaneControler.getInstance().active();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
	
}

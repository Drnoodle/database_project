import controler.ContentPaneControler;
import data_access.DBConnection;
import import_csv.AbstractImport;
import utils.CsvFile;

import javax.swing.*;
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

        try {

            System.out.println("pub");
            AbstractImport import_ = AbstractImport.abstractImport(CsvFile.PUBLICATIONS, DBConnection.getConnection());
            import_.insert();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	
}

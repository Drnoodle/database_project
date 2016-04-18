import data_access.CsvReader;
import utils.CsvFile;

public class LDB {

	
	public static void main(String[] args){
	
		/*
		ContentPaneControler.getInstance().active();
		
		JFrame frame = new JFrame();
		frame.setContentPane(ContentPaneControler.getInstance().getComponent());
		frame.setSize(800,600);
		frame.setTitle("library database");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		
		
		int i = 0; 
		
		CsvReader reader = new CsvReader(CsvFile.NOTES.path());
		
		while( i<1000 && reader.hasNext()){
			
			String[] row = reader.next();
			for(String col : row){
				System.out.print(CsvReader.removeWeirdChar(CsvReader.removeHtml(col)));				
			}
			
			System.out.println("");
			i++;
		}
		
		
	}
	
}

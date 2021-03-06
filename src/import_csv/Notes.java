package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import data_access.CsvReader;
import utils.CsvFile;

public class Notes extends AbstractImport{

	public Notes(Connection conn) throws SQLException {
		super(CsvFile.NOTES, conn);
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		
		try {
		Integer.parseInt(row[0]);
		} catch(NumberFormatException e){
			return;
		}
		
		
		String note = "";
		if(row.length == 2){

			note = CsvReader.removeHtml(row[1]);

			if(note.length() > 3000){
				note = note.substring(0,3000);
			}

			if(note.length()==0){
				return;
			}
		}

		
		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, note);
		this.insert.addBatch();
		
	}

	@Override
	protected String getSqlRequest() {
		return "INSERT INTO note (ID_NOTE, NOTE) VALUES (?,?);";
	}


}

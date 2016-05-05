package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import utils.CsvFile;

public class Author extends AbstractImport {

	public Author(Connection conn) throws SQLException {
		super(CsvFile.AUTHORS, conn);
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		assert(row.length == 2); 
		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.setStringInInsert(3, row[2]);
		this.setStringInInsert(4, row[3]);
		this.setStringInInsert(5, row[4]);
		this.setStringInInsert(6, row[5]);
		
		String date = row[6];
		if(date.length() != 10){
			date = "";
		}
		this.setStringInInsert(7, date);
		
		date = row[7];
		if(date.length() != 10){
			date = "";
		}
		this.setStringInInsert(8, date);
		
		this.setStringInInsert(9, row[8]);
		this.setStringInInsert(10, row[9]);
		this.setStringInInsert(11, row[10]);
		this.setStringInInsert(12, row[11]);
		this.insert.addBatch();
	}

	@Override
	protected String getSqlRequest() {
		
		return new StringBuilder()
				.append("INSERT INTO author (")
				.append("ID_AUTHOR, NAME, LEGAL_NAME,LAST_NAME,PSEUDO, BIRTHPLACE,")
				.append("BIRTHDATE,DEATHDATE,EMAIL,IMG_LINK,ID_LANGUAGE,ID_NOTE")
				.append(") VALUES ")
				.append("(?,?,?,?,?,?,?,?,?,?,?,?);")
				.toString();
			
	}


	
	
}

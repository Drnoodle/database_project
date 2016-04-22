package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import utils.CsvFile;

public class Language extends AbstractImport{

	public Language(Connection conn) throws SQLException {
		super(CsvFile.LANGUAGES, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.setStringInInsert(3, row[2]);
		this.setStringInInsert(4, row[3]);
		this.insert.addBatch();
	}

	@Override
	protected String getSqlRequest() {
		return new StringBuilder()
				.append("INSERT INTO language ")
				.append("(ID_LANGUAGE,NAME, CODE, SCRIPT)")
				.append(" VALUES ")
				.append("(?,?,?,?);")
				.toString();
	}

	  

	
	
}

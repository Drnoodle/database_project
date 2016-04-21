package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class PublicationAuthor extends AbstractImport{

	public PublicationAuthor(DBConnection conn) throws SQLException {
		super(CsvFile.PUBLICATIONS_AUTHORS, conn);
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
	
		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.insert.addBatch();
		
	}

	@Override
	protected String getSqlRequest() {
		  
		return new StringBuilder()
				.append("INSERT INTO publication_author ")
				.append(" (ID_PUBLICATION,ID_AUTHOR)")
				.append(" VALUES (?,?);")
				.toString();
	}

}

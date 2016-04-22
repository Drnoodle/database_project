package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import utils.CsvFile;

public class TaggedBy extends AbstractImport{

	public TaggedBy(Connection conn) throws SQLException {
		super(CsvFile.TITLES_TAG, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.insert.addBatch();
	}

	@Override
	protected String getSqlRequest() {
		 
		return "INSERT INTO tagged_by (ID_TAG,ID_TITLE) VALUES (?,?);";
	}

}

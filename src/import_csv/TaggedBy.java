package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class TaggedBy extends AbstractImport{

	public TaggedBy(DBConnection conn) throws SQLException {
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

package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class TitleHasPublication extends AbstractImport{

	public TitleHasPublication(DBConnection conn) throws SQLException {
		super(CsvFile.PUBLICATIONS_CONTENT, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		
		this.setStringInInsert(1, row[1]);
		this.setStringInInsert(2, row[0]);
		this.insert.addBatch();
	}

	@Override
	protected String getSqlRequest() {
		
		return new StringBuilder()
				.append("INSERT INTO title_has_publication")
				.append(" (ID_TITLE,ID_PUBLICATION) ")
				.append(" VALUES (?,?); ")
				.toString();
	}

}

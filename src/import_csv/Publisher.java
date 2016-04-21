package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class Publisher extends AbstractImport{

	public Publisher(DBConnection conn) throws SQLException {
		super(CsvFile.PUBLISHERS, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.setStringInInsert(3, row[2]);
		this.insert.addBatch();
	}

	@Override
	protected String getSqlRequest() {

		return new StringBuilder()
				.append("INSERT INTO publisher ")
				.append("(ID_PUBLISHER,NAME,ID_NOTE)")
				.append(" VALUES (?,?,?);")
				.toString();
				
	}

}

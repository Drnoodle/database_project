package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class WebPage extends AbstractImport{

	public WebPage(DBConnection conn) throws SQLException {
		super(CsvFile.WEBPAGES, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[3]);
		this.setStringInInsert(3, row[1]);
		this.setStringInInsert(4, row[5]);
		this.setStringInInsert(5, row[2]);
		this.setStringInInsert(6, row[6]);
		this.insert.addBatch();
	}

	@Override
	protected String getSqlRequest() {
		
		return new StringBuilder()
				.append("INSERT INTO webpage ")
				.append(" (ID_WEBPAGE,URL,ID_AUTHOR,")
				.append("ID_TITLE,ID_PUBLISHER, ID_AWARD_TYPE)")
				.append(" VALUES (?,?,?,?,?,?);")
				.toString();
	}

}

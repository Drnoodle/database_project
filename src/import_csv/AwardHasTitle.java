package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class AwardHasTitle extends AbstractImport{

	public AwardHasTitle(DBConnection conn) throws SQLException {
		super(CsvFile.TITLES_AWARDS, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		
		this.setStringInInsert(1, row[1]);
		this.setStringInInsert(2, row[2]);
		this.insert.addBatch();
		
	}

	@Override
	protected String getSqlRequest() {

		return new StringBuilder()
				.append("INSERT INTO award_has_title")
				.append(" (ID_AWARD,ID_TITLE) ")
				.append(" VALUES (?,?);")
				.toString();
	}

}

package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import utils.CsvFile;

public class Review extends AbstractImport{

	public Review(Connection conn) throws SQLException {
		super(CsvFile.REVIEWS, conn);
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
				.append("INSERT INTO review  ")
				.append("(ID_TITLE_REVIEW,ID_TITLE_REVIEWED)")
				.append("VALUES (?,?);")
				.toString();
				
	}

}

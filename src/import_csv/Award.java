package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import utils.CsvFile;

public class Award extends AbstractImport{

	public Award(Connection conn) throws SQLException {
		super(CsvFile.AWARDS, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.setStringInInsert(3, row[2]);
		this.setStringInInsert(4, row[6]);
		this.setStringInInsert(5, row[4]);
		this.insert.addBatch();
		
	}

	@Override
	protected String getSqlRequest() {
		return new StringBuilder()
				.append("INSERT INTO award ")
				.append("(ID_AWARD,TITLE,DATE,ID_NOTE,ID_AWARD_TYPE)")
				.append(" VALUES (?,?,?,?,?);")
				.toString();
	}

}

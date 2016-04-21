package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class TilteSerie extends AbstractImport{

	public TilteSerie(DBConnection conn) throws SQLException {
		super(CsvFile.TITLES_SERIES, conn);
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
				.append("INSERT INTO title_serie")
				.append(" (ID_SERIE,TITLE,ID_PARENT,ID_NOTE) ")
				.append(" VALUES (?,?,?,?);")
				.toString();
	}

	
	
	
}

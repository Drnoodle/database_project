package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class PublicationSerie extends AbstractImport{

	public PublicationSerie(DBConnection conn) throws SQLException {
		super(CsvFile.PUBLICATIONS_SERIES, conn);
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
				.append("INSERT INTO publication_serie")
				.append(" (ID_PUBLICATION_SERIE,NAME,ID_NOTE)")
				.append(" VALUES (?,?,?);")
				.toString();
	}

}

package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;

public class NotesImport extends AbstractImport{

	public NotesImport(CsvFile csvFile, DBConnection conn) throws SQLException {
		super(csvFile, conn);
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		assert(row.length == 2); 
		this.insert.setString(1, row[0]);
		this.insert.setString(2, row[1]);
	}

	@Override
	protected String getSqlRequest() {
		return "INSERT INTO notes (idNote, value) VALUES (?,?);";
	}


}

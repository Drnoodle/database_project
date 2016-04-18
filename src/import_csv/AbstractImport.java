package import_csv;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import data_access.CsvReader;
import data_access.DBConnection;
import utils.CsvFile;

public abstract class AbstractImport {

	/*
	 * csv file to import
	 * 
	 */
	protected CsvFile csvFile;
	protected PreparedStatement insert; 
	
	
	/*
	 * constructor
	 */
	public AbstractImport(CsvFile csvFile, DBConnection conn) throws SQLException{
		
		this.csvFile = csvFile;
		this.insert = conn
				.getConnection()
				.prepareStatement(this.getSqlRequest());
	}


	/*
	 * submits the cluster of request added in the batch
	 */
	public int[] commitBatch() throws SQLException{
		return insert.executeBatch();
	}
	
	
	/*
	 * load all row to db
	 */
	public void startImport() throws SQLException{
		
		CsvReader reader = new CsvReader(csvFile.path());
		
		while(reader.hasNext()){
			String[] row = reader.next();
			this.addBatch(row);
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * non abstract class know how to parametrize the
	 * prepared statement with the row.
	 * the sql request is then added.
	 */
	protected abstract void addBatch(String[] row) throws SQLException;
	
	/*
	 * get the sql request needed to make the prepared statement
	 */
	protected abstract String getSqlRequest();
	
	
}

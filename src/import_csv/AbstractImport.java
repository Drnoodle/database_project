package import_csv;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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
	protected PreparedStatement truncate; 
	
	
	/*
	 * constructor
	 */
	public AbstractImport(CsvFile csvFile, DBConnection conn) throws SQLException{
		
		this.csvFile = csvFile;
		this.insert = conn
				.getConnection()
				.prepareStatement(this.getSqlRequest());
	}


	private void clearInsertParameters() throws SQLException{
		this.insert.clearParameters();
	}
	
	/*
	 * submits the cluster of request added in the batch
	 */
	public int[] insertCommit() throws SQLException{
		return insert.executeBatch();
	}
	
	
	/*
	 * load all row to db
	 */
	public void insertAll() throws SQLException{
		
		CsvReader reader = new CsvReader(csvFile.path());
		
		while(reader.hasNext()){
			String[] row = reader.next();
			this.clearInsertParameters();
			this.addBatch(row);
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * load all row to db
	 */
	public void insert10EntriesTest() throws SQLException{
		
		CsvReader reader = new CsvReader(csvFile.path());
		int i = 0; 
		
		while(i<10 && reader.hasNext()){
			String[] row = reader.next();
			this.clearInsertParameters();
			this.addBatch(row);
			System.out.println(insert.toString());
			i++;
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
	
	protected void setStringInInsert(int parameter, String value) throws SQLException{
		
		if(value.equals("\\N")){
			this.insert.setString(parameter, null);
		}
		else {
			this.insert.setString(parameter, value);
		}
		
	}

	public static AbstractImport abstractImport(CsvFile file, DBConnection conn) 
			throws SQLException {

		switch(file){
		case AUTHORS : return new Author(conn);
		case AWARD_CATEGORIES : return new AwardCategory(conn);
		case AWARD_TYPES : return new AwardType(conn);
		case AWARDS : return new Award(conn);
		case LANGUAGES : return new Language(conn);
		case NOTES : return new Notes(conn);
		case PUBLICATIONS : return new Publication(conn);
		case PUBLICATIONS_AUTHORS : return new PublicationAuthor(conn);
		case PUBLICATIONS_CONTENT : return new TitleHasPublication(conn);
		case PUBLICATIONS_SERIES: return new PublicationSerie(conn);
		case PUBLISHERS : return new Publisher(conn);
		case REVIEWS : return new Review(conn);
		case TAGS : return new TaggedBy(conn);
		case TITLES : return new Title(conn);
		case TITLES_AWARDS : return new AwardHasTitle(conn);
		case TITLES_SERIES : return new TilteSerie(conn);
		case TITLES_TAG : return new Tag(conn);
		case WEBPAGES : return new WebPage(conn);
		default : throw new InvalidParameterException();
		}

	}

}

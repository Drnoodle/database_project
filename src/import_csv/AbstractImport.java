package import_csv;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import data_access.CsvReader;
import utils.CsvFile;

public abstract class AbstractImport {

	/*
	 * csv file to import
	 * 
	 */
	protected CsvFile csvFile;
	protected PreparedStatement insert; 
	protected PreparedStatement truncate; 
	private PreparedStatement activeConstraint; 
	private PreparedStatement inactiveConstraint; 
	
	/*
	 * constructor
	 */
	public AbstractImport(CsvFile csvFile, Connection conn) throws SQLException{
		
		String req = new StringBuilder()
				.append("SET FOREIGN_KEY_CHECKS=0")
				.toString();
		inactiveConstraint = conn.prepareStatement(req);
		
		req = new StringBuilder()
				.append("SET FOREIGN_KEY_CHECKS=1")
				.toString();
		activeConstraint = conn.prepareStatement(req);

		this.csvFile = csvFile;
		this.insert = conn
				.prepareStatement(this.getSqlRequest());
		
		this.insert.executeBatch();
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
	 * insert & commit 100 by 100 rows to db
	 */
	public void insert() throws SQLException{
		
		CsvReader reader = new CsvReader(csvFile.path());
		
		this.inactiveConstraint();
		
		int i =1;
		while(i<501 && reader.hasNext()){
			String[] row = reader.next();
			this.clearInsertParameters();
			this.addBatch(row);
			i++;
			if(i%100 == 0){
				this.insertCommit();
				System.out.println(i+" rows inserted");
			}
		}
		
		this.activeConstraint();
		this.insert.executeBatch();
		
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
		
		if(value.equals("\\N") || value.equals("")){
			this.insert.setString(parameter, null);
			return;
		}
		this.insert.setString(parameter, value);
		
		
	}

	
	public void inactiveConstraint() throws SQLException{
		
		
		inactiveConstraint.executeUpdate();
	}
	
	
	public void activeConstraint() throws SQLException{
		activeConstraint.executeUpdate();
	}
	
	
	public static AbstractImport abstractImport(CsvFile file, Connection conn) 
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

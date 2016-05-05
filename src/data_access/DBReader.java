package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import view.BodyForm.AvailableRequest;

public class DBReader {

	
	
	/*
	 * Connection to database
	 * 
	 */
	
	private static final String URL = 
			new StringBuilder("jdbc:mysql://localhost:3306/DB2016_G21")
			.append("?useUnicode=true&characterEncoding=utf8")
			.append("&connectionCollation=utf8_general_ci&useSSL=false")
			.toString();
	
	private static final String USER = "root";
	
	private static final String PASSWORD = "hb1992";
	
	
	
	public static Connection getConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
		
		return conn;
		
	}
	
	/*
	 * Factory of prepared statement required in the assignment
	 */
	
	public static PreparedStatement getStatement(AvailableRequest req, Connection conn) 
			throws SQLException{

		switch(req){

		case AUTHOR_SCIENCE_FICTION : 
			return authorScienceFiction(conn);
		case COMIC_DETAIL:	
			return comicDetail(conn); 
		case OLDEST_AUTHOR:
			return oldestAuthor(conn); 
		case PUBLISHER_AVERAGE_NOVEL_PRICE:
			return publisherAverageNovelPrice(conn); 
		case TEN_MOST_PUBLISHED_AUTHORS: 
			return tenMostPublishedAuthors(conn);
		case THREE_MOST_POPULAR_TITLE: 
			return threeMostPopularTitle(conn); 
		case YEAR_TO_TOTAL_PUB: 
			return yearToTotalPub(conn);
		case YOUNGEST_AUTHOR: 
			return youngestAuthor(conn);
		default: 
			throw new IllegalArgumentException();

		}
		
	}


	

	
	private static PreparedStatement authorScienceFiction(Connection conn) throws SQLException{
		
		String req = new StringBuilder()
				.append("SELECT A.NAME, COUNT(*) as totalPub FROM title_has_publication TP ")
				.append("INNER JOIN publication_author PA ON PA.ID_PUBLICATION = TP.ID_PUBLICATION ")
				.append("INNER JOIN author A ON A.ID_AUTHOR = PA.ID_AUTHOR ")
				.append("WHERE TP.ID_TITLE IN ( ")
				.append("SELECT ID_TITLE FROM tagged_by ")
				.append("WHERE ID_TAG = (SELECT ID_TAG FROM tag WHERE NAME='science fiction' ) ")
				.append(")")
				.append("GROUP BY(A.ID_AUTHOR) ")
				.append("ORDER BY totalPub DESC ")
				.append("LIMIT 0,1;")
				.toString();

		return conn.prepareStatement(req);
	}

	private static PreparedStatement comicDetail(Connection conn) throws SQLException{

		String req = new StringBuilder()
				.append("SELECT CEIL(P.PAGES/50)*50, COUNT(*) FROM publication P ")
				.append("INNER JOIN title_has_publication TP ")
				.append("ON TP.ID_PUBLICATION = P.ID_PUBLICATION ")
				.append("INNER JOIN title T ON T.ID_TITLE = TP.ID_PUBLICATION ")
				.append("WHERE T.IS_GRAPHICS = 'Y' ")
				.append("GROUP BY(CEIL((P.PAGES+1)/50));")
				.toString();

		return conn.prepareStatement(req);
	}

	private static PreparedStatement oldestAuthor(Connection conn) throws SQLException{

		String req = new StringBuilder()
				.append("SELECT * FROM author WHERE ID_AUTHOR IN ( ")
				.append("SELECT PA.ID_AUTHOR FROM publication_author PA ")
				.append("INNER JOIN publication P ")
				.append("ON P.ID_PUBLICATION = PA.ID_PUBLICATION ")
				.append("WHERE YEAR(P.DATE) = 1993 ")
				.append(") ")
				.append("ORDER BY YEAR(BIRTHDATE) ASC, MONTH(BIRTHDATE) ASC, DAY(BIRTHDATE) ASC ")
				.append("LIMIT 0,1;")
				.toString(); 

		return conn.prepareStatement(req);

	}

	private static PreparedStatement youngestAuthor(Connection conn) throws SQLException{


		String req = new StringBuilder()
				.append("SELECT * FROM author WHERE ID_AUTHOR IN ( ")
				.append("SELECT PA.ID_AUTHOR FROM publication_author PA ")
				.append("INNER JOIN publication P ")
				.append("ON P.ID_PUBLICATION = PA.ID_PUBLICATION ")
				.append("WHERE YEAR(P.DATE) = 1993 ")
				.append(") ")
				.append("ORDER BY YEAR(BIRTHDATE) DESC, MONTH(BIRTHDATE) DESC, DAY(BIRTHDATE) DESC ")
				.append("LIMIT 0,1;")
				.toString(); 

		return conn.prepareStatement(req);
	}

	private static PreparedStatement publisherAverageNovelPrice(Connection conn) throws SQLException{

		String req = new StringBuilder()
				.append("SELECT SUM(PRICE)/COUNT(*) FROM publication ")
				.append("WHERE CURRENCY='$' ")
				.append("GROUP BY(ID_PUBLISHER);")
				.toString();

		return conn.prepareStatement(req);
	}

	private static PreparedStatement tenMostPublishedAuthors(Connection conn) throws SQLException{

		String req = new StringBuilder()
				.append("SELECT A.NAME, COUNT(*) as totalPub FROM author A ")
				.append("INNER JOIN publication_author P ")
				.append("ON A.ID_AUTHOR = P.ID_AUTHOR ")
				.append("GROUP BY(A.ID_AUTHOR) ")
				.append("ORDER BY totalPub DESC ")
				.append("LIMIT 0,10;")
				.toString();

		return conn.prepareStatement(req);

	}

	private static PreparedStatement threeMostPopularTitle(Connection conn) throws SQLException{

		String req = new StringBuilder()
				.append("SELECT T.TITLE, COUNT(*) AS popularity  FROM ( ")
				.append("SELECT ID_TITLE AS idTitle FROM award_has_title ")
				.append("UNION ALL ")
				.append("SELECT ID_TITLE_REVIEWED AS idTitle FROM review) ID ")
				.append("INNER JOIN title T WHERE T.ID_TITLE = idTitle ")
				.append("GROUP BY(IDTITLE) ")
				.append("ORDER BY  popularity DESC ")
				.append("LIMIT 0,3;")
				.toString();

		return conn.prepareStatement(req);
	}

	private static PreparedStatement yearToTotalPub(Connection conn) throws SQLException{

		String req = new StringBuilder()
				.append("SELECT YEAR(DATE) AS year, COUNT(*) AS total ")
				.append("FROM publication ")
				.append("GROUP BY(YEAR(DATE));")
				.toString();

		return conn.prepareStatement(req);

	}



	/*
	 * Extract a 2 dimensional string array with a statement. 
	 */
	public static String[][] getData(PreparedStatement statement) throws SQLException{
		
		
		ResultSet resultSet = statement.executeQuery();
		
		
		ResultSetMetaData metaData = resultSet.getMetaData(); 

		
		resultSet.last(); 
		int totalRow = resultSet.getRow(); 
		resultSet.beforeFirst();
		int totalCol = resultSet.getMetaData().getColumnCount(); 
		
		String[][] results = new String[totalRow+1][totalCol];
		
		for(int i = 0; i < totalCol; i++){
			results[0][i] = metaData.getColumnName(i+1);
		}
		
		
		int currentRow = 0; 
		while(resultSet.next()){
			
			for(int currentCol = 0; currentCol<totalCol; currentCol++){
				results[currentRow][currentCol] = resultSet.getString(currentCol+1);
			}
			
			currentRow++;
		}
		
		
		return results;
	}


	
}

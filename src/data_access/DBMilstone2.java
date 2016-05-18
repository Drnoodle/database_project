package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import view.BodyForm.AvailableRequest;

public class DBMilstone2 {


    private PreparedStatement authorScienceFiction;
    private PreparedStatement comicDetail;
    private PreparedStatement oldestAuthor;
    private PreparedStatement publisherAverageNovelPrice;
    private PreparedStatement tenMostPublishedAuthors;
    private PreparedStatement threeMostPopularTitle;
    private PreparedStatement yearToTotalPub;
    private PreparedStatement youngestAuthor;


    public DBMilstone2(Connection conn) throws SQLException {

        String req = new StringBuilder()
                .append("SELECT A.NAME, COUNT(*) as totalPub FROM title_has_publication TP ")
                .append("INNER JOIN publication_author PA ON PA.ID_PUBLICATION = TP.ID_PUBLICATION ")
                .append("INNER JOIN Author A ON A.ID_AUTHOR = PA.ID_AUTHOR ")
                .append("WHERE TP.ID_TITLE IN ( ")
                .append("SELECT ID_TITLE FROM tagged_by ")
                .append("WHERE ID_TAG = (SELECT ID_TAG FROM tag WHERE NAME='science fiction' ) ")
                .append(")")
                .append("GROUP BY(A.ID_AUTHOR) ")
                .append("ORDER BY totalPub DESC ")
                .append("LIMIT 0,1;")
                .toString();

        authorScienceFiction = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT CEIL(P.PAGES/50)*50, COUNT(*) FROM publication P ")
                .append("INNER JOIN title_has_publication TP ")
                .append("ON TP.ID_PUBLICATION = P.ID_PUBLICATION ")
                .append("INNER JOIN title T ON T.ID_TITLE = TP.ID_PUBLICATION ")
                .append("WHERE T.IS_GRAPHICS = 'Y' ")
                .append("GROUP BY(CEIL((P.PAGES+1)/50));")
                .toString();

        comicDetail = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT * FROM Author WHERE ID_AUTHOR IN ( ")
                .append("SELECT PA.ID_AUTHOR FROM publication_author PA ")
                .append("INNER JOIN publication P ")
                .append("ON P.ID_PUBLICATION = PA.ID_PUBLICATION ")
                .append("WHERE YEAR(P.DATE) = 1993 ")
                .append(") ")
                .append("ORDER BY YEAR(BIRTHDATE) ASC, MONTH(BIRTHDATE) ASC, DAY(BIRTHDATE) ASC ")
                .append("LIMIT 0,1;")
                .toString();
        oldestAuthor = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT SUM(PRICE)/COUNT(*) FROM publication ")
                .append("WHERE CURRENCY='$' ")
                .append("GROUP BY(ID_PUBLISHER);")
                .toString();
        publisherAverageNovelPrice = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT A.NAME, COUNT(*) as totalPub FROM Author A ")
                .append("INNER JOIN publication_author P ")
                .append("ON A.ID_AUTHOR = P.ID_AUTHOR ")
                .append("GROUP BY(A.ID_AUTHOR) ")
                .append("ORDER BY totalPub DESC ")
                .append("LIMIT 0,10;")
                .toString();
        tenMostPublishedAuthors = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT T.TITLE, COUNT(*) AS popularity  FROM ( ")
                .append("SELECT ID_TITLE AS idTitle FROM award_has_title ")
                .append("UNION ALL ")
                .append("SELECT ID_TITLE_REVIEWED AS idTitle FROM review) ID ")
                .append("INNER JOIN title T WHERE T.ID_TITLE = idTitle ")
                .append("GROUP BY(IDTITLE) ")
                .append("ORDER BY  popularity DESC ")
                .append("LIMIT 0,3;")
                .toString();
        threeMostPopularTitle = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT YEAR(DATE) AS year, COUNT(*) AS total ")
                .append("FROM publication ")
                .append("GROUP BY(YEAR(DATE));")
                .toString();

        yearToTotalPub = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT * FROM Author WHERE ID_AUTHOR IN ( ")
                .append("SELECT PA.ID_AUTHOR FROM publication_author PA ")
                .append("INNER JOIN publication P ")
                .append("ON P.ID_PUBLICATION = PA.ID_PUBLICATION ")
                .append("WHERE YEAR(P.DATE) = 1993 ")
                .append(") ")
                .append("ORDER BY YEAR(BIRTHDATE) DESC, MONTH(BIRTHDATE) DESC, DAY(BIRTHDATE) DESC ")
                .append("LIMIT 0,1;")
                .toString();
        youngestAuthor = conn.prepareStatement(req);

    }
	
	/*
	 * Factory of prepared statement required in the assignment
	 */
	
	public PreparedStatement getStatement(AvailableRequest req)
			throws SQLException{

		switch(req){

		case AUTHOR_SCIENCE_FICTION : return authorScienceFiction;
		case COMIC_DETAIL:	return comicDetail;
		case OLDEST_AUTHOR:return oldestAuthor;
		case PUBLISHER_AVERAGE_NOVEL_PRICE:return publisherAverageNovelPrice;
		case TEN_MOST_PUBLISHED_AUTHORS: return tenMostPublishedAuthors;
		case THREE_MOST_POPULAR_TITLE: return threeMostPopularTitle;
		case YEAR_TO_TOTAL_PUB: return yearToTotalPub;
		case YOUNGEST_AUTHOR: return youngestAuthor;
		default: throw new IllegalArgumentException();

		}
		
	}


	
}

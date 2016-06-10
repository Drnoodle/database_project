package data_access;

import view.ContentPane.AvailableRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class DBMilstone2 {


    private Map<AvailableRequest, PreparedStatement> requests = new HashMap();

    public DBMilstone2(Connection conn) throws SQLException {


        String req = new String();


        // For every year, output the year and the number of publications for said year

        req = "For every year, output the year and the numbe2r of publications for said year " +
        "SELECT YEAR(DATE) AS year, "+
        "COUNT(*) AS total FROM publication "+
        "GROUP BY(YEAR(DATE)); ";

        PreparedStatement stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.PUBLICATION_FOR_SAID_YEAR, stat);


        // Output the names of the ten authors with most publications.
        req = "SELECT A.NAME, A.BIRTHDATE, " +
        "TX.ID_PUBLICATION, TX.DATE as publicationDate " +
        "FROM author A " +
        "INNER JOIN " +
        "(SELECT PA.ID_AUTHOR, P.ID_PUBLICATION, P.DATE " +
        "FROM publication P " +
        "INNER JOIN " +
        "(SELECT * " +
                "FROM publication_author) PA " +
        "ON P.ID_PUBLICATION = PA.ID_PUBLICATION " +
        "WHERE YEAR(P.DATE) = 2010) TX " +
        "ON A.ID_AUTHOR = TX.ID_AUTHOR " +
        "WHERE A.BIRTHDATE != 'null' " +
        "ORDER BY A.BIRTHDATE ASC (resp. DESC) LIMIT 0,1 ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.TEN_AUTHOR_WITH_MOST_PUBLICATION , stat);



        // How many comics have publications with less than 50, 100 or more than 100 pages?
        // SELECT COUNT(*) as nOfPublications
        req = "FROM publication P " +
        "INNER JOIN " +
        "(SELECT TP.ID_TITLE, TP.ID_PUBLICATION " +
        "FROM title_has_publication TP " +
        "INNER JOIN " +
        "(SELECT T.ID_TITLE " +
        "FROM title T " +
        "WHERE T.IS_GRAPHICS = 'Y') TX " +
        "ON TP.ID_TITLE = TX.ID_TITLE) TY " +
        "ON P.ID_PUBLICATION = TY.ID_PUBLICATION " +
        "WHERE P.PAGES >= 100 #input your condition ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.COMICS_PAGES_CLUSTERED , stat);

                // For every publisher, calculate the average price of its published novels
        req = "SELECT TY.ID_PUBLISHER, AVG(TY.PRICE) as averagePrice " +
        "FROM title T " +
        "INNER JOIN " +
        "(SELECT TP.ID_TITLE, TX.ID_PUBLICATION, " +
                "TX.ID_PUBLISHER, TX.PRICE " +
        "FROM title_has_publication TP " +
        "INNER JOIN " +
        "(SELECT p.ID_PUBLICATION, P.ID_PUBLISHER, P.PRICE " +
        "FROM publication P " +
        "WHERE CURRENCY='$' && P.ID_PUBLISHER != 'null') TX " +
        "ON TP.ID_PUBLICATION = TX.ID_PUBLICATION) TY " +
        "ON T.ID_TITLE = TY.ID_TITLE " +
        "WHERE T.TYPE = 9 " +
        "GROUP BY TY.ID_PUBLISHER ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.AVERAGE_NOVEL_PRICE_BY_PUBLISHER, stat);


        // Author with the highest number of titles that are tagged as science fiction ?
        //        SELECT A.NAME, COUNT(*) as totalTitle
        req = "FROM title_has_publication TP " +
        "INNER JOIN publication_author PA " +
        "ON PA.ID_PUBLICATION = TP.ID_PUBLICATION " +
        "INNER JOIN author A ON A.ID_AUTHOR = PA.ID_AUTHOR " +
        "WHERE TP.ID_TITLE IN ( " +
                "SELECT ID_TITLE " +
        "FROM tagged_by " +
        "WHERE ID_TAG = ( " +
                "SELECT ID_TAG " +
        "FROM tag " +
        "WHERE NAME='science fiction' ) ) " +
        "GROUP BY(A.ID_AUTHOR) " +
                "ORDER BY totalTitle DESC LIMIT 0,1 ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.AUTHOR_SCIENCE_FICTION, stat);



        // The three most popular titles
        req = "SELECT T.TITLE, COUNT(*) AS popularity " +
        "FROM ( " +
                "SELECT ID_TITLE AS idTitle FROM award_has_title " +
                "UNION ALL " +
                "SELECT ID_TITLE AS idTitle FROM review) ID " +
        "INNER JOIN title T " +
        "WHERE T.ID_TITLE = idTitle " +
        "GROUP BY(idTitle) " +
                "ORDER BY popularity DESC LIMIT 0,3 ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.THREE_MOST_POPULAR_TITLE, stat);



                // Average price per currency of the publications of the  most popular title
        req = "SELECT P.CURRENCY, AVG(P.PRICE) " +
        "FROM publication P " +
        "INNER JOIN " +
        "(SELECT DISTINCT TP2.ID_PUBLICATION " +
        "FROM title_has_publication TP2 " +
        "INNER JOIN " +
        "(SELECT TP.ID_TITLE " +
        "FROM title_has_publication TP " +
        "GROUP BY TP.ID_TITLE " +
        "ORDER BY COUNT(TP.ID_TITLE) DESC LIMIT 0,1) TX " +
        "ON TP2.ID_TITLE = TX.ID_TITLE) TY " +
        "ON P.ID_PUBLICATION = TY.ID_PUBLICATION " +
        "WHERE P.CURRENCY != 'null' " +
        "GROUP BY P.CURRENCY ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.AVERAGE_PRICE_BY_CURRENY_MOST_POPULAR_TITLE, stat);





        // Names of the top ten title series with most awards
        req = "SELECT TS.TITLE, TX.nOfAwards " +
        "FROM title_serie TS " +
        "INNER JOIN (SELECT T.ID_SERIE, COUNT(*) as nOfAwards " +
        "FROM title T " +
        "INNER JOIN award_has_title TA " +
        "ON T.ID_TITLE = TA.ID_TITLE " +
        "GROUP BY T.ID_TITLE) TX " +
        "ON TX.ID_SERIE = TS.ID_SERIE " +
        "ORDER BY nOfAwards DESC " +
        "LIMIT 0,10 " ;

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.MOST_AWARDED_AUTHORS, stat);



                // Name of the author who has received the most awards after his/her death.


        req = "SELECT TZ.NAME, COUNT(TZ.ID_AUTHOR) as nOfAwards " +
        "FROM award_has_title AWT " +
        "(SELECT AUT.ID_AUTHOR, AUT.ID_TITLE, TX.NAME " +
        "FROM author_has_title AUT " +
        "INNER JOIN " +
        "(SELECT A.ID_AUTHOR, a.NAME " +
        "FROM author A " +
        "WHERE A.DEATHDATE != 'null' && A.DEATHDATE <=2016) TX " +
        "ON AUT.ID_AUTHOR = TX.ID_AUTHOR) TZ " +
        "ON AWT.ID_TITLE = TZ.ID_TITLE " +
        "GROUP BY TZ.ID_AUTHOR " +
        "ORDER BY nOfAwards DESC LIMIT 0,1 ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.AUTHOR_MOST_AWARDED_AFTER_DEATH, stat);

        // For a given year, output the three publishers that published the most publications


        req = "SELECT PU.NAME, TX.nOfPublications " +
        "FROM publisher PU " +
        "INNER JOIN " +
        "(SELECT P.ID_PUBLISHER, COUNT(*) as nOfPublications " +
        "FROM publication P " +
        "WHERE YEAR(P.DATE) = 2000 " +
        "GROUP BY P.ID_PUBLISHER " +
        "ORDER BY nOfPublications DESC LIMIT 0,3) TX " +
        "ON PU.ID_PUBLISHER = TX.ID_PUBLISHER ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.PUBLICATION_FOR_SAID_YEAR, stat);



        //  Given an author, compute his/her most reviewed title(s)
        req = "SELECT T.TITLE, TXX.nOfReviews " +
        "FROM title T " +
        "INNER JOIN " +
        "(SELECT DISTINCT R.ID_TITLE, " +
                "COUNT(R.ID_TITLE) as nOfReviews " +
        "FROM review R " +
        "INNER JOIN " +
        "(SELECT TP.ID_TITLE, TY.ID_AUTHOR, TY.NAME " +
        "FROM title_has_publication TP " +
        "INNER JOIN " +
        "(SELECT PA.ID_PUBLICATION, PA.ID_AUTHOR, TX.NAME " +
        "FROM publication_author PA " +
        "INNER JOIN " +
        "(SELECT A.ID_AUTHOR, A.NAME " +
        "FROM author A " +
        "WHERE A.ID_AUTHOR = 1591) TX " +
        "ON PA.ID_AUTHOR = TX.ID_AUTHOR) TY " +
        "ON TP.ID_PUBLICATION = TY.ID_PUBLICATION) TZ " +
        "ON R.ID_TITLE= TZ.ID_TITLE " +
        "GROUP BY R.ID_TITLE " +
        "ORDER BY nOfReviews DESC LIMIT 0,1) TXX " +
        "ON T.ID_TITLE = TXX.ID_TITLE ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.MOST_REVIEWED_TITLE_FOR_AUTHOR, stat);


        //  For every language, find the top three title types with most translations.
        req = "SELECT " +
        "T2.TITLE, " +
                "COUNT(T2.ID_TITLE) AS totalTranslation " +
        "FROM title T1 " +
        "INNER JOIN title T2 " +
        "ON T1.ID_ORIGINAL = T2.ID_TITLE " +
        "WHERE T2.ID_LANGUAGE IS NOT NULL " +
        "GROUP BY T2.ID_TITLE " +
        "ORDER BY totalTranslation DESC " +
        "LIMIT 0,3; ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.TOP_3_MOST_TRANSLASTED_TITLES, stat);


                // For each year, compute the average number of authors per publisher

                req = "SELECT " +
        "PSHER.ID_PUBLISHER, " +
                "YEAR(LINKED_DATE_REQ.DATE), " +
                "COUNT(DISTINCT LINKED_PTION.ID_AUTHOR) AS signedAuthor " +
        "FROM publisher PSHER " +
        "INNER JOIN ( " +
                "SELECT DISTINCT P.ID_PUBLISHER, P.DATE FROM publication P " +
        ") LINKED_DATE_REQ " +
        "ON PSHER.ID_PUBLISHER = LINKED_DATE_REQ.ID_PUBLISHER " +
        "INNER JOIN ( " +
                "SELECT DISTINCT TA.ID_AUTHOR, " +
                "PTION.DATE, " +
                "PTION.ID_PUBLISHER " +
        "FROM publication PTION " +
        "INNER JOIN publication_author TA " +
        "ON TA.ID_PUBLICATION = PTION.ID_PUBLICATION " +
        ") LINKED_PTION "+
        "ON PSHER.ID_PUBLISHER = LINKED_PTION.ID_PUBLISHER " +
        "AND YEAR(LINKED_PTION.DATE) " +
                "<= YEAR(LINKED_DATE_REQ.DATE) " +
        "GROUP BY PSHER.ID_PUBLISHER, YEAR(LINKED_DATE_REQ.DATE) ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.TOTAL_AUTHOR_BY_PUBLISHER_BY_YEAR, stat);


                // Publication series with most titles that have been given awards of “World Fantasy Award” type

        req = "SELECT PS.NAME, TYY.nOfAwards " +
        "FROM publication_serie PS " +
        "INNER JOIN " +
        "(SELECT " +
        "P.ID_PUBLICATION_SERIE, " +
                "COUNT(TXX.ID_TITLE) as nOfAwards " +
        "FROM publication P " +
        "INNER JOIN " +
        "(SELECT TP.ID_PUBLICATION, " +
                "TZ.ID_TITLE, " +
                "TZ.TITLE, " +
                "TZ.ID_AWARD, " +
                "TZ.NAME, " +
                "TZ.ID_AWARD_TYPE " +
        "FROM title_has_publication TP " +
        "INNER JOIN " +
        "(SELECT Atitle.ID_TITLE, " +
                "TY.TITLE, "  +
                "Atitle.ID_AWARD, " +
                "TY.NAME, " +
                "TY.ID_AWARD_TYPE " +
        "FROM award_has_title Atitle " +
        "INNER JOIN " +
        "(SELECT AW.ID_AWARD," +
                "AW.TITLE," +
                "TX.NAME,"+
                "AW.ID_AWARD_TYPE"+
        "FROM award AW "+
        "INNER JOIN "+
        "(SELECT Atype.ID_AWARD_TYPE, Atype.NAME "+
        "FROM award_type Atype "+
        "WHERE Atype.name = 'World Fantasy Award') TX "+
        "ON AW.ID_AWARD_TYPE = TX.ID_AWARD_TYPE) TY "+
        "ON Atitle.ID_AWARD = TY.ID_AWARD) TZ "+
        "ON TP.ID_TITLE = TZ.ID_TITLE) TXX "+
        "ON P.ID_PUBLICATION = TXX.ID_PUBLICATION "+
        "WHERE P.ID_PUBLICATION_SERIE != 'null' "+
        "GROUP BY P.ID_PUBLICATION_SERIE "+
        "ORDER BY nOfAwards DESC) TYY "+
        "ON PS.ID_PUBLICATION_SERIE = TYY.ID_PUBLICATION_SERIE "+
        "LIMIT 0,1 ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.AWARDED_WORLD_FANTASY_AWARD, stat);


                // For every award category, list the names of the three most awarded authors

        req = "SELECT Au.NAME, COUNT(*) AS total "  +
        "FROM award_category AC "  +
        "INNER JOIN award Aw "  +
        "ON Aw.ID_AWARD_TYPE = AC.ID_AWARD_TYPE "  +
        "INNER JOIN award_has_title AHT "  +
        "ON AHT.ID_AWARD = Aw.ID_AWARD "  +
        "INNER JOIN title T "  +
        "ON T.ID_TITLE = AHT.ID_TITLE "  +
        "INNER JOIN title_has_publication THP "  +
        "ON THP.ID_TITLE = T.ID_TITLE "  +
        "INNER JOIN publication P "  +
        "ON P.ID_PUBLICATION = THP.ID_PUBLICATION "  +
        "INNER JOIN publication_author PA "  +
        "ON PA.ID_PUBLICATION = P.ID_PUBLICATION "  +
        "INNER JOIN author Au "  +
        "ON Au.ID_AUTHOR = PA.ID_AUTHOR "  +
        "GROUP BY Au.NAME "  +
        "ORDER BY total "  +
        "LIMIT 0,3 ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.MOST_AWARDED_AUTHORS, stat);

                //  Names of all living authors that have published at least one anthology from youngest to oldest
        req = "SELECT A.NAME, A.BIRTHDATE, A.DEATHDATE "  +
        "FROM author A "  +
        "INNER JOIN "  +
        "(SELECT P.ID_PUBLICATION, PA.ID_AUTHOR, P.TYPE "  +
        "FROM publication P "  +
        "INNER JOIN "  +
        "(SELECT * "  +
                "FROM publication_author) PA "  +
        "ON P.ID_PUBLICATION = PA.ID_PUBLICATION "  +
        "WHERE P.TYPE = 1) TX "  +
        "ON A.ID_AUTHOR = TX.ID_AUTHOR "  +
        "ORDER BY A.BIRTHDATE DESC "  +
        "WHERE A.DEATHDATE IS NOT NULL ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.LIVING_AUTHORS_ANTHOLOGY, stat);


                //  Average number of publications per publication series

                req = "SELECT "  +
        "AVG(TX.nOfPublicationsPerSeries) as averageNofPubliPerSeries "  +
        "FROM publication_serie PS "  +
        "INNER JOIN "  +
        "(SELECT P.ID_PUBLICATION_SERIE, "  +
                "COUNT(P.ID_PUBLICATION_SERIE) as nOfPublicationsPerSeries "  +
        "FROM publication P "  +
        "WHERE P.ID_PUBLICATION_SERIE IS NOT NULL "  +
        "GROUP BY P.ID_PUBLICATION_SERIE) TX "  +
        "ON PS.ID_PUBLICATION_SERIE = TX.ID_PUBLICATION_SERIE ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.AVERAGE_NUMBER_PUBLICATION_SERIE, stat);


                // For every language, list the three authors with the most translated titles of “novel” type

                req = "SELECT "+
        "A.NAME, "+
                "COUNT(DISTINCT TITLE.ID_TITLE) AS total "+
        "FROM title TRANSLATED_TITLE "+
        "INNER JOIN title TITLE ON TITLE.ID_TITLE = TRANSLATED_TITLE.ID_ORIGINAL "+
        "INNER JOIN title_has_publication THP ON THP.ID_TITLE = TITLE.ID_TITLE "+
        "INNER JOIN publication PTION ON PTION.ID_PUBLICATION = THP.ID_PUBLICATION "+
        "INNER JOIN publication_author PA ON PA.ID_PUBLICATION = PTION.ID_PUBLICATION "+
        "INNER JOIN author A ON A.ID_AUTHOR = PA.ID_AUTHOR "+
        "INNER JOIN language L ON L.ID_LANGUAGE = TITLE.ID_LANGUAGE "+
        "WHERE TITLE.TYPE = 8 "+
        "GROUP BY A.NAME "+
        "ORDER BY total DESC "+
        "LIMIT 0,3; ";

        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.THREE_AUTHORS_MOST_TRANSLATED_IN_NOVEL, stat);




                // Order the top ten authors whose publications have the largest pages per dollar ratio

        req = "SELECT A.NAME, SUM(P.PRICE)/SUM(P.PAGES) AS ratio "+
        "FROM author A "+
        "INNER JOIN publication_author PA "+
        "ON PA.ID_AUTHOR = A.ID_AUTHOR "+
        "INNER JOIN publication P "+
        "ON P.ID_PUBLICATION = PA.ID_PUBLICATION "+
        "WHERE P.CURRENCY = '$' "+
        "GROUP BY A.ID_AUTHOR "+
        "ORDER BY ratio DESC "+
        "LIMIT 0,10 ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.TEN_AUTHORS_PAGES_DOLLAR_RATIO, stat);




                // For publications that have been awarded the Nebula award, find the top 10 with the most extensive web presence

        req = "SELECT T.TITLE, COUNT(W.ID_WEBPAGE) as presence FROM award_type AwT "+
        "INNER JOIN award A "+
        "ON A.ID_AWARD_TYPE = AwT.ID_AWARD_TYPE "+
        "INNER JOIN award_has_title AHT "+
        "ON AHT.ID_AWARD = A.ID_AWARD "+
        "INNER JOIN title T ON T.ID_TITLE = AHT.ID_TITLE "+
        "INNER JOIN Webpage W ON W.ID_TITLE = T.ID_TITLE "+
        "WHERE AwT.name = 'Nebula Award' "+
        "GROUP BY T.TITLE "+
        "ORDER BY presence DESC ";


        stat = conn.prepareStatement(req);
        requests.put(AvailableRequest.NEBULA_MOST_EXTENSIVE_WEB, stat);











    }


	public PreparedStatement getStatement(AvailableRequest req)
			throws SQLException{

        return requests.get(req);
	}


	
}

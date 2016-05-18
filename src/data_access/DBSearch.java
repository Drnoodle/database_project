package data_access;

import library.Author;
import library.Award;
import library.Publisher;
import library.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by noodle on 17.05.16.
 */
public class DBSearch {


    private PreparedStatement searchAuthors;
    private PreparedStatement searchTitles;
    private PreparedStatement searchAwards;
    private PreparedStatement searchPublisher;

    public DBSearch(Connection conn) throws SQLException {

        String req = new StringBuilder()
                .append("SELECT A.*, L.NAME AS LANGUAGE, N.NOTE FROM author A ")
                .append("LEFT JOIN language L ON A.ID_LANGUAGE = L.ID_LANGUAGE ")
                .append("LEFT JOIN note N ON  A.ID_NOTE = N.ID_NOTE ")
                .append("WHERE A.NAME LIKE ? OR A.LEGAL_NAME LIKE ? OR A.PSEUDO LIKE ? ")
                .append("LIMIT 0,50; ")
                .toString();

        searchAuthors = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT T.*, N.NOTE, S.NOTE AS SYNOPSIS FROM title T ")
                .append("LEFT JOIN NOTE N ON T.ID_NOTE = N.ID_NOTE ")
                .append("LEFT JOIN NOTE S ON T.ID_SYNOPSIS = S.ID_NOTE ")
                .append("WHERE T.TITLE LIKE ? ")
                .append("LIMIT 0,50; ")
                .toString();

        searchTitles = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("SELECT A.*, Atype.NAME, Atype.BY_NAME, Atype.FOR_NAME, N.NOTE ")
                .append("FROM AWARD A ")
                .append("LEFT JOIN award_type AType ON AType.ID_AWARD_TYPE = A.ID_AWARD_TYPE ")
                .append("LEFT JOIN note N ON N.ID_NOTE = Atype.ID_NOTE ")
                .append("WHERE A.Title LIKE ? OR Atype.NAME LIKE ? OR Atype.FOR_NAME LIKE ? ")
                .append("LIMIT 0,50; ")
                .toString();

        searchAwards = conn.prepareStatement(req);

        req = new StringBuilder()
                .append("SELECT P.*, N.NOTE FROM publisher P ")
                .append("LEFT JOIN note N ON N.ID_NOTE = P.ID_NOTE ")
                .append("WHERE P.NAME LIKE ? ")
                .append("LIMIT 0,50; ")
                .toString();

        searchPublisher = conn.prepareStatement(req);

    }



    public List<Author> getAuthors(String search) throws SQLException {

        searchAuthors.clearParameters();
        searchAuthors.setString(1, "%" +search + "%");
        searchAuthors.setString(2, "%" +search + "%");
        searchAuthors.setString(3, "%" +search + "%");

        ResultSet res = searchAuthors.executeQuery();
        List<Author> authors = new ArrayList<>();
        while (res.next()) {

            Author author = new Author(
                    res.getInt("ID_AUTHOR"),
                    res.getString("NAME"),
                    res.getString("LEGAL_NAME"),
                    res.getString("PSEUDO"),
                    res.getString("BIRTHPLACE"),
                    res.getString("BIRTHDATE"),
                    res.getString("DEATHDATE"),
                    res.getString("EMAIL"),
                    res.getString("IMG_LINK"),
                    res.getInt("ID_LANGUAGE"),
                    res.getInt("ID_NOTE"),
                    res.getString("LANGUAGE"),
                    res.getString("NOTE")
            );

            authors.add(author);
        }

        return authors;
    }



    public List<Title> getTitles(String search) throws SQLException {


        searchTitles.clearParameters();
        searchTitles.setString(1, "%" +search + "%");

        ResultSet res = searchTitles.executeQuery();

        List<Title> titles = new ArrayList<>();

        while(res.next()){

            Title title = new Title(
                    res.getInt("ID_TITLE"),
                    res.getString("TITLE"),
                    res.getInt("ID_TITLE"),
                    res.getInt("ID_NOTE"),
                    res.getInt("ID_SYNOPSIS"),
                    res.getString("TITLE"),
                    res.getInt("SERIE_NUMBER"),
                    res.getInt("ID_ORIGINAL"),
                    res.getString("TRANSLATOR_NAME"),
                    res.getString("NOTE"),
                    res.getString("SYNOPSIS")
            );

            titles.add(title);

        }

        return titles;
    }



    public List<Award> getAwards(String search) throws SQLException {


        searchAwards.clearParameters();
        searchAwards.setString(1, "%" +search + "%");
        searchAwards.setString(2, "%" +search + "%");
        searchAwards.setString(3, "%" +search + "%");

        ResultSet res = searchAwards.executeQuery();

        List<Award> awards = new ArrayList<>();

        while(res.next()){

            Award award = new Award(
                    res.getInt("ID_AWARD"),
                    res.getString("TITLE"),
                    res.getString("DATE"),
                    res.getInt("ID_AWARD_TYPE"),
                    res.getString("NAME"),
                    res.getString("BY_NAME"),
                    res.getString("FOR_NAME"),
                    res.getString("NOTE")
                    );

            awards.add(award);

        }

        return awards;
    }


    public List<Publisher> getPublishers(String search) throws SQLException {

        searchPublisher.clearParameters();
        searchPublisher.setString(1,"%" +search + "%");
        ResultSet res = searchPublisher.executeQuery();

        List<Publisher> publishers = new ArrayList<>();


        while(res.next()){

            Publisher p = new Publisher(
                     res.getInt("ID_PUBLISHER"),
                    res.getString("NAME"),
                    res.getInt("ID_NOTE"),
                    res.getString("NOTE")
            );

            publishers.add(p);
        }

        return publishers;
    }


}

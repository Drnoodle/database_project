package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by noodle on 20.05.16.
 */
public class DBInsert {




    private PreparedStatement insert;
    private PreparedStatement maxNoteId;
    private PreparedStatement maxAuthorId;
    private PreparedStatement insertNote;

    public DBInsert(Connection conn) throws SQLException {


        String req = new StringBuilder()
                .append("INSERT INTO author (")
                .append("ID_AUTHOR, NAME, LEGAL_NAME,LAST_NAME,PSEUDO, BIRTHPLACE,")
                .append("BIRTHDATE,DEATHDATE,EMAIL,IMG_LINK,ID_LANGUAGE,ID_NOTE")
                .append(") VALUES ")
                .append("(?,?,?,?,?,?,?,?,?,?,?,?);")
                .toString();


        insert = conn.prepareStatement(req);

        req = new StringBuilder()
                .append("SELECT MAX(ID_NOTE) FROM note;")
                .toString();

        maxNoteId = conn.prepareStatement(req);

        req = new StringBuilder()
                .append("SELECT MAX(ID_AUTHOR) FROM author;")
                .toString();

        maxAuthorId = conn.prepareStatement(req);


        req = new StringBuilder()
                .append("INSERT INTO note (ID_NOTE, NOTE) VALUES (?,?);")
                .toString();

        insertNote = conn.prepareStatement(req);

    }


    public void insertAuthor(
            String name,
            String legalName,
            String lastName,
            String pseudo,
            String birthplace,
            String birthdate,
            String deathdate,
            String email,
            String imgLink,
            String note
    ) throws SQLException {

        insert.clearParameters();
        insertNote.clearParameters();

        Integer nextAvailableNoteId=null;
        Integer nextAvailableAuthorId=null;

        if(note.length() != 0) {

            ResultSet res =maxNoteId.executeQuery();
            res.next();

            nextAvailableNoteId = res.getInt(1) + 1;
            insertNote.setInt(1, nextAvailableNoteId);
            insertNote.setString(2,note);
            insertNote.executeUpdate();
        }


        ResultSet res =maxAuthorId.executeQuery();
        res.next();
        nextAvailableAuthorId = res.getInt(1)+1;

        insert.setInt(1,nextAvailableAuthorId);
        insert.setString(2,name);
        insert.setString(3,legalName);
        insert.setString(4,lastName);
        insert.setString(5,pseudo);
        insert.setString(6,birthplace);
        insert.setString(7,birthdate);
        insert.setString(8,deathdate);
        insert.setString(9,email);
        insert.setString(10,imgLink);
        insert.setString(11,null);
        insert.setInt(12, nextAvailableNoteId);
        insert.executeUpdate();

    }



}

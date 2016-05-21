package data_access;

import library.Author;
import library.Award;
import library.Publisher;
import library.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by noodle on 20.05.16.
 */
public class DBInsert {




    private PreparedStatement maxAuthorId;
    private PreparedStatement insertAuthor;
    private PreparedStatement maxNoteId;
    private PreparedStatement insertNote;

    private PreparedStatement updateNoteAuthor;
    private PreparedStatement updateNotePublisher;
    private PreparedStatement updateNoteTitle;
    private PreparedStatement updateNoteAward;
    private PreparedStatement updateNote;

    public DBInsert(Connection conn) throws SQLException {


        String req = new StringBuilder()
                .append("INSERT INTO author (")
                .append("ID_AUTHOR, NAME, LEGAL_NAME,LAST_NAME,PSEUDO, BIRTHPLACE,")
                .append("BIRTHDATE,DEATHDATE,EMAIL,IMG_LINK,ID_LANGUAGE,ID_NOTE")
                .append(") VALUES ")
                .append("(?,?,?,?,?,?,?,?,?,?,?,?);")
                .toString();


        insertAuthor = conn.prepareStatement(req);

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

        updateNoteAuthor = conn.prepareStatement("UPDATE author SET ID_NOTE = ? WHERE ID_AUTHOR = ?");
        updateNotePublisher = conn.prepareStatement("UPDATE publisher SET ID_NOTE = ? WHERE ID_PUBLISHER = ?");
        updateNoteTitle = conn.prepareStatement("UPDATE title SET ID_NOTE = ? WHERE ID_TITLE = ?");
        updateNoteAward = conn.prepareStatement("UPDATE award SET ID_NOTE = ? WHERE ID_AWARD = ?");

        updateNote = conn.prepareStatement("UPDATE note SET NOTE = ? WHERE ID_NOTE = ?");

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

        insertAuthor.clearParameters();
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

        insertAuthor.setInt(1,nextAvailableAuthorId);
        insertAuthor.setString(2,name);
        insertAuthor.setString(3,legalName);
        insertAuthor.setString(4,lastName);
        insertAuthor.setString(5,pseudo);
        insertAuthor.setString(6,birthplace);
        insertAuthor.setString(7,birthdate);
        insertAuthor.setString(8,deathdate);
        insertAuthor.setString(9,email);
        insertAuthor.setString(10,imgLink);
        insertAuthor.setString(11,null);
        insertAuthor.setInt(12, nextAvailableNoteId);
        insertAuthor.executeUpdate();

    }


    private void updateNote(
            PreparedStatement updateNoteId,
            String note,
            Integer idEntity,
            Integer idNote
            ) throws SQLException {


        if(idNote == null){

            ResultSet res = maxNoteId.executeQuery();
            res.next();
            Integer noteId = res.getInt(1)+1;
            insertNote.clearParameters();
            insertNote.setInt(1, noteId);
            insertNote.setString(2, note);
            insertNote.executeUpdate();
            updateNoteId.clearParameters();
            updateNoteId.setInt(1,noteId);
            updateNoteId.setInt(2, idEntity);
            updateNoteId.executeUpdate();

        }
        else {
            updateNote.clearParameters();
            updateNote.setString(1, note);
            updateNote.setInt(2, idNote);
            updateNote.executeUpdate();
        }

    }


    public void updateNote(Author author, String note) throws SQLException {

        updateNote(updateNoteAuthor,note,author.id_author,author.id_note);

    }

    public void updateNote(Publisher publisher, String note) throws SQLException {

        updateNote(updateNotePublisher,note,publisher.idPublisher, publisher.idNote);

    }

    public void updateNote(Title title, String note) throws SQLException {

        updateNote(updateNoteTitle,note,title.idTitle,title.idNote);

    }

    public void updateNote(Award award, String note) throws SQLException {

        updateNote(updateNoteAward,note,award.idAward,award.idNote);

    }





}

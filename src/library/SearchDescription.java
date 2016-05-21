package library;

import data_access.DBInsert;

import java.sql.SQLException;

/**
 * Created by noodle on 17.05.16.
 */
public interface SearchDescription {


    public String searchEntryTitle();
    public String searchEntryDescription();
    public void displayContent();
    public void updateNote(String note, DBInsert insert) throws SQLException;

}

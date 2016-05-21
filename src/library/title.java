package library;

import controler.ResultControler;
import data_access.DBInsert;

import java.sql.SQLException;

/**
 * Created by noodle on 17.05.16.
 */
public class Title implements SearchDescription {


    public Integer idTitle;
    public String title;
    public Integer storyLength;
    public Integer idNote;
    public Integer idSynopsis;
    public String type;
    public Integer serieNumber;
    public Integer idOriginal;
    public String translatorName;
    public String note;
    public String synopsis;


    public Title(
            Integer idtitle,
            String title,
            Integer storyLength,
            Integer idNote,
            Integer idSynopsis,
            String type,
            Integer serieNumber,
            Integer idOriginal,
            String translatorName,
            String note,
            String synopsis){

        this.idTitle = idtitle;
        this.title = title;
        this.storyLength = storyLength;
        this.idNote = idNote;
        this.idSynopsis = idSynopsis;
        this.type = type;
        this.serieNumber = serieNumber;
        this.idOriginal = idOriginal;
        this.translatorName = translatorName;
        this.note = note;
        this.synopsis = synopsis;

    }


    @Override
    public String searchEntryTitle() {
        return this.title;
    }

    @Override
    public String searchEntryDescription() {
        return this.type;
    }

    @Override
    public void displayContent(){
        ResultControler.getInstance().displayTitle(this);
    }

    @Override
    public void updateNote(String note, DBInsert insert) throws SQLException {
        insert.updateNote(this,note);
        this.note = note;
    }

}

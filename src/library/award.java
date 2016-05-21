package library;

import controler.ResultControler;
import data_access.DBInsert;

import java.sql.SQLException;

/**
 * Created by noodle on 17.05.16.
 */
public class Award implements SearchDescription {


    public Integer idAward;
    public String title;
    public String date;
    public Integer id_award_type;
    public String name;
    public String byName;
    public String forName;
    public String note;
    public Integer idNote;


    public Award(
            Integer idAward,
            String title,
            String date,
            Integer id_award_type,
            String name,
            String byName,
            String forName,
            String note,
            Integer idNote
    ){

        this.idAward = idAward;
        this.title = title;
        this.date = date;
        this.id_award_type = id_award_type;
        this.name = name;
        this.byName = byName;
        this.forName = forName;
        this.note = note;
        this.idNote = idNote;

    }


    @Override
    public String searchEntryTitle() {
        return this.title;
    }

    @Override
    public String searchEntryDescription() {
        StringBuilder sB = new StringBuilder();
        sB.append("rewarded by : ");
        if(this.byName != null){
            sB.append(this.byName);
        }
        else {
            sB.append("unknow");
        }
        sB.append("\n");
        sB.append("rewarded for : ");
        if(this.forName != null){
            sB.append(this.forName);
        }
        else {
            sB.append("unknown");
        }


        return sB.toString();
    }

    @Override
    public void displayContent(){
        ResultControler.getInstance().displayAward(this);
    }

    @Override
    public void updateNote(String note, DBInsert insert) throws SQLException {
        insert.updateNote(this,note);
        this.note = note;
    }


}

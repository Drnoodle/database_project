package library;

import controler.ContentControler;

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


    public Award(
            Integer idAward,
            String title,
            String date,
            Integer id_award_type,
            String name,
            String byName,
            String forName,
            String note
    ){

        this.idAward = idAward;
        this.title = title;
        this.date = date;
        this.id_award_type = id_award_type;
        this.name = name;
        this.byName = byName;
        this.forName = forName;
        this.note = note;

    }


    @Override
    public String searchEntryTitle() {
        return this.title;
    }

    @Override
    public String searchEntryDescription() {
        return "rewarded by "+this.name;
    }

    @Override
    public void displayContent(){
        ContentControler.getInstance().displayAward(this);
    }

}

package library;

import controler.ContentControler;

/**
 * Created by noodle on 17.05.16.
 */
public class Publisher implements SearchDescription {

    public Integer idPublisher;
    public String name;
    public Integer idNote;
    public String note;

    public Publisher(
            Integer idPublisher,
            String name,
            Integer idNote,
            String note
    ){
        this.idPublisher = idPublisher;
        this.name = name;
        this.idNote = idNote;
        this.note = note;
    }


    @Override
    public String searchEntryTitle() {
        return this.name;
    }

    @Override
    public String searchEntryDescription() {
        return this.note;
    }

    @Override
    public void displayContent(){
        ContentControler.getInstance().displayPublisher(this);
    }

}

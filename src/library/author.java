package library;

import controler.ContentControler;
import controler.ContentPaneControler;

/**
 * Created by noodle on 17.05.16.
 */
public class Author implements SearchDescription {


    public int id_author;
    public String name;
    public String legalName;
    public String pseudo;
    public String birthplace;
    public String birthdate;
    public String deathDate;
    public String email;
    public String img_link;
    public int id_language;
    public int id_note;
    public String language;
    public String note;



    public Author(
            int idAuthor,
            String name,
            String legalName,
            String pseudo,
            String birthplace,
            String birthdate,
            String deathDate,
            String email,
            String img_link,
            int id_language,
            int id_note,
            String language,
            String note
    ){

        this.id_author  = id_author ;
        this.id_language = id_language;
        this.id_note = id_note;
        this.name = name;
        this.legalName = legalName;
        this.pseudo = pseudo;
        this.birthplace = birthplace;
        this.birthdate = birthdate;
        this.deathDate = deathDate;
        this.email = email;
        this.img_link = img_link;
        this.language = language;
        this.note = note;

    }


    @Override
    public String searchEntryTitle() {
        return this.name;
    }

    @Override
    public String searchEntryDescription() {
        return "born in "+this.birthplace+" the " + this.birthdate;
    }

    @Override
    public void displayContent(){
        ContentControler.getInstance().displayAuthor(this);
    }

}

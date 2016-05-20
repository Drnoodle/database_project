package library;

import controler.ResultControler;

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

       StringBuilder sB = new StringBuilder();

        sB.append("legal name : ");
        if(this.legalName != null){
            sB.append(this.legalName);
        }
        else {
            sB.append("unknown");
        }

        sB.append("\n");
        sB.append("pseudo : ");
        if(this.birthplace != null){
            sB.append(this.pseudo);
        }
        else {
            sB.append("unknown");
        }
        sB.append("\n");
        sB.append("language : ");
        if(this.language != null){
            sB.append(this.language);
        }
        else {
            sB.append("unknown");
        }
        sB.append("\n");

        sB.append("email : ");
        if(this.email != null){
            sB.append(this.email);
        }
        else {
            sB.append("unknown");
        }
        sB.append("\n");

        return sB.toString();
    }

    @Override
    public void displayContent(){
        ResultControler.getInstance().displayAuthor(this);
    }

}

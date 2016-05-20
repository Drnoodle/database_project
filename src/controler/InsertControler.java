package controler;

import data_access.DBConnection;
import data_access.DBInsert;
import data_access.DBSearch;
import library.Author;
import library.SearchDescription;
import view.BodyInsert;

import javax.naming.directory.SearchResult;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by noodle on 19.05.16.
 */
public class InsertControler implements Controler {



    private static InsertControler instance = new InsertControler();

    private BodyInsert bodyInsert = new BodyInsert();

    private DBInsert dbInsert;
    private DBSearch dbSearch;

    private InsertControler(){

        if(instance !=  null){
            throw new IllegalAccessError();
        }

        try {
            dbInsert = new DBInsert(DBConnection.getConnection());
            dbSearch = new DBSearch(DBConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bodyInsert.newInsertCallback()
                .addCallback(new Runnable(){
                    @Override
                    public void run() {

                        try {

                            dbInsert.insertAuthor(
                                    bodyInsert.getNameText(),
                                    bodyInsert.getLegalNameText(),
                                    bodyInsert.getLastNameText(),
                                    bodyInsert.getPseudoText(),
                                    bodyInsert.getBirthplaceText(),
                                    bodyInsert.getBirthdateText(),
                                    bodyInsert.getDeathdateText(),
                                    bodyInsert.getEmailText(),
                                    bodyInsert.getImgLinkText(),
                                    bodyInsert.getNoteText()
                            );

                            List<Author> searchNewAdded =
                                    dbSearch.getAuthors(bodyInsert.getNameText());

                            SearchResultControler
                                    .getInstance()
                                    .display(searchNewAdded);

                            System.out.print("a");
                            ContentPaneControler
                                    .getInstance()
                                    .display(SearchResultControler.getInstance());
                            System.out.print("b");

                        } catch (SQLException e) {
                            e.printStackTrace();

                        }

                    }
                });


    }



    public static InsertControler getInstance(){
        return instance;
    }



    @Override
    public void active() {

    }

    @Override
    public void inactive() {

    }

    @Override
    public JComponent getComponent() {
        return bodyInsert;
    }



}

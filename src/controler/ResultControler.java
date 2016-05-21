package controler;

import data_access.DBConnection;
import data_access.DBInsert;
import library.Author;
import library.Award;
import library.Publisher;
import library.Title;
import view.BodyResult;
import view.ContentPane;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by noodle on 17.05.16.
 */
public class ResultControler implements Controler {


    private static ResultControler instance = new ResultControler();

    private DBInsert insert;

    private BodyResult bodyContent;

    private ResultControler(){

        if(instance != null){
            throw new IllegalAccessError();
        }

        try {
            insert = new DBInsert(DBConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bodyContent = new BodyResult();

        bodyContent.closeButtonCallback()
                .addCallback(new Runnable(){
                    @Override
                    public void run() {
                        ContentPaneControler.getInstance().display(SearchResultControler.getInstance());
                    }
                });

        bodyContent.updateButtonCallback().addCallback(new Runnable(){
            @Override
            public void run() {
                if(bodyContent.getDescription() != null){
                    try {
                        bodyContent.getDescription()
                                .updateNote(bodyContent.getNote(),insert);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    public static ResultControler getInstance(){
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
        return bodyContent;
    }


    public void displayAuthor(Author a){
        bodyContent.setAuthor(a);
    }

    public void displayAward(Award a){
        bodyContent.setAward(a);
    }

    public void displayTitle(Title t){
        bodyContent.setTitle(t);
    }

    public void displayPublisher(Publisher p){
        bodyContent.setPublisher(p);
    }

    public void displayArray(String[][] data){
        bodyContent.setData(data);
    }


}

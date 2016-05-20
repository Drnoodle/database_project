package controler;

import library.Author;
import library.Award;
import library.Publisher;
import library.Title;
import view.BodyResult;

import javax.swing.*;

/**
 * Created by noodle on 17.05.16.
 */
public class ResultControler implements Controler {


    private static ResultControler instance = new ResultControler();

    private BodyResult bodyContent;

    private ResultControler(){

        if(instance != null){
            throw new IllegalAccessError();
        }

        bodyContent = new BodyResult();

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

package controler;

import library.Author;
import library.Award;
import library.Publisher;
import library.Title;
import view.BodyContent;

import javax.swing.*;

/**
 * Created by noodle on 17.05.16.
 */
public class ContentControler implements Controler {


    private static ContentControler instance = new ContentControler();

    private BodyContent bodyContent;

    private ContentControler(){

        if(instance != null){
            throw new IllegalAccessError();
        }

        bodyContent = new BodyContent();


        bodyContent.closeCallback().addCallback(new Runnable(){
            @Override
            public void run() {
                ContentPaneControler
                        .getInstance()
                        .display(SearchResultControler.getInstance());
            }
        });

    }


    public static ContentControler getInstance(){
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




}

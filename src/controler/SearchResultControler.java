package controler;

import library.SearchDescription;
import view.BodySearchResult;
import view.ContentPane;

import javax.naming.directory.SearchResult;
import javax.swing.*;
import java.util.List;

/**
 * Created by noodle on 17.05.16.
 */
public class SearchResultControler implements Controler {

    private static SearchResultControler instance = new SearchResultControler();

    private BodySearchResult searchResult;


    private SearchResultControler(){

        if(instance != null){
            throw new IllegalAccessError();
        }

        searchResult = new BodySearchResult();

    }


    public static SearchResultControler getInstance(){
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
        return searchResult;
    }



    public void display(List<? extends SearchDescription> results){
        this.searchResult.setResults(results);
    }


}

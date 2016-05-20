package controler;

import view.BodyAccueil;

import javax.swing.*;

/**
 * Created by noodle on 20.05.16.
 */
public class AccueilControler implements Controler {

    private static AccueilControler instance = new AccueilControler();


    private BodyAccueil bodyAccueil = new BodyAccueil();

    private AccueilControler(){

        if(instance != null){
            throw new IllegalAccessError();
        }


    }


    public static AccueilControler getInstance(){
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
        return bodyAccueil;
    }
}

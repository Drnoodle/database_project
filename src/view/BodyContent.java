package view;

import library.Author;
import library.Award;
import library.Publisher;
import library.Title;
import utils.BackgroundPanel;
import utils.Callback;
import utils.LocalFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by noodle on 17.05.16.
 */
public class BodyContent extends BackgroundPanel {


    private utils.Button close;
    private JPanel content;


    public BodyContent() {

        super("images/background.png");

        this.setLayout(new BorderLayout());

        close = new utils.Button("x");

        content = new JPanel(new BorderLayout());
        content.setOpaque(false);

        JPanel topCloseContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topCloseContainer.add(close);

        this.add(topCloseContainer, BorderLayout.NORTH);
        this.add(content);

    }


    public Callback closeCallback(){
        return close.clickedCallback();
    }


    public void setTitle(Title t){

        this.content.removeAll();

        JPanel content = new JPanel();
        content.setOpaque(false);

        BoxLayout box = new BoxLayout(content, BoxLayout.PAGE_AXIS);
        content.setLayout(box);

        JPanel bodyTitle = new JPanel();
        bodyTitle.setOpaque(false);
        JLabel titleLab = new JLabel(t.title);
        titleLab.setFont(utils.LocalFont.getFont(LocalFont.BREE, 22));
        titleLab.setForeground(new Color(200,200,200));
        titleLab.setBorder(new EmptyBorder(15,0,40,0));
        bodyTitle.add(titleLab);

        utils.Title synopsisTitle = new utils.Title("synopsis");
        JLabel synopsisLab = new JLabel(t.synopsis);
        synopsisLab.setBorder(new EmptyBorder(0,0,30,0));
        content.add(synopsisTitle);
        content.add(synopsisLab);

        utils.Title generalTitle = new utils.Title("general");
        JLabel serieNumberLab = new JLabel("serie number : "+t.serieNumber);
        JLabel storyLengthLab = new JLabel("storyLength : "+t.storyLength);
        JLabel translatorLab = new JLabel("translator name : "+t.translatorName);
        JLabel typeLab = new JLabel("type : "+t.type);
        typeLab.setBorder(new EmptyBorder(0,0,30,0));
        content.add(generalTitle);
        content.add(serieNumberLab);
        content.add(storyLengthLab);
        content.add(translatorLab);
        content.add(typeLab);

        utils.Title noteTitle = new utils.Title("note");
        JLabel noteLab = new JLabel(t.note);
        content.add(noteTitle);
        content.add(noteLab);

        JPanel container = new JPanel();
        container.add(content);
        this.content.add(bodyTitle, BorderLayout.NORTH);
        this.content.add(container, BorderLayout.WEST);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });


    }




    public void setAuthor(Author author){

        this.content.removeAll();

        JPanel content = new JPanel();
        content.setOpaque(false);

        BoxLayout box = new BoxLayout(content, BoxLayout.PAGE_AXIS);
        content.setLayout(box);


        JPanel bodyTitle = new JPanel();
        bodyTitle.setOpaque(false);
        JLabel nameLab = new JLabel(author.name);
        nameLab.setFont(utils.LocalFont.getFont(LocalFont.BREE, 22));
        nameLab.setForeground(new Color(200,200,200));
        nameLab.setBorder(new EmptyBorder(15,0,40,0));
        bodyTitle.add(nameLab);
        // content.add(bodyTitle);

        utils.Title penNameTitle = new utils.Title("general");
        JLabel legalNameLab = new JLabel("legal name : " + author.legalName);
        JLabel pseudoLab = new JLabel("pseudo : " + author.pseudo);
        JLabel emailLab = new JLabel("email : " + author.email);
        JLabel languageLab = new JLabel("language : " + author.language);
        languageLab.setBorder(new EmptyBorder(0,0,30,0));
        content.add(penNameTitle);
        content.add(legalNameLab);
        content.add(pseudoLab);
        content.add(emailLab);
        content.add(languageLab);


        utils.Title bornTitle = new utils.Title("born");
        JLabel birthDateLab = new JLabel("birth date : " + author.birthdate);
        JLabel birthPlaceLab = new JLabel("birth place : " + author.birthplace);
        JLabel deathDateLab = new JLabel("death date : " + author.deathDate);
        content.add(bornTitle);
        content.add(birthDateLab);
        content.add(birthPlaceLab);
        content.add(deathDateLab);
        deathDateLab.setBorder(new EmptyBorder(0,0,30,0));


        utils.Title noteTitle = new utils.Title("note");
        JLabel noteLab = new JLabel(author.note);
        content.add(noteTitle);
        content.add(noteLab);

        JPanel container = new JPanel();
        container.add(content);
        this.content.add(bodyTitle, BorderLayout.NORTH);
        this.content.add(container, BorderLayout.WEST);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });


    }



    public void setPublisher(Publisher p){

        this.content.removeAll();

        JPanel content = new JPanel();
        content.setOpaque(false);

        BoxLayout box = new BoxLayout(content, BoxLayout.PAGE_AXIS);
        content.setLayout(box);

        JPanel bodyTitle = new JPanel();
        bodyTitle.setOpaque(false);
        JLabel nameLab = new JLabel(p.name);
        nameLab.setFont(utils.LocalFont.getFont(LocalFont.BREE, 22));
        nameLab.setForeground(new Color(200,200,200));
        nameLab.setBorder(new EmptyBorder(15,0,40,0));
        bodyTitle.add(nameLab);

        utils.Title noteTitle = new utils.Title("note");
        JLabel noteLab = new JLabel(p.note);
        content.add(noteTitle);
        content.add(noteLab);

        JPanel container = new JPanel();
        container.add(content);
        this.content.add(bodyTitle, BorderLayout.NORTH);
        this.content.add(container, BorderLayout.WEST);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });

    }



    public void setAward(Award award){

        this.content.removeAll();

        JPanel content = new JPanel();
        content.setOpaque(false);

        BoxLayout box = new BoxLayout(content, BoxLayout.PAGE_AXIS);
        content.setLayout(box);

        JPanel bodyTitle = new JPanel();
        bodyTitle.setOpaque(false);
        JLabel titleLab = new JLabel(award.title);
        titleLab.setFont(utils.LocalFont.getFont(LocalFont.BREE, 22));
        titleLab.setForeground(new Color(200,200,200));
        titleLab.setBorder(new EmptyBorder(15,0,40,0));
        bodyTitle.add(titleLab);

        utils.Title generalTitle = new utils.Title("general");
        JLabel nameLab = new JLabel("name : " + award.name);
        JLabel byNameLab = new JLabel("supplied by : " + award.byName);
        JLabel forNameLab = new JLabel("for : " + award.forName);
        JLabel dateLab = new JLabel("date : " + award.date);
        dateLab.setBorder(new EmptyBorder(0,0,30,0));
        content.add(generalTitle);
        content.add(nameLab);
        content.add(byNameLab);
        content.add(forNameLab);
        content.add(dateLab);

        utils.Title noteTitle = new utils.Title("note");
        JLabel noteLab = new JLabel(award.note);
        content.add(noteTitle);
        content.add(noteLab);

        JPanel container = new JPanel();
        container.add(content);
        this.content.add(bodyTitle, BorderLayout.NORTH);
        this.content.add(container, BorderLayout.WEST);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });


    }



}

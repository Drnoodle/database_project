package view;

import controler.ContentControler;
import controler.ContentPaneControler;
import library.*;
import utils.BackgroundPanel;
import utils.Callback;
import utils.LocalFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by noodle on 17.05.16.
 */
public class BodySearchResult extends BackgroundPanel{


    private utils.Button closeButton;

    private JPanel resultContent;


    public BodySearchResult() {
        super("images/background.png");

        this.setLayout(new BorderLayout());


        this.resultContent = new BackgroundPanel("images/background.png");
        this.resultContent.setLayout(new BorderLayout());
        resultContent.setOpaque(true);
        this.closeButton = new utils.Button("x");

        JPanel closeTopContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closeTopContainer.setOpaque(false);

        closeTopContainer.add(closeButton, BorderLayout.NORTH);
        closeTopContainer.add(closeButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setOpaque(false);
        scrollPane.setViewportView(resultContent);
        this.add(closeTopContainer, BorderLayout.NORTH);
        this.add(scrollPane);

    }



    public void setResults(List<? extends SearchDescription> results){

        this.resultContent.removeAll();

        JPanel container = new JPanel();
        container.setOpaque(false);
        BoxLayout box = new BoxLayout(container,BoxLayout.PAGE_AXIS);
        container.setLayout(box);

        for(SearchDescription sd : results){
            JPanel entry = new ResultEntry(sd);
            container.add(entry);
        }

        this.resultContent.add(container, BorderLayout.NORTH);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });
    }



    private static class ResultEntry extends JPanel{
        public ResultEntry(SearchDescription d){

            utils.Button title = new utils.Button(d.searchEntryTitle());
            title.setForeground(Color.darkGray);
            title.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,14));
            title.setBorder(new EmptyBorder(0,0,0,0));
            JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
            titleContainer.setOpaque(false);
            titleContainer.add(title);
            title.clickedCallback().addCallback(new Runnable(){
                @Override
                public void run() {
                    d.displayContent();
                    ContentPaneControler.getInstance().display(ContentControler.getInstance());
                }
            });

            JLabel desc = new JLabel(d.searchEntryDescription());
            desc.setForeground(Color.LIGHT_GRAY);
            desc.setBorder(new EmptyBorder(0,20,0,0));


            this.setBorder(new EmptyBorder(5,5,5,5));
            this.setOpaque(false);
            this.setLayout(new BorderLayout());
            this.add(titleContainer, BorderLayout.NORTH);
            this.add(desc);
        }
    }


    public Callback closeCallback(){
        return closeButton.clickedCallback();
    }

}

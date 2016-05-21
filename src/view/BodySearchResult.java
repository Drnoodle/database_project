package view;

import com.sun.tools.javac.comp.Flow;
import controler.ResultControler;
import controler.ContentPaneControler;
import library.*;
import utils.BackgroundPanel;
import utils.LocalFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by noodle on 17.05.16.
 */
public class BodySearchResult extends BackgroundPanel{


    private JPanel resultContent;


    public BodySearchResult() {

        super("images/background_body.png");

        this.setLayout(new BorderLayout());


        this.resultContent = new JPanel();
        this.resultContent.setBorder(new EmptyBorder(0,20,0,20));
        this.resultContent.setOpaque(false);
        this.resultContent.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setOpaque(false);
        scrollPane.setViewportView(resultContent);
        scrollPane.getViewport().setOpaque(false);
        this.add(scrollPane);

    }



    public void setResults(List<? extends SearchDescription> results){

        this.resultContent.removeAll();

        JPanel container = new JPanel();
        container.setOpaque(false);

        int totalRows = (int)Math.ceil(((double)results.size())/2);

        container.setLayout(new GridLayout(totalRows,2));

        for(SearchDescription sd : results){
            container.add(new ResultEntry(sd));
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


            utils.Button title =
                    new utils.Button(
                            d.searchEntryTitle(),
                            new Color(220,150,70),
                            LocalFont.getFont(LocalFont.FRANCOISONE,14)
            );

            JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));

            titleContainer.setBackground(new Color(0,0,0,10));
            titleContainer.add(title);
            title.clickedCallback().addCallback(new Runnable(){
                @Override
                public void run() {
                    d.displayContent();
                    ContentPaneControler.getInstance().display(ResultControler.getInstance());
                }
            });


            String[] descLines = d.searchEntryDescription().split("\n");

            JPanel descPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
            descPan.setBackground(new Color(0,0,0,10));

            BoxLayout box = new BoxLayout(descPan, BoxLayout.PAGE_AXIS);
            descPan.setLayout(box);

            for(int i = 0; i<descLines.length; i++){
                JLabel lab = new JLabel(descLines[i]);
                lab.setFont(LocalFont.getFont(LocalFont.LATO,12));
                lab.setForeground(Color.DARK_GRAY);
                lab.setBorder(new EmptyBorder(0,20,0,0));
                descPan.add(lab);
            }

            JLabel emptyLine = new JLabel();
            emptyLine.setBorder(new EmptyBorder(0,0,10,0));
            descPan.add(emptyLine);

            this.setOpaque(false);
            this.setBorder(new EmptyBorder(15,5,5,5));
            this.setLayout(new BorderLayout());


            this.add(titleContainer, BorderLayout.NORTH);
            this.add(descPan);

        }
    }


}

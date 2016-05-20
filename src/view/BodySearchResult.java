package view;

import controler.ResultControler;
import controler.ContentPaneControler;
import library.*;
import utils.BackgroundPanel;
import utils.LocalFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
        this.resultContent.setBorder(new EmptyBorder(0,20,0,0));
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
            JPanel entry = new ResultEntry(sd);
            entry.setOpaque(false);
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
            title.setForeground(new Color(220,150,70));
            title.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,14));
            title.setBorder(new EmptyBorder(0,0,0,0));
            JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
            titleContainer.setOpaque(false);
            titleContainer.add(title);
            title.clickedCallback().addCallback(new Runnable(){
                @Override
                public void run() {
                    d.displayContent();
                    ContentPaneControler.getInstance().display(ResultControler.getInstance());
                }
            });


            String[] descLines = d.searchEntryDescription().split("\n");

            JPanel descPan = new JPanel();
            descPan.setBackground(new Color(225,225,225,80));

            BoxLayout box = new BoxLayout(descPan, BoxLayout.PAGE_AXIS);
            descPan.setLayout(box);
            descPan.add(title);

            for(int i = 0; i<descLines.length; i++){
                JLabel lab = new JLabel(descLines[i]);
                lab.setFont(LocalFont.getFont(LocalFont.LATO,12));
                lab.setForeground(Color.DARK_GRAY);
                descPan.add(lab);
            }

            this.setBorder(new EmptyBorder(15,5,5,5));
            this.setOpaque(false);
            this.setLayout(new BorderLayout());
            this.add(descPan, BorderLayout.NORTH);

        }
    }


}

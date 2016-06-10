package view;

import library.*;
import utils.BackgroundPanel;
import utils.Callback;
import utils.LocalFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by noodle on 17.05.16.
 */
public class BodyResult extends BackgroundPanel {


    private JPanel content;
    private utils.Button closeButton;
    private utils.Button updateButton;
    private SearchDescription searchDescription = null;
    private JTextArea noteArea = new JTextArea();

    public BodyResult() {

        super("images/background_body.png");

        this.setLayout(new BorderLayout());

        content = new JPanel(new BorderLayout());
        content.setOpaque(false);

        closeButton= new utils.Button("close", new Color(40,40,40), LocalFont.getFont(LocalFont.FRANCOISONE,12));
        updateButton= new utils.Button("save", new Color(40,40,40), LocalFont.getFont(LocalFont.FRANCOISONE,12));
        this.add(content);

    }


    public Callback closeButtonCallback(){
        return closeButton.clickedCallback();
    }

    public Callback updateButtonCallback(){
        return updateButton.clickedCallback();
    }

    public SearchDescription getDescription(){
        return searchDescription;
    }

    public String getNote(){
        return noteArea.getText();
    }


    private class ResultContainer extends JPanel {


        private JPanel titleContainer;
        private JPanel contentContainer;

        public ResultContainer(){

            this.setOpaque(false);

            contentContainer = new JPanel();
            contentContainer.setOpaque(false);

            titleContainer = new JPanel(new BorderLayout());
            titleContainer.setOpaque(false);

            BoxLayout box = new BoxLayout(contentContainer, BoxLayout.PAGE_AXIS);
            contentContainer.setLayout(box);

            this.setLayout(new BorderLayout());
            this.add(titleContainer, BorderLayout.NORTH);
            JPanel contentUp = new JPanel(new BorderLayout());
            contentUp.setOpaque(false);
            contentUp.add(contentContainer,BorderLayout.NORTH);

            this.add(contentUp);

        }



        public void addPageTitle(String title){
            JPanel bodyTitle = new JPanel();
            bodyTitle.setOpaque(false);

            JLabel titleLab = new JLabel(title);
            titleLab.setFont(utils.LocalFont.getFont(LocalFont.BREE, 24));
            titleLab.setForeground(new Color(200, 200, 200));
            titleLab.setBorder(new EmptyBorder(20,40, 10, 0));

            JPanel buttonsContainer = new JPanel();
            buttonsContainer.setOpaque(false);
            buttonsContainer.add(updateButton);
            buttonsContainer.add(closeButton);

            titleContainer.add(titleLab, BorderLayout.WEST);
            titleContainer.add(buttonsContainer, BorderLayout.EAST);
        }


        public void addParaTitle(String title){
            contentContainer.add(new utils.Title(title));
        }

        private void addLabel(String prefix, String value){
            String text;
            if(value == null){
                text = prefix + "unknown";
            }
            else {
                text = prefix + value;
            }

            JLabel label = new JLabel(text);
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel.setOpaque(false);
            panel.setBorder(new EmptyBorder(0,12,0,0));
            panel.add(label);
            contentContainer.add(panel);
        }

        // TODO
        private void addTextArea(String text){

            if(text == null){
                text = "";
            }

            String[] noteWords = text.split("\\s");
            StringBuilder note = new StringBuilder();

            for(int i = 0; i<noteWords.length; i++){
                note.append(noteWords[i]);
                note.append(" ");
                if(i%8==7) {
                    note.append("\n");
                }
            }

            noteArea = new JTextArea(6,40);
            noteArea.setBorder(BorderFactory.createLineBorder(new Color(230,230,230),3));
            noteArea.setAlignmentX(0);
            if(text != null) {
                noteArea.setText(note.toString());
            }
            JPanel noteContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
            noteContainer.setOpaque(false);
            noteContainer.add(noteArea);
            noteContainer.setBorder(new EmptyBorder(5,20,0,0));
            contentContainer.add(noteContainer);
        }


    }



    public void setData(String[][] datas){

        if(datas.length <= 0){
            return;
        }

        int rowSize = datas.length;
        int colSize = datas[0].length;


        JScrollPane pane = new JScrollPane(){
            public void setLayout(LayoutManager layout) {
                setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                super.setLayout(layout);
            }
        };
        pane.setOpaque(false);
        pane.setBorder(new EmptyBorder(0,0,0,0));

        JPanel dataContainer = new JPanel();
        dataContainer.setOpaque(false);
        BoxLayout bl = new BoxLayout(dataContainer, BoxLayout.LINE_AXIS );
        dataContainer.setLayout(bl);

        for(int col = 0; col < colSize; col++){

            JPanel colContainer = new JPanel();
            colContainer.setOpaque(false);
            bl = new BoxLayout(colContainer, BoxLayout.PAGE_AXIS);
            colContainer.setLayout(bl);

            for(int row = 0; row < rowSize; row++){

                JLabel label = new JLabel(datas[row][col]);
                colContainer.add(label);
            }
            colContainer.setBorder(new EmptyBorder(0,0,0,40));

            dataContainer.add(colContainer);
        }

        pane.setViewportView(dataContainer);
        pane.getViewport().setOpaque(false);

        this.content.removeAll();
        this.content.add(pane);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }});

    }




    public void setTitle(Title t){

        searchDescription = t;

        this.content.removeAll();

        ResultContainer container = new ResultContainer();
        container.addPageTitle(t.title);
        container.addParaTitle("general");
        container.addLabel("serie number : ", t.serieNumber.toString());
        container.addLabel("translator name : ",t.translatorName);
        container.addLabel("storyLength : ",t.storyLength.toString());
        container.addLabel("type : ",t.type);
        container.addParaTitle("note");
        container.addTextArea(t.note);

        this.content.add(container, BorderLayout.CENTER);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });


    }




    public void setAuthor(Author author){

        searchDescription = author;
        this.content.removeAll();

        ResultContainer container = new ResultContainer();

        container.addPageTitle(author.name);
        container.addParaTitle("general");
        container.addLabel("legal name : ", author.legalName);
        container.addLabel("pseudo : ", author.pseudo);
        container.addLabel("email : ", author.email);
        container.addLabel("language : ", author.language);

        container.addParaTitle("born");
        container.addLabel("birth date : ", author.birthdate);
        container.addLabel("birth place : ", author.birthplace);
        container.addLabel("death date : ",author.deathDate);

        container.addParaTitle("note");
        container.addTextArea(author.note);

        this.content.add(container);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });


    }



    public void setPublisher(Publisher p){

        searchDescription = p;
        this.content.removeAll();

        ResultContainer container = new ResultContainer();

        container.addPageTitle(p.name);
        container.addParaTitle("note");
        container.addTextArea(p.note);

        this.content.add(container);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });

    }



    public void setAward(Award award){

        searchDescription = award;
        this.content.removeAll();

        ResultContainer container = new ResultContainer();
        container.addPageTitle(award.title);
        container.addParaTitle("general");
        container.addLabel("name : ", award.name);
        container.addLabel("supplied by : ", award.byName);
        container.addLabel("for : ", award.forName);
        container.addLabel("date : ", award.date);
        container.addParaTitle("note");
        container.addTextArea(award.note);

        this.content.add(container);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        });


    }






}

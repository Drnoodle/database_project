package view;

import utils.BackgroundPanel;
import utils.Callback;
import utils.LocalFont;
import utils.Button;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by noodle on 19.05.16.
 */
public class BodyInsert extends BackgroundPanel {


    private Button insertDataButton;

    JTextField nameField = new JTextField();
    JTextField legalNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField pseudoField = new JTextField();
    JTextField birthplaceField = new JTextField();
    JTextField birthdateField = new JTextField();
    JTextField deathdateField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField noteField = new JTextField();
    JTextField imgLinkField = new JTextField();


    public BodyInsert(){

            super("images/background_body.png");

            JLabel nameLabel = new JLabel("name");
            JLabel legalNameLabel = new JLabel("legal name");
            JLabel lastNameLabel = new JLabel("last name");
            JLabel pseudoLabel = new JLabel("pseudo");
            JLabel birthplaceLabel = new JLabel("birthplace");
            JLabel birthdateLabel = new JLabel("birthdate");
            JLabel deathdateLabel = new JLabel("deathdate");
            JLabel emailLabel = new JLabel("email");
            JLabel noteLabel = new JLabel("note");
            JLabel imgLinkLabel = new JLabel("image link");

            nameLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            legalNameLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            lastNameLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            pseudoLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            birthplaceLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            birthdateLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            deathdateLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            emailLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            noteLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));
            imgLinkLabel.setFont(LocalFont.getFont(LocalFont.FRANCOISONE,12));


            this.nameField = new JTextField();
            this.legalNameField = new JTextField();
            this.lastNameField = new JTextField();
            this.pseudoField = new JTextField();
            this.birthplaceField = new JTextField();
            this.birthdateField = new JTextField();
            this.deathdateField = new JTextField();
            this.emailField = new JTextField();
            this.noteField = new JTextField();
            this.imgLinkField = new JTextField();



            JPanel container = new JPanel();
            container.setOpaque(false);
            GridLayout gl = new GridLayout(10,2);
            container.setLayout(gl);

            container.add(nameLabel);
            container.add(nameField);

            container.add(legalNameLabel);
            container.add(legalNameField);

            container.add(lastNameLabel);
            container.add(lastNameField);

            container.add(pseudoLabel);
            container.add(pseudoField);

            container.add(birthplaceLabel);
            container.add(birthplaceField);

            container.add(birthdateLabel);
            container.add(birthdateField);

            container.add(deathdateLabel);
            container.add(deathdateField);

            container.add(emailLabel);
            container.add(emailField);

            container.add(noteLabel);
            container.add(noteField);

            container.add(imgLinkLabel);
            container.add(imgLinkField);


            this.insertDataButton = new Button("insert");
            insertDataButton.setFont(LocalFont.getFont(LocalFont.BREE,14));
            container.add(insertDataButton);
            container.setBorder(new EmptyBorder(20,20,20,20));


            this.setLayout(new BorderLayout());
            this.add(container);
            this.add(insertDataButton, BorderLayout.SOUTH);
            this.setBorder(new EmptyBorder(50,50,50,50));

        }



    public String getNameText(){return nameField.getText();}
    public String getLegalNameText(){return legalNameField.getText();}
    public String getLastNameText(){return lastNameField.getText();}
    public String getPseudoText(){return pseudoField.getText();}
    public String getBirthplaceText(){return birthplaceField.getText();}
    public String getBirthdateText(){return birthdateField.getText();}
    public String getDeathdateText(){return deathdateField.getText();}
    public String getEmailText(){return emailField.getText();}
    public String getNoteText(){return noteField.getText();}
    public String getImgLinkText(){return imgLinkField.getText();}



    public Callback newInsertCallback(){
        return insertDataButton.clickedCallback();
    }




}

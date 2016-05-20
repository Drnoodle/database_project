package view;

import utils.BackgroundPanel;
import utils.LocalFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by noodle on 20.05.16.
 */
public class BodyAccueil extends BackgroundPanel {

    public BodyAccueil() {

        super("images/background_body.png");

        JLabel imgLab = new JLabel(new ImageIcon("images/book.png"));
        JLabel libDB = new JLabel("LibDBMS");
        libDB.setFont(LocalFont.getFont(LocalFont.PATUAONE,20));
        libDB.setForeground(new Color(40,140,200));
        libDB.setBorder(new EmptyBorder(0,20,0,0));

        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new BorderLayout());
        container.add(imgLab);
        container.add(libDB,BorderLayout.SOUTH);
        container.setBorder(new EmptyBorder(180,0,0,0));

        this.add(container);

    }




}

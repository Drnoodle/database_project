package utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import com.sun.tools.javac.comp.Flow;
import utils.Callback;

/**
 * Custom Button
 */
public class Button extends JPanel implements MouseListener {


    private static final long serialVersionUID = -127037380654037854L;


    private Callback callback = new Callback();

    private JLabel label = new JLabel();



    public Button(
            String value,
            Color foregroundColor,
            Font font){


        this.addMouseListener(this);
        this.label = new JLabel(value);
        this.label.setFont(font);
        this.label.setForeground(foregroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(label);
        this.setOpaque(false);

    }


    /**
     * callback on clicked event
     */
    public Callback clickedCallback() {
        return callback;
    }


    @Override
    public void mouseClicked(MouseEvent arg0) {

        clickedCallback().runAll();

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }






}

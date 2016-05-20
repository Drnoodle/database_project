package utils;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Custom combo box
 */
public class CustomComboBox extends JComboBox<String> {

	private static final long serialVersionUID = 1L;


    private Callback callback = new Callback();


	public CustomComboBox(){
		setEditable(true);
		setRenderer(new MyComboBoxRenderer());
		setEditor(new MyComboBoxEditor());
		setUI(new ArrowUI());
        this.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                callback.runAll();
            }
        });

    }


    public Callback clickedCallback(){
        return callback;
    }
	
	
	private class MyComboBoxRenderer 
	extends JPanel implements ListCellRenderer<String> {

		
		private static final long serialVersionUID = 1L;

		private JLabel label = new JLabel();

		public MyComboBoxRenderer() {
			
			//setOpaque(false);
			label.setFont(LocalFont.getFont(LocalFont.PATUAONE, 12));
			label.setForeground(new Color(150,150,150));
			this.setBorder(new EmptyBorder(1,10,1,10));
			this.setBackground(new Color(35,35,35));
			this.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
			this.add(label);
		}


		@Override
		public Component getListCellRendererComponent(
				JList<? extends String> list, 
				String value, 
				int index,
				boolean isSelected, 
				boolean cellHasFocus) {

            if (value != null) {
                label.setText(value.toString());
            }

			return this;
		}


	}
	
	
	
	private class MyComboBoxEditor extends BasicComboBoxEditor {
		
		private JLabel label = new JLabel();
		private JPanel panel = new JPanel();
		private Object selectedItem = new Object();
		
		public MyComboBoxEditor() {
			
			label.setOpaque(false);
			label.setFont(LocalFont.getFont(LocalFont.PATUAONE, 12));
			label.setForeground(new Color(220,220,220));
			label.setBorder(new EmptyBorder(3,0,0,0));

			panel.add(label);
			panel.setBackground(new Color(50,50,50));
			setBorder(new EmptyBorder(0,10,0,0));
		}
		
		public Component getEditorComponent() {
			return this.panel;
		}
		
		public Object getItem() {
			return this.selectedItem.toString();
		}
		
		public void setItem(Object item) {
			if(item != null){	
			this.selectedItem = item;
			label.setText(item.toString());
			}
		}
		
	}
	

	
	class ArrowUI extends BasicComboBoxUI {
	    
		@Override 
	    protected JButton createArrowButton() {
	    	return new JButton(){
                @Override
                public int getWidth(){return 0;}
            };
	    }
	    
	}
	


}

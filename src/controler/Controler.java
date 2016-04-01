package controler;

import javax.swing.JComponent;

public interface Controler {
	
	/**
	 * before displaying his component the controler is activated 
	 */
	public void active();
	
	/**
	 * when the view is no longer displayed the controler is inactivated
	 */
	public void inactive();

	
	/**
	 * controled component
	 */
	public JComponent getComponent(); 
	
	
	
}

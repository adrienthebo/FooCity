package foocity.grid;

import java.util.EventListener;

/**
 * implement this interface when subscribing to Grid updates
 */
public interface GridListener extends EventListener {
	
	/**
	 * 
	 * @param e The grid event storing the state about the event
	 */
	public void gridUpdated(GridEvent e);
}

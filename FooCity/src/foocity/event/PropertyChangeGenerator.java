package foocity.event;

import java.beans.PropertyChangeListener;

/**
 * Defines an interface that declares that a class generates property change
 * events.
 */
public interface PropertyChangeGenerator {

	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	public void removePropertyChangeListener(PropertyChangeListener listener);
}

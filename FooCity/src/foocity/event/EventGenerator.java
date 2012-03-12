package foocity.event;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.EventListenerList;

public class EventGenerator {

	/**
	 * <p>
	 * Generate a property change event.
	 * </p>
	 *
	 * @param trigger The object that is generating the event
	 * @param listeners The EventListenerList of the triggering object
	 * @param property The name of the property that has been changed
	 * @param oldObject The old value of the property
	 * @param newObject The new value of the property
	 */
	public static <T> void firePropertyChangeEvent(
			Object trigger,
			EventListenerList listenerList,
			String property,
			T oldObject,
			T newObject
		) {
		// HOLY CRAP YOU CAN HAVE PARAMETERIZED METHODS! NEATO!

		PropertyChangeListener[] listeners = listenerList.getListeners(PropertyChangeListener.class);

		PropertyChangeEvent event = new PropertyChangeEvent(trigger, property, oldObject, newObject);

		for(PropertyChangeListener l : listeners) {
			PropertyChangeListener listener = l;
			listener.propertyChange(event);
		}
	}
}

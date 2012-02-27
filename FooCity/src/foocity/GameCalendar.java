package foocity;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.event.EventListenerList;
/**
 * This class forms a thin wrapper around the java.util.Calendar, with a
 * simpler interface and support for Event Listeners.
 */
public class GameCalendar {
	
	private EventListenerList _listeners = new EventListenerList();
	private Calendar _calendar;
	
	public GameCalendar(Calendar newCalendar) {
		_calendar = newCalendar;
	}
	
	/**
	 * Adds a month to the current date.
	 */
	public void increment() {
		//TODO notify listeners
		_calendar.add(Calendar.MONTH, 1);
	}
	
	@Override
	public String toString() {
		return _calendar.toString();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_listeners.add(PropertyChangeListener.class, listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_listeners.remove(PropertyChangeListener.class, listener);
	}

	protected void fireEvent(String property, Calendar oldDate, Calendar newDate) {
		PropertyChangeListener[] listeners = (PropertyChangeListener[])_listeners.getListenerList();
		
		PropertyChangeEvent event = new PropertyChangeEvent(this, property, oldDate, newDate);
		
		for(PropertyChangeListener listener : listeners) {
			listener.propertyChange(event);
		}
	}
}

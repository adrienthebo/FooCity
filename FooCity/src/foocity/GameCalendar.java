package foocity;

import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.event.EventListenerList;

import foocity.event.*;
/**
 * This class forms a thin wrapper around the java.util.Calendar, with a
 * simpler interface and support for Event Listeners.
 */
public class GameCalendar implements PropertyChangeGenerator {

	private EventListenerList _listeners = new EventListenerList();
	private Calendar _calendar;

	public GameCalendar(Calendar newCalendar) {
		_calendar = newCalendar;
	}

	/**
	 * Adds a month to the current date.
	 */
	public void increment() {
		Object oldCalendar = _calendar.clone();
		_calendar.add(Calendar.MONTH, 1);
		Object newCalendar = _calendar.clone();
		EventGenerator.firePropertyChangeEvent(this, _listeners, "Calendar", oldCalendar, newCalendar);
	}

	@Override
	public String toString() {
		return _calendar.toString();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_listeners.add(PropertyChangeListener.class, listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_listeners.remove(PropertyChangeListener.class, listener);
	}
}

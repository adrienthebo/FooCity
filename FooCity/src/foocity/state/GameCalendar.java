package foocity.state;

import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.event.EventListenerList;

import foocity.event.*;
/**
 * <p>
 * This class forms a thin wrapper around the java.util.Calendar, with a
 * simpler interface and support for Event Listeners.
 * </p>
 */
public class GameCalendar implements PropertyChangeGenerator {

	private EventListenerList _listeners = new EventListenerList();
	private Calendar _calendar;

	/**
	 * <p>
	 * Instantiates a new calendar at the current date.
	 * </p>
	 */
	public GameCalendar() {
		_calendar = new GregorianCalendar();
	}

	/**
	 * <p>
	 * Instantiates a new calendar at a specified date.
	 * </p>
	 *
	 * @param newCalendar The calendar representing the new date.
	 */
	public GameCalendar(Calendar newCalendar) {
		_calendar = newCalendar;
	}

	/**
	 * </p>
	 * Adds a month to the current date.
	 * </p>
	 */
	public void increment() {
		Object oldCalendar = _calendar.clone();
		_calendar.add(Calendar.MONTH, 1);
		Object newCalendar = _calendar.clone();
		EventGenerator.firePropertyChangeEvent(this, _listeners, "Calendar", oldCalendar, newCalendar);
	}

	/**
	 * <p>
	 * Sets the date to a specific time.
	 * </p>
	 *
	 * @param newCalendar The calendar representing the new date.
	 */
	public void set(Calendar newCalendar) {
		Object oldCalendar = _calendar.clone();
		_calendar = newCalendar;
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

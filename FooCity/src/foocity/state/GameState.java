package foocity.state;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import foocity.grid.Grid;
import foocity.population.Person;
import foocity.rules.*;

/**
 * <p>
 * Representation of a particular game state.
 * </p>
 *
 * <p>
 * This class forms a representation of a specific game. All members should be
 * either persistent while this game is active, or ensure that objects hand off
 * event listeners so that state swap out is transparent and listening classes
 * do not need to be aware of such a state change.
 * </p>
 *
 * <p>
 * However, this requirement raises the issue of how components will be
 * notified of a full state change.
 * </p>
 */
public class GameState {
	protected Grid _grid;
	protected TaxRates _taxes;
	protected GameCalendar _calendar;
	protected List<Person> _citizens = new ArrayList<Person>();

	protected int _funds;
	/*
	 * XXX I'm not sure if funds should be a separate class; it might make
	 * sense to do so to isolate the logic of purchasing things and whatnot
	 * into a different class. It would also make adding a listener for funds
	 * simpler. On the other hand, it's just a ****ing integer.
	 */

	public GameState(Grid newGrid, TaxRates newTaxRates, GameCalendar newGameCalendar, int newFunds) {
		_grid = newGrid;
		_taxes = newTaxRates;
		_calendar = newGameCalendar;
		_funds = newFunds;

		RuleSet.registerState(this);
	}

	public Grid getGrid() {
		return _grid;
	}

	public TaxRates getTaxRates() {
		return _taxes;
	}

	public GameCalendar getCalendar() {
		return _calendar;
	}

	public int getFunds() {
		return _funds;
	}
}

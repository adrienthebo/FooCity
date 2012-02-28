package foocity;

import foocity.grid.Grid;

/**
 * The GameState class is an aggregation of the different state components of a
 * specific game.
 */
public class GameState {
	protected Grid _grid;
	protected TaxRates _taxes;
	protected GameCalendar _calendar;
	
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

package foocity;

import foocity.grid.Grid;

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

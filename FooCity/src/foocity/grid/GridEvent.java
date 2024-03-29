package foocity.grid;

import java.util.EventObject;

/**
 * <p>
 * Stores a grid event with coordinates and state change.
 * </p>
 *
 * <p>
 * Provides an event object that returns the location of a grid update, the old
 * tile type, and the new tile type. To receive these, implement the GridListener
 * interface and attach to a given grid object.
 * </p>
 */
public class GridEvent extends EventObject {

	private static final long serialVersionUID = 2905534740897957517L;
	private int _xAxis, _yAxis;
	private String _oldTile, _newTile;

	public GridEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public GridEvent(Object source, int xAxis, int yAxis, String oldTile, String newTile) {
		super(source);

		_xAxis = xAxis;
		_yAxis = yAxis;

		_oldTile = oldTile;
		_newTile = newTile;
	}

	public int getXAxis() {
		return _xAxis;
	}

	public int getYAxis() {
		return _yAxis;
	}

	public String getOldTile() {
		return _oldTile;
	}

	public String getNewTile() {
		return _newTile;
	}
}

package foocity.grid;

import java.util.Iterator;
import java.util.NoSuchElementException;

import foocity.tile.*;
/**
 * Internal grid iteration class.
 *
 * This class is in the default permission namespace as it permits direct
 * access to grid members, and this needs to be hidden to avoid breaking encapsulating.
 *
 * Iteration is done top to bottom, then left to right.
 */
class GridMemberIterator implements Iterator<Tile> {

	protected Grid _grid;
	protected int _xAxisIter = 0;
	protected int _yAxisIter = 0;

	public GridMemberIterator(Grid grid) {
		_grid = grid;
	}

	/**
	 * @returns The next tile in the grid if it exists
	 */
	@Override
	public Tile next() {
		Tile nextTile = null;
		if(hasNextY()) {
			/*
			 *  We are still scanning across one column on the y axis. Return
			 *  the current tile and increment the iterator count.
			 */
			nextTile = getCurrentTile();
			_yAxisIter++;
		}
		else if(hasNextX()) {
			/*
			 * We've reached the end of a row, drop down to the next row,
			 * retrieve the first element for returning, and point to the next
			 * location for the next iteration
			 */
			_yAxisIter = 0;
			_xAxisIter++;

			// XX This could be merged with the previous case.
			nextTile = getCurrentTile();
			_yAxisIter++;
		}
		else {
			/* Per the Iterable interface spec
			 * (http://docs.oracle.com/javase/1.4.2/docs/api/java/util/Iterator.html)
			 * if we are out of elements, we should throw a NoSuchElementException
			 */
			throw new NoSuchElementException();
		}

		return nextTile;
	}

	/**
	 * The grid is immutable in size, so we cannot discard an element of the
	 * grid.
	 *
	 * @throws UnsupportedOperationException();
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @thisisselfexplanatory
	 */
	@Override
	public boolean hasNext() {
		return hasNextX() || hasNextY();
	}

	private boolean hasNextX() {
		return _xAxisIter < (_grid.getXSize() - 1);
	}

	private boolean hasNextY() {
		return _yAxisIter < (_grid.getYSize());
	}

	private Tile getCurrentTile() {
		return _grid._tiles[_xAxisIter][_yAxisIter];
	}

	// XXX This was written in a fit of late night hacking. This may be evil.
	protected int currentXAxis() {
		return _xAxisIter;
	}

	// XXX This was written in a fit of late night hacking. This may be evil.
	protected int currentYAxis() {
		return _yAxisIter;
	}
}

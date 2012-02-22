package foocity.grid;

import java.util.Iterator;
import java.util.NoSuchElementException;

import foocity.tile.*;
/**
 * Internal grid iteration class.
 * 
 * This class is in the default permission namespace as it permits direct
 * access to grid members, and this needs to be hidden to avoid breaking encapsulating.
 */
class GridIterator implements Iterator<Tile> {
	
	protected Grid _grid;
	protected int _xAxisIter = 0;
	protected int _yAxisIter = 0;

	public GridIterator(Grid grid) {
		_grid = grid;
	}
	
	/**
	 * @returns The next tile in the grid if it exists
	 */
	public Tile next() {
		Tile nextTile = null;
		if(hasNextX()) {
			/*
			 *  We are still scanning across one row on the X axis. Return the
			 *  current tile and increment the iterator count.
			 */
			nextTile = getCurrentTile();
			_xAxisIter++;
		}
		else if(hasNextY()) {
			/*
			 * We've reached the end of a row, drop down to the next row and
			 * reset the X axis iterator.
			 */
			_xAxisIter = 0;
			_yAxisIter++;
			nextTile = getCurrentTile();
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
	 * grid. If you call this I will laugh at you.
	 * 
	 * @throws UnsupportedOperationException();
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @thisisselfexplanatory
	 */
	public boolean hasNext() {
		return hasNextX() || hasNextY();
	}
	
	private boolean hasNextX() {
		return _xAxisIter < (_grid.getXSize() - 1);
	}
	
	private boolean hasNextY() {
		return _yAxisIter < (_grid.getYSize() - 1);
	}
	
	private Tile getCurrentTile() {
		return _grid._tiles[_xAxisIter][_yAxisIter];
	}
	
}

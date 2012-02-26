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
		return _yAxisIter < (_grid.getYSize());
	}
	
	private Tile getCurrentTile() {
		return _grid._tiles[_xAxisIter][_yAxisIter];
	}
	
}

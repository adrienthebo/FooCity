package foocity.grid;

import java.util.Iterator;

import foocity.tile.Tile;
/**
 * Public version of the GridIterator. All functionality is delegated to the
 * GridMemberIterator and munged to strings.
 * @author adrien
 *
 */
public class GridIterator implements Iterator<String> {
	
	Iterator<Tile> _iter;
	
	public GridIterator(Grid grid) {
		_iter = new GridMemberIterator(grid);
	}

	public boolean hasNext() {
		return _iter.hasNext();
	}

	public String next() {
		return _iter.next().unqualifiedClassName();
	}

	public void remove() {
		_iter.remove();
	}
}

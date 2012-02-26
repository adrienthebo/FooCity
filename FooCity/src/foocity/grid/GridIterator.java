package foocity.grid;

import java.util.Iterator;

import foocity.tile.Tile;
/**
 * Public version of the GridIterator. All functionality is delegated to the
 * GridMemberIterator and munged to strings.
 */
public class GridIterator implements Iterator<String> {
	
	Iterator<Tile> _iter;
	
	public GridIterator(Grid grid) {
		_iter = new GridMemberIterator(grid);
	}

	@Override
	public boolean hasNext() {
		return _iter.hasNext();
	}

	@Override
	public String next() {
		return _iter.next().unqualifiedClassName();
	}

	@Override
	public void remove() {
		_iter.remove();
	}
}

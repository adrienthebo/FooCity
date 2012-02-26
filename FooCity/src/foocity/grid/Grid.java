package foocity.grid;

import java.util.Iterator;

import foocity.tile.*;

/**
 * Representation of the game grid
 * 
 * This class provides access to the members of the grid, and provides methods
 * for safely interacting with the underlying elements.
 */
public class Grid {
	
	/*
	 *  The tiles themselves are exposed so that internal helper classes can
	 *  directly access and update tile elements,
	 */
	protected Tile[][] _tiles;
	private int _xSize;
	private int _ySize;
	
	/**
	 * Generates a game grid of the specified size.
	 * 
	 * <pre><b>Example:</b></pre>
	 * 
	 * <code>
	 * Grid gameGrid = new Grid(10, 10);
	 * </code>
	 * 
	 * @param xSize the size of the grid X axis
	 * @param ySize the size of the grid Y axis
	 */
	public Grid(int xSize, int ySize) {
		_xSize = xSize;
		_ySize = ySize;
		_tiles = new Tile[xSize][ySize];
	}
	
	/**
	 * <p>
	 * Generates a game grid from a 2D array of tile names.
	 * </p>
	 * 
	 * <p>
	 * XXX This currently does not check if the 2D array is irregular. It
	 * assumes that the length of the first nested array is the length of the
	 * subsequent arrays.
	 * </p>
	 * 
	 * @param newTiles a 2D array of tile names
	 */
	public Grid(String[][] newTiles) {
		// Generate new empty grid
		this(newTiles.length, newTiles[0].length);
		
		// Populate the grid by instantiating a tile at each location
		for(int yIter = 0; yIter < _ySize; yIter++) {
			for(int xIter = 0; xIter < _xSize; xIter++) {
				setTile(xIter, yIter, newTiles[xIter][yIter]);
			}
		}
	}
	
	public int getXSize() { 
		return _xSize;
	}
	
	public int getYSize() {
		return _ySize;
	}
	/**
	 * Provides the intern'd class name. This is done to encapsulate the
	 * underlying grid members.
	 * 
	 * @param xAxis the zero indexed X coordinate
	 * @param yAxis the zero indexed Y coordinate
	 * 
	 * @return the class type of the tile at the given axis
	 */
	@SuppressWarnings("unchecked") // XXX this is lazy
	public String getTile(int xAxis, int yAxis) {
		if(xAxis >= _xSize || yAxis >= _ySize)
			throw new IndexOutOfBoundsException(); // XXX Does this need to be done explicitly?
		
		Class<Tile> tileClass = (Class<Tile>) _tiles[xAxis][yAxis].getClass();
		return tileClass.getName().intern();
	}
	
	/**
	 * Sets the tile type at the specified location
	 * 
	 * @param xAxis the X coordinate
	 * @param yAxis the Y coordinate
	 * @param tileClass the name of the new tile class
	 * @return whether the tile was updated successfully.
	 * @wtf I'm not going to be caught dead writing case statements for class names
	 */
	public boolean setTile(int xAxis, int yAxis, String tileClass) {
		if(xAxis >= _xSize || yAxis >= _ySize)
			throw new IndexOutOfBoundsException(); // XXX Does this need to be done explicitly?

		try {
			// Attempt to retrieve the class of the tile we're generating.
			Class<Tile> newTileClass = Tile.getSubTile(tileClass);
			
			if(newTileClass != null) {
				// Do some sweet sweet metaprogramming magic.
				Tile newTile = newTileClass.newInstance();
				_tiles[xAxis][yAxis] = newTile;
				return true;
			}
			else {
				/*
				 * The tile class couldn't be found, return false.
				 * XXX Should we just be aborting here since this error should
				 * only be encountered on developer error?
				 */
				System.err.println("Unable to instantiate requested tile \"" + tileClass + "\" at index " + xAxis + ", " + yAxis);
				System.err.println(Thread.currentThread().getStackTrace());
				return false;
			}
		} catch (Exception e) {
			/*
			 *  A motley of errors could have occurred while we tried us some
			 *  sweet metaprogramming magic, such as InstantiationExceptions
			 *  and IllegalAccessExceptions. If we get them, PANIC because
			 *  there's probably nothing we can do.
			 */
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <p>
	 * Produces a grid iterator for this grid.
	 * </p>
	 * 
	 * <p>
	 * XXX This breaks encapsulation, but I want to write tests for this.
	 * You probably don't need/want to use this.
	 * </p>
	 * 
	 * @return An iterator for the current grid.
	 */
	public Iterator<Tile> getIterator() {
		return new GridIterator(this);
	}
}
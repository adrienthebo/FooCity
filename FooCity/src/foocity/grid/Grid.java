package foocity.grid;

import javax.swing.event.EventListenerList;

import java.util.Iterator;

import foocity.tile.*;

/**
 * <p>
 * Representation of the game grid
 * </p>
 * 
 * <p>
 * This class provides access to the members of the grid, and provides methods
 * for safely interacting with the underlying elements.
 * </p>
 * 
 * <p>
 * This class can generate events, but it has a custom event type, the
 * GridEvent, that indicates the coordinates of an update as well as the update
 * made. See the GridEvent class for more information.
 * </p>
 */
public class Grid {
	
	/*
	 *  The tiles themselves are exposed so that internal helper classes can
	 *  directly access and update tile elements,
	 */
	protected Tile[][] _tiles;
	private int _xSize;
	private int _ySize;
	
	private EventListenerList _listeners = new EventListenerList();
	
	/**
	 * Generates a game grid of the specified size.
	 * 
	 * <pre><b>Example:</b></pre>
	 * 
	 * <pre>
	 * Grid gameGrid = new Grid(10, 10);
	 * </pre>
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
	 * <pre>
	 * String[][] tiles = {
	 *   {"Tile00", "Tile01"},
	 *   {"Tile10", "Tile11"},
	 *   {"Tile20", "Tile21"},
	 * };
	 * 
	 * Grid newGrid = new Grid(tiles);
	 * </pre>
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
		gridFromStrings(newTiles);
	}
	
	/**
	 * <p>
	 * Generates a game grid from a 2D array of characters
	 * </p>
	 * 
	 * <pre>
	 * char[][] tiles = {
	 *   {'D', 'D',},
	 *   {'D', 'G',},
	 *   {'F', '~',},
	 * };
	 * 
	 * Grid newGrid = new Grid(tiles);
	 * </pre>
	 *   
	 * <p>
	 * XXX This currently does not check if the 2D array is irregular. It
	 * assumes that the length of the first nested array is the length of the
	 * subsequent arrays.
	 * </p>
	 * 
	 * @param newTiles a 2D array of tile names
	 */
	public Grid(char[][] newTiles) {
		// Generate new empty grid
		this(newTiles.length, newTiles[0].length);
		gridFromChars(newTiles);
	}
	
	public int getXSize() { 
		return _xSize;
	}
	
	public int getYSize() {
		return _ySize;
	}

	/**
	 * <p>
	 * Provides the class name as a string of the tile at the given location.
	 * This is done to encapsulate the underlying grid members.
	 * </p>
	 * 
	 * @param xAxis the zero indexed X coordinate
	 * @param yAxis the zero indexed Y coordinate
	 * 
	 * @return the class type of the tile at the given axis
	 */
	public String getTile(int xAxis, int yAxis) {
		if(xAxis >= _xSize || yAxis >= _ySize)
			throw new IndexOutOfBoundsException(); // XXX Does this need to be done explicitly?
		
		Tile thisTile = _tiles[xAxis][yAxis];
		return thisTile.getType().getName();
	}
	
	/**
	 * <p>
	 * Sets the tile type at the specified location
	 * </p>
	 * 
	 * <p>
	 * This method will generate a GridEvent upon success.
	 * </p>
	 * 
	 * @param xAxis the X coordinate
	 * @param yAxis the Y coordinate
	 * @param tileClass the name of the new tile class
	 * @return whether the tile was updated successfully.
	 * @wtf I'm not going to be caught dead writing case statements for class names
	 */
	public boolean setTile(int xAxis, int yAxis, String tileName) {
		if(xAxis >= _xSize || yAxis >= _ySize)
			throw new IndexOutOfBoundsException(); // XXX Does this need to be done explicitly?

		Tile thisTile = _tiles[xAxis][yAxis];

		TileType newType = TileCollection.instance().getByName(tileName);

		if(newType != null) {
			thisTile.setType(newType);

			if(thisTile != null) {
				/* If we're still populating the grid, existing grid elements
				 * will be empty. Firing grid events on null tiles is 
				 * 1) meaningless and 2) kinda dangerous. Only fire an event
				 * on non-null old tiles.
				 */
				TileType oldType = thisTile.getType();
				fireGridUpdated(xAxis, yAxis, oldType.getName(), newType.getName());
			}
			return true;
		}
		else {
			System.err.println("WARNING: Unable to set requested tile type \"" + tileName + "\" at index " + xAxis + ", " + yAxis);
			return false;
		}
	}

	/**
	 * <pre>
	 * Populate a grid from a grid of strings naming tiles
	 * </pre>
	 *
	 * <pre>
	 * Note that this will *NOT* trigger events.
	 * </pre>
	 *
	 * @param tiles a 2D array of tile names
	 */
	protected void gridFromStrings(String[][] tiles) {
		// Populate the grid by instantiating a tile at each location
		for(int yIter = 0; yIter < _ySize; yIter++) {
			for(int xIter = 0; xIter < _xSize; xIter++) {
				String currentTileName = tiles[xIter][yIter];
				_tiles[xIter][yIter] = new Tile(currentTileName);
			}
		}
	}
	
	/**
	 * <pre>
	 * Populate a grid from a grid of strings naming tiles
	 * </pre>
	 *
	 * <pre>
	 * Note that this will *NOT* trigger events.
	 * </pre>
	 *
	 * @param tiles A 2D array of characters representing tiles
	 */
	protected void gridFromChars(char[][] tiles) {
		// Populate the grid by instantiating a tile at each location
		for(int yIter = 0; yIter < _ySize; yIter++) {
			for(int xIter = 0; xIter < _xSize; xIter++) {
				char currentChar = tiles[xIter][yIter];
				_tiles[xIter][yIter] = new Tile(currentChar);
			}
		}
	}

	/**
	 * @return An iterator for the current grid.
	 */
	public Iterator<String> getIterator() {
		return new GridIterator(this);
	}
	
	public void addGridListener(GridListener listener) {
		_listeners.add(GridListener.class, listener);
	}
	
	public void removeGridListener(GridListener listener) {
		_listeners.remove(GridListener.class, listener);
	}
	
	/**
	 * <p>
	 * Sends a GridEvent to all registered GridListeners
	 * </p>
	 * @param xAxis the X axis of the event
	 * @param yAxis the Y axis of the event
	 * @param oldTile The name of the tile type being replaced
	 * @param newTile The name of the replacing tile type
	 */
	protected void fireGridUpdated(int xAxis, int yAxis, String oldTile, String newTile) {
		GridListener[] listeners = _listeners.getListeners(GridListener.class);

		GridEvent event = new GridEvent(this, xAxis, yAxis, oldTile, newTile);
		for(GridListener l : listeners ){
			l.gridUpdated(event);
		}
	}
}

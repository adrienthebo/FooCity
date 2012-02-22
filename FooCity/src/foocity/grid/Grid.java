package foocity.grid;

import foocity.tile.*;

import java.lang.*;
public class Grid {
	
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
	@SuppressWarnings("unchecked") // XXX this is lazy
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
}
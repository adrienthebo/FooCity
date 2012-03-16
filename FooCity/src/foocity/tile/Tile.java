package foocity.tile;

/**
 * <p>
 * Represents a specific location in a grid.
 * </p>
 *
 * <p>
 * This class acts as a collection of attributes at a single grid location. It
 * stores the attributes of the tile type, and can calculate the net statistics
 * of the location
 * </p>
 */
public class Tile {

	protected TileType _type;
	protected TileState _state = new TileState(this);

	/**
	 * <p>
	 * Generate a Tile with a preexisting type.
	 * </p>
	 *
	 * <pre>
	 * TileType t = BeachTile.newType();
	 * Tile myTile = new Tile(t);
	 * </pre>
	 */
	public Tile(TileType type) {
		_type = type;
	}

	/**
	 * <p>
	 * Generate a Tile and set the type by a name
	 * </p>
	 *
	 * <pre>
	 * Tile myTile = new Tile("Beach");
	 * </pre>
	 */
	public Tile(String type) {
		TileType newType = TileCollection.instance().getByName(type);
		if(newType != null) {
			_type = newType;
		}
		else {
			throw new IllegalArgumentException(type + " is not a valid tile name");
		}
	}

	/**
	 * <p>
	 * Generate a Tile and set the type by a character representation of a tile
	 * </p>
	 *
	 * <pre>
	 * Tile myTile = new Tile('B');
	 * </pre>
	 */
	public Tile(char c) {
		TileType newType = TileCollection.instance().getByChar(c);
		if(newType != null) {
			_type = newType;
		}
		else {
			throw new IllegalArgumentException(c + " is not a valid tile character representation");
		}
	}

	/**
	 * @return The current TileType associated with this tile
	 */
	public TileType getType() {
		return _type;
	}

	/**
	 * @param type The current TileType to associate with this tile
	 */
	public void setType(TileType type) {
		_type = type;
	}

	/**
	 * @return The TileState object for this type.
	 */
	public TileState getState() {
		return _state;
	}
}

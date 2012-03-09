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

	public Tile(TileType type) {
		_type = type;
	}

	public Tile(String type) {
		TileType newType = TileCollection.instance().getByName(type);
		if(newType != null) {
			_type = newType;
		}
		else {
			throw new IllegalArgumentException(type + " is not a valid tile name");
		}
	}

	public TileType getType() {
		return _type;
	}

	public void setType(TileType type) {
		_type = type;
	}
}

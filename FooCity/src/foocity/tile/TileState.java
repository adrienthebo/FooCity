package foocity.tile;

import java.util.EnumMap;
import java.util.Map;


/**
 * <p>
 * Holds the current state of this tile, with relation to the surrounding
 * tiles. This class is transient.
 * </p>
 */
public class TileState {

	protected Map<TileAttribute, Integer> _attributes = new EnumMap<TileAttribute, Integer>(TileAttribute.class);

	/**
	 * <p>
	 * Store the calculated attribute of this tile
	 * </p>
	 *
	 * @param attr The TileAttribute to store
	 * @param value The value of the attribute
	 */
	public void setAttribute(TileAttribute attr, int value) {
		_attributes.put(attr, value);
	}

	/**
	 * <p>
	 * Retrieve the calculated attribute of this tile.
	 * </p>
	 *
	 * @return The value of the requested attribute if it is set; else zero
	 */
	public int getAttribute(TileAttribute attr) {
		Integer val = _attributes.get(attr);
		if(val == null) {
			val = 0;
		}
		return val;
	}

	public void calculate(Tile[] neighbors) {
		for(Tile currentTile : neighbors) {
			add(currentTile.getType());
		}
	}

	/**
	 * <p>
	 * Merges the attributes of a tiletype with this state object
	 * </p>
	 */
	public void add(TileType type) {
		for(TileAttribute attr : TileAttribute.values()) {
			int value = getAttribute(attr);
			value += type.getAttribute(attr);
			_attributes.put(attr, value);
		}

	}

	/**
	 * <p>
	 * Wipes all attributes from the state.
	 * </p>
	 */
	public void clear() {
		_attributes.clear();
	}
}

package foocity.tile;

import java.util.EnumMap;
import java.util.Map;


/**
 * <p>
 * Holds the characteristics of a generic tile type.
 * </p>
 *
 * <p>
 * These are immutable values meant to define the characteristics that a tile
 * type produces, and not the current state of a tile.
 * </p>
 */
public class TileType {

	protected String _name;
	protected char _symbol;
	protected Map<TileAttribute, Integer> _attributes = new EnumMap<TileAttribute, Integer>(TileAttribute.class);

	public TileType(String name, char symbol, Map<TileAttribute, Integer> attributes) {
		_name = name;
		_symbol = symbol;
		_attributes = attributes;
	}

	public String getName() {
		return _name;
	}

	public char getSymbol() {
		return _symbol;
	}

	public int getAttribute(TileAttribute attr) {
		Integer val = _attributes.get(attr);
		if(val == null) {
			val = 0;
		}
		return val;
	}
}

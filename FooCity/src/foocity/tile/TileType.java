package foocity.tile;

import java.util.EnumMap;
import java.util.Map;


/**
 * Holds the characteristics of a generic tile type. These are immutable values
 * meant to define the characteristics that a tile type produces, and not the
 * current state of a tile.
 *
 */
public class TileType {
	
	protected String _name;
	protected char _symbol;
	protected Map<TileAttribute, Integer> _attributes = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
	
	public TileType(String name, char symbol, Map<TileAttribute, Integer> attributes) {
		_name = new String(name); //Deep copy of name
		_symbol = symbol;
		_attributes = attributes;
	}

	public String getName() {
		return _name; // XXX Should this be a deep copy?
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

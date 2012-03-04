package foocity.tile;

import java.util.*;


/**
 * This class is a singleton instance meant to contain all tile types existing
 * in the system. It also serves to instantiate the basic tile types, and
 * provide a facility for adding new types.
 */
public class TileCollection {
	
	private List<TileType> _types;
	private static TileCollection _self = new TileCollection();
	
	private TileCollection() {
		instantiateBaseTypes();
	}
	
	private void instantiateBaseTypes() {
		
	}
	
	public TileType getByName(String name) {
		return null;
	}
	
	public TileType getByChar(char c){
		return null;
	}
}

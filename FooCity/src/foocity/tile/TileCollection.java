package foocity.tile;

import java.util.*;
import foocity.tile.type.*;


/**
 * <p>
 * Collect and reference all tile instances
 * </p>
 *
 * <p>
 * This class contains a static reference to itself so that there can be a
 * global point of tile registration. However, for testing purposes standalone
 * instances can be generated so that subcollections can be used.
 * </p>
 *
 * <p>
 * The class singleton instance will also instantiate all basic tile types.
 * </p>
 */
public class TileCollection {
	
	private List<TileType> _types = new ArrayList<TileType>();
	private static TileCollection _self = new TileCollection().instantiateBaseTypes();
	
	/**
	 * @return The globally registered class instance
	 */
	static public TileCollection instance() {
		return _self;
	}

	/**
	 * <p>
	 * Search the registered TileTypes for a type with a matching name 
	 * representation and return it.
	 * </p>
	 *
	 * @param name The TileType to retrieve by name
	 */
	public TileType getByName(String name) {
		for(TileType current : _types) {
			if(current.getName().equals(name))
				return current;
		}
		return null;
	}
	
	/**
	 * <p>
	 * Search the registered TileTypes for a type with a matching character
	 * representation and return it.
	 * </p>
	 *
	 * @param symbol The TileType to retrieve, based on the character representation
	 */
	public TileType getByChar(char symbol){
		for(TileType current : _types) {
			if(current.getSymbol() == symbol)
				return current;
		}
		return null;
	}

	/**
	 * <p>
	 * Add a new TileType to the registered types
	 * </p>
	 *
	 * @param newType the new TileType instance to register.
	 */
	public void add(TileType newType) {
		_types.add(newType);
	}

	/**
	 * <p>
	 * Remove a TileType with a given name.
	 * </p>
	 *
	 * @param tileName the name of the TileType to delete
	 * @return True if the tile was found, else false
	 */
	public boolean remove(String tileName) {
		for(int i = 0; i < _types.size(); i++) {
			if(_types.get(i).getName().equals(tileName)) {
				_types.remove(i);
				return true;
			}
		}
		return false;
	}


	private TileCollection() {
		instantiateBaseTypes();
	}

	/**
	 * <p>
	 * Instantiate all the base TileTypes that come with the application.
	 * </p>
	 *
	 * @return The instantiated TileCollection - this facilitates operation chaining.
	 */
	private TileCollection instantiateBaseTypes() {

		_types.add(BeachTile.newType());
		_types.add(CoalPowerTile.newType());
		_types.add(CommercialTile.newType());
		_types.add(DirtTile.newType());
		_types.add(ForestTile.newType());
		_types.add(GrassTile.newType());
		_types.add(IndustrialTile.newType());
		_types.add(NaturalGasTile.newType());
		_types.add(ParkTile.newType());
		_types.add(PoliceStationTile.newType());
		_types.add(ResidentialTile.newType());
		_types.add(SolarPowerTile.newType());
		_types.add(WaterPlantTile.newType());
		_types.add(WaterTile.newType());
		_types.add(WindPowerTile.newType());

		return this;
	}
}

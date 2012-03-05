package foocity.tile;

import java.util.*;
import foocity.tile.type.*;


/**
 * Collect and reference all tile instances
 *
 * This class is a singleton instance meant to contain all tile types existing
 * in the system. It also serves to instantiate the basic tile types, and
 * provide a facility for adding new types.
 */
public class TileCollection {
	
	private List<TileType> _types = new ArrayList<TileType>();
	private static TileCollection _self = new TileCollection();
	
	static public TileType getByName(String name) {
		for(TileType current : _self._types) {
			if(current.getName().equals(name))
				return current;
		}
		return null;
	}
	
	static public TileType getByChar(char symbol){
		for(TileType current : _self._types) {
			if(current.getSymbol() == symbol)
				return current;
		}
		return null;
	}

	public void add(TileType newType) {
		_types.add(newType);
	}

	private TileCollection() {
		instantiateBaseTypes();
	}
	
	// This makes me feel like a bad person.
	private void instantiateBaseTypes() {

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
	}
}

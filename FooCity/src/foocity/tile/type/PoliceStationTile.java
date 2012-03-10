package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class PoliceStationTile {
	
	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 1000);
		attrs.put(TileAttribute.HAPPINESS, 2);
		attrs.put(TileAttribute.JOBS, 15);
		attrs.put(TileAttribute.CRIME, -10);
		attrs.put(TileAttribute.CONSUME_POWER, 15);
		attrs.put(TileAttribute.CONSUME_WATER, 10);
		
		return new TileType("PoliceStation", 'O', attrs);
	}
}

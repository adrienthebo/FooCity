package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class SolarPowerTile {
	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 2500);
		attrs.put(TileAttribute.HAPPINESS, -2);
		attrs.put(TileAttribute.JOBS, 10);
		attrs.put(TileAttribute.PRODUCE_POWER, 1000);

		return new TileType("SolarPower", 'S', attrs);
	}
}

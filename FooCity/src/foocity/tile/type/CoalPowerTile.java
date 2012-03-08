package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class CoalPowerTile {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 4000);
		attrs.put(TileAttribute.HAPPINESS, -10);
		attrs.put(TileAttribute.JOBS, 50);
		attrs.put(TileAttribute.POLLUTION, 10);
		attrs.put(TileAttribute.PRODUCE_POWER, 5000);
		attrs.put(TileAttribute.CONSUME_WATER, 25);

		return new TileType("CoalPower", 'S', attrs);
	}

}

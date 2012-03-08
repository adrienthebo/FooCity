package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class ParkTile {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 100);
		attrs.put(TileAttribute.HAPPINESS, 8);
		attrs.put(TileAttribute.POLLUTION, -3);
		attrs.put(TileAttribute.CONSUME_POWER, 10);
		attrs.put(TileAttribute.CONSUME_WATER, 5);

		return new TileType("Park", 'P', attrs);
	}
}

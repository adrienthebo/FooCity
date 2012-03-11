package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class ResidentialTile {
	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 250);
		attrs.put(TileAttribute.HAPPINESS, -100);
		attrs.put(TileAttribute.JOBS, 1);
		attrs.put(TileAttribute.CRIME, 1);
		attrs.put(TileAttribute.CONSUME_POWER, 30);
		attrs.put(TileAttribute.CONSUME_WATER, 15);

		return new TileType("Residential", 'R', attrs);
	}
}

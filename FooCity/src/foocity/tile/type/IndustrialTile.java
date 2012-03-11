package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class IndustrialTile {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 450);
		attrs.put(TileAttribute.HAPPINESS, -5);
		attrs.put(TileAttribute.JOBS, 75);
		attrs.put(TileAttribute.POLLUTION, 7);
		attrs.put(TileAttribute.CRIME, 7);
		attrs.put(TileAttribute.CONSUME_POWER, 75);
		attrs.put(TileAttribute.CONSUME_WATER, 35);

		return new TileType("Industrial", 'I', attrs);
	}
}

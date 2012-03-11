package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class CommercialTile {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 350);
		attrs.put(TileAttribute.HAPPINESS, 2);
		attrs.put(TileAttribute.JOBS, 35);
		attrs.put(TileAttribute.POLLUTION, 2);
		attrs.put(TileAttribute.CRIME, 3);
		attrs.put(TileAttribute.CONSUME_POWER, 35);
		attrs.put(TileAttribute.CONSUME_WATER, 25);

		return new TileType("Commercial", '$', attrs);
	}
}

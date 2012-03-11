package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.*;

public class BeachTile {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.HAPPINESS, 8);

		return new TileType("Beach", 'B', attrs);
	}
}

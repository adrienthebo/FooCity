package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class ForestTile {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 200);
		attrs.put(TileAttribute.HAPPINESS, 4);
		attrs.put(TileAttribute.POLLUTION, -5);

		return new TileType("Forest", 'T', attrs);
	}
}

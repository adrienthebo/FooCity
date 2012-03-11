package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class GrassTile {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 25);
		attrs.put(TileAttribute.HAPPINESS, 5);
		attrs.put(TileAttribute.POLLUTION, -2);

		return new TileType("Grass", 'G', attrs);
	}
}

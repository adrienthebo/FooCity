package foocity.tile.type;

import foocity.tile.TileType;
import foocity.tile.TileAttribute;
import java.util.*;

public class WaterTile {
	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 0); // XXX This has major concerns for purchasing.
		attrs.put(TileAttribute.HAPPINESS, 10);
		
		return new TileType("Water", '~', attrs);
	}
}

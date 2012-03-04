package foocity.tile.type;

import foocity.tile.TileType;
import foocity.tile.TileAttribute;
import java.util.*;

public class WindPowerTile {
	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 2500);
		attrs.put(TileAttribute.HAPPINESS, -3);
		attrs.put(TileAttribute.JOBS, 20);
		attrs.put(TileAttribute.PRODUCE_POWER, 1000);
		
		return new TileType("WindPower", 'X', attrs);
	}
}

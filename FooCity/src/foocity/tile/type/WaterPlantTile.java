package foocity.tile.type;

import foocity.tile.TileType;
import foocity.tile.TileAttribute;
import java.util.*;

public class WaterPlantTile {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 1000);
		attrs.put(TileAttribute.HAPPINESS, -8);
		attrs.put(TileAttribute.JOBS, 20);
		attrs.put(TileAttribute.CRIME, 2);
		attrs.put(TileAttribute.CONSUME_POWER, 35);
		attrs.put(TileAttribute.PRODUCE_WATER, 2500);

		return new TileType("WaterPlant", '~', attrs);
	}
}

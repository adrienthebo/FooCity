package foocity.tile.type;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class NaturalGasTile {
	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, 3500);
		attrs.put(TileAttribute.HAPPINESS, -4);
		attrs.put(TileAttribute.JOBS, 50);
		attrs.put(TileAttribute.POLLUTION, 3);
		attrs.put(TileAttribute.PRODUCE_POWER, 2500);
		attrs.put(TileAttribute.CONSUME_WATER, 10);
		
		return new TileType("NaturalGas", 'N', attrs);
	}
}

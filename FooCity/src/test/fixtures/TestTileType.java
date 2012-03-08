package test.fixtures;

import java.util.EnumMap;

import foocity.tile.TileAttribute;
import foocity.tile.TileType;

public class TestTileType {

	public static TileType newType() {
		EnumMap<TileAttribute, Integer> attrs = new EnumMap<TileAttribute, Integer>(TileAttribute.class);
		attrs.put(TileAttribute.PRICE, -100);
		attrs.put(TileAttribute.HAPPINESS, -200);
		attrs.put(TileAttribute.JOBS, -300);
		attrs.put(TileAttribute.POLLUTION, -400);
		attrs.put(TileAttribute.CRIME, -500);
		attrs.put(TileAttribute.HOUSING, -600);
		attrs.put(TileAttribute.CONSUME_POWER, -700);
		attrs.put(TileAttribute.PRODUCE_POWER, -800);
		attrs.put(TileAttribute.CONSUME_WATER, -900);
		attrs.put(TileAttribute.PRODUCE_WATER, -1000);

		return new TileType("TestType", '?', attrs);
	}
}

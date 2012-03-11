package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foocity.tile.*;

import test.fixtures.TileTypes;

public class TestTileCollection {

	@Test
	public void testDefaultTypes() {
		// Simple copypasta to ensure some basic level of sanity

		for(String name : TileTypes.defaultTypes) {
			assertNotNull(TileCollection.instance().getByName(name));
		}
	}

	@Test
	public void getInvalidTile() {
		assertNull(TileCollection.instance().getByName("ThisIsClearlyNotAValidTileNameUnlessSomeoneHasGoneOffTheDeepEnd"));
	}

	@Test
	public void testGetNames() {
		String[] names = TileCollection.instance().getNames();
		String[] expected = TileTypes.defaultTypes;

		assertArrayEquals(expected, names);
	}
}

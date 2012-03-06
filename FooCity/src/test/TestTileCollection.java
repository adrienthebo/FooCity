package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foocity.tile.*;

public class TestTileCollection {

	@Test
	public void testDefaultTypes() {
		// Simple copypasta to ensure some basic level of sanity
		assertNotNull(TileCollection.instance().getByName("Beach"));
		assertNotNull(TileCollection.instance().getByName("CoalPower"));
		assertNotNull(TileCollection.instance().getByName("Commercial"));
		assertNotNull(TileCollection.instance().getByName("Dirt"));
		assertNotNull(TileCollection.instance().getByName("Forest"));
		assertNotNull(TileCollection.instance().getByName("Grass"));
		assertNotNull(TileCollection.instance().getByName("Industrial"));
		assertNotNull(TileCollection.instance().getByName("NaturalGas"));
		assertNotNull(TileCollection.instance().getByName("Park"));
		assertNotNull(TileCollection.instance().getByName("PoliceStation"));
		assertNotNull(TileCollection.instance().getByName("Residential"));
		assertNotNull(TileCollection.instance().getByName("SolarPower"));
		assertNotNull(TileCollection.instance().getByName("WaterPlant"));
		assertNotNull(TileCollection.instance().getByName("Water"));
		assertNotNull(TileCollection.instance().getByName("WindPower"));
		
	}

	@Test
	public void getInvalidTile() {
		assertNull(TileCollection.instance().getByName("ThisIsClearlyNotAValidTileNameUnlessSomeoneHasGoneOffTheDeepEnd"));
	}
}

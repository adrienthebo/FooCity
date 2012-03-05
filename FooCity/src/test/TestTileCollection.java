package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foocity.tile.*;

public class TestTileCollection {

	@Test
	public void testDefaultTypes() {
		// Simple copypasta to ensure some basic level of sanity
		assertNotNull(TileCollection.getByName("Beach"));
		assertNotNull(TileCollection.getByName("CoalPower"));
		assertNotNull(TileCollection.getByName("Commercial"));
		assertNotNull(TileCollection.getByName("Dirt"));
		assertNotNull(TileCollection.getByName("Forest"));
		assertNotNull(TileCollection.getByName("Grass"));
		assertNotNull(TileCollection.getByName("Industrial"));
		assertNotNull(TileCollection.getByName("NaturalGas"));
		assertNotNull(TileCollection.getByName("Park"));
		assertNotNull(TileCollection.getByName("PoliceStation"));
		assertNotNull(TileCollection.getByName("Residential"));
		assertNotNull(TileCollection.getByName("SolarPower"));
		assertNotNull(TileCollection.getByName("WaterPlant"));
		assertNotNull(TileCollection.getByName("Water"));
		assertNotNull(TileCollection.getByName("WindPower"));
		
	}

	@Test
	public void getInvalidTile() {
		assertNull(TileCollection.getByName("ThisIsClearlyNotAValidTileNameUnlessSomeoneHasGoneOffTheDeepEnd"));
	}
}

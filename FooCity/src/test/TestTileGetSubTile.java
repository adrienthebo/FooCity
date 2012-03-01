package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foocity.tile.*;

public class TestTileGetSubTile {

	@Test
	public void getValidTile() {
		Class<Tile> grass = Tile.getSubTile("GrassTile");
		assertNotNull(grass);
	}
	
	@Test
	public void getInvalidTile() {
		Class<Tile> noSuchTile = Tile.getSubTile("ThisIsNotATile");
		assertNull(noSuchTile);
	}
}
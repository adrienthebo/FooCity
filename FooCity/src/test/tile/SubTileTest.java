package test.tile;

import static org.junit.Assert.*;

import org.junit.Test;

import foocity.tile.*;

public class SubTileTest {

	@Test
	public void getValidTile() {
		Class<Tile> grass = Tile.getSubTile("Grass");
		assertNotNull(grass);
	}
	
	@Test
	public void getInvalidTile() {
		Class<Tile> noSuchTile = Tile.getSubTile("ThisIsNotATile");
		assertNull(noSuchTile);
	}

}

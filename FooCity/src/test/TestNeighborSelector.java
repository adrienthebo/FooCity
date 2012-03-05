package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foocity.grid.*;
import foocity.tile.Tile;

public class TestNeighborSelector {

	Grid _grid = new Grid(test.fixtures.GridFixtures.smallGrid);
	
	@Test
	public void testGetRadiusOne() {
		Tile[] neighbors = NeighborSelector.get(_grid, 2, 2, 1);
		String[] tileNames = mapTilesToNames(neighbors);
		
		assertEquals(4, neighbors.length);
		
		String[] expected = {
				"Dirt",
				"Residential",
				"SolarPower",
				"Park",
		};

		for(int i = 0; i < expected.length; i++)
			assertEquals(expected[i], tileNames[i]);
	}
	
	@Test
	public void testGetRadiusTwo() {
		Tile[] neighbors = NeighborSelector.get(_grid, 2, 2, 2);
		String[] tileNames = mapTilesToNames(neighbors);
		
		assertEquals(12, neighbors.length);
		
		String[] expected = {
				"Beach",
				"Grass",
				"Dirt",
				"Beach",
				"Forest",
				"Residential",
				"SolarPower",
				"Dirt",
				"PoliceStation",
				"Park",
				"Park",
				"Grass",
		};

		for(int i = 0; i < expected.length; i++)
			assertEquals(expected[i], tileNames[i]);
	}
	
	@Test
	public void testRadiusMapEdge() {
		Tile[] neighbors = NeighborSelector.get(_grid, 0, 0, 2);
		String[] tileNames = mapTilesToNames(neighbors);
		
		String[] expected = {
				"Dirt",
				"Beach",
				"Grass",
				"Grass",
				"Forest",
		};
		
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], tileNames[i]);
		}
		
	}
	
	/*
	 *  Java doesn't have anything like a map function AFAIK, so we use 
	 *  and for loops. Map tiles to their names for easier comparison.
	 */
	private String[] mapTilesToNames(Tile[] tiles) {
		String[] tileNames = new String[tiles.length];

		for(int i = 0; i < tiles.length; i++)
			tileNames[i] = tiles[i].getType().getName();
		
		return tileNames;
	}
}

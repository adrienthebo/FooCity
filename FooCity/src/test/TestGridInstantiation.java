package test;

import test.fixtures.GridFixtures;

import static org.junit.Assert.*;

import org.junit.Test;

import foocity.grid.Grid;

public class TestGridInstantiation {
	
	private Grid _subject;
		
	@Test
	public void testBlankConstructor() {
		_subject = new Grid(10, 20);
		
		assertEquals(_subject.getXSize(), 10);
		assertEquals(_subject.getYSize(), 20);
	}
	
	@Test
	public void testArrayConstructor() {
		
		String[][] tiles = GridFixtures.tinyGrid;
		
		_subject = new Grid(tiles);
		
		// Assert that the size is right
		assertEquals(_subject.getXSize(), 3);
		assertEquals(_subject.getYSize(), 2);
		
		// Assert that the locations are right
		assertEquals("GrassTile", _subject.getTile(0,0).toString());
		assertEquals("DirtTile", _subject.getTile(0,1).toString());
		assertEquals("CommercialTile", _subject.getTile(1,0).toString());
		assertEquals("IndustrialTile", _subject.getTile(1,1).toString());
		assertEquals("WaterTile", _subject.getTile(2,0).toString());
		assertEquals("ForestTile", _subject.getTile(2,1).toString());
	}
}

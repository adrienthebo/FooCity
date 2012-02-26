package test;

import java.util.Iterator;

import test.fixtures.GridFixtures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import foocity.grid.Grid;
import foocity.tile.Tile;

public class TestGridIterator {
	
	private Grid _grid;
	private Iterator<Tile> _subject;
	
	@Before
	public void instantiate() {
		_grid = new Grid(GridFixtures.tiles);
		_subject = _grid.getIterator();
	}
	
	@Test
	public void testIteration() {
		assertEquals("GrassTile", _subject.next().unqualifiedClassName());
		assertEquals("DirtTile", _subject.next().unqualifiedClassName());
		assertEquals("CommercialTile", _subject.next().unqualifiedClassName());
		assertEquals("IndustrialTile", _subject.next().unqualifiedClassName());
		assertEquals("WaterTile", _subject.next().unqualifiedClassName());
		assertEquals("ForestTile", _subject.next().unqualifiedClassName());
		assertFalse(_subject.hasNext());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		_subject.remove();
	}
}
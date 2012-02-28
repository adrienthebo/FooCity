package test;

import java.util.Iterator;

import test.fixtures.GridFixtures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import foocity.grid.Grid;

public class TestGridIterator {
	
	private Grid _grid;
	private Iterator<String> _subject;
	
	@Before
	public void instantiate() {
		_grid = new Grid(GridFixtures.tinyGrid);
		_subject = _grid.getIterator();
	}
	
	@Test
	public void testIteration() {
		assertEquals("GrassTile", _subject.next());
		assertEquals("DirtTile", _subject.next());
		assertEquals("CommercialTile", _subject.next());
		assertEquals("IndustrialTile", _subject.next());
		assertEquals("WaterTile", _subject.next());
		assertEquals("ForestTile", _subject.next());
		assertFalse(_subject.hasNext());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		_subject.remove();
	}
}
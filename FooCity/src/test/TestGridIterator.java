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
		assertEquals("Grass", _subject.next());
		assertEquals("Dirt", _subject.next());
		assertEquals("Commercial", _subject.next());
		assertEquals("Industrial", _subject.next());
		assertEquals("Water", _subject.next());
		assertEquals("Forest", _subject.next());
		assertFalse(_subject.hasNext());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		_subject.remove();
	}
}
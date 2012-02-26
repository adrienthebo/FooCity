package test.grid;

import test.grid.fixtures.*;

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
		
		String[][] tiles = GridFixtures.tiles;
		
		_subject = new Grid(tiles);
		
		assertEquals(_subject.getXSize(), 3);
		assertEquals(_subject.getYSize(), 2);
	}
}

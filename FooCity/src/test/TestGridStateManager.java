package test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

import foocity.grid.*;
import test.fixtures.GridFixtures;

public class TestGridStateManager {

	private GridStateManager _subject;
	private Grid _grid;

	@Test
	public void testLoadFromStream() {
		_grid = new Grid(2, 3);
		_subject = new GridStateManager(_grid);

		try {
			Reader dummy = new StringReader(GridFixtures.tinySaveString);
			char[][] saveData = _subject.loadFromStream(dummy);
			assertArrayEquals(GridFixtures.tinyCharGrid, saveData);
		}
		catch(IOException e) {
			fail("Threw IOException " + e);
		}

	}

	@Test
	public void testSaveStream() {
		_grid = new Grid(GridFixtures.tinyCharGrid);
		_subject = new GridStateManager(_grid);

		StringWriter stream = new StringWriter();

		try {
			_subject.writeToStream(stream);
			assertEquals(GridFixtures.tinySaveString, stream.toString());
		}
		catch(IOException e) {
			fail("Threw IOException " + e);
		}
	}
}

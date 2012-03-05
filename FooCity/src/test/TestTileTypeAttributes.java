package test;

import test.fixtures.TestTileType;
import foocity.tile.*;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestTileTypeAttributes {

	private TileType _subject = TestTileType.newType();
	
	@Test
	public void testPrice() {
		assertEquals(-100, _subject.getAttribute(TileAttribute.PRICE));
	}
	
	@Test
	public void testHappiness() {
		assertEquals(-200, _subject.getAttribute(TileAttribute.HAPPINESS));
	}

	@Test
	public void testJobs() {
		assertEquals(-300, _subject.getAttribute(TileAttribute.JOBS));
	}

	@Test
	public void testPollution() {
		assertEquals(-400, _subject.getAttribute(TileAttribute.POLLUTION));
	}

	@Test
	public void testCrime() {
		assertEquals(-500, _subject.getAttribute(TileAttribute.CRIME));
	}
	
	@Test
	public void testHousing() {
		assertEquals(-600, _subject.getAttribute(TileAttribute.HOUSING));
	}
	
	@Test
	public void testProducePower() {
		assertEquals(-800, _subject.getAttribute(TileAttribute.PRODUCE_POWER));
	}
	
	@Test
	public void testConsumeWater() {
		assertEquals(-900, _subject.getAttribute(TileAttribute.CONSUME_WATER));
	}

	@Test
	public void testProduceWater() {
		assertEquals(-1000, _subject.getAttribute(TileAttribute.PRODUCE_WATER));
	}
	
}

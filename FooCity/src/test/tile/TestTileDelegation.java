package test.tile;

import test.tile.fixtures.TestTile;

import static org.junit.Assert.*;

import org.junit.Test;

import foocity.tile.Tile;

public class TestTileDelegation {

	private Tile _testObject = new TestTile();
	
	@Test
	public void testPowerConsumption() {
		assertEquals(_testObject.getConsumePower(), -100);
	}
	
	@Test
	public void testWaterConsumption() {
		assertEquals(_testObject.getConsumeWater(), -200);
	}
	
	@Test
	public void testCrime() {
		assertEquals(_testObject.getCrime(), -300);
	}
	
	@Test
	public void testHappiness() {
		assertEquals(_testObject.getHappiness(), -400);
	}
	
	@Test
	public void testHousing() {
		assertEquals(_testObject.getHousing(), -500);
	}
	
	@Test
	public void testJobs() {
		assertEquals(_testObject.getJobs(), -600);
	}

	@Test
	public void testPollution() {
		assertEquals(_testObject.getPollution(), -700);
	}
	
	@Test
	public void testPrice() {
		assertEquals(_testObject.getPrice(), -800);
	}
	
	@Test
	public void testProducePower() {
		assertEquals(_testObject.getProducePower(), -900);
	}

	@Test
	public void testProduceWater() {
		assertEquals(_testObject.getProduceWater(), -1000);
	}
}

package foocity.tile;

public class IndustrialTile extends Tile {
	public IndustrialTile() {
		_price = 450;
		_happiness = -5;
		_jobs = 75;
		_pollution = 7;
		_crime = 7;
		// no housing
		_consumePower = 75;
		// no produced power
		_consumeWater = 35;
		// no produced water
	}
}

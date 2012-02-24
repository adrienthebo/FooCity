package foocity.tile;

public class CommercialTile extends Tile {

	public CommercialTile() {
		_price = 350;
		_happiness = 2;
		_jobs = 35;
		_pollution = 2;
		_crime = 3;
		// no housing
		_consumePower = 35;
		// no produce power
		_consumeWater = 25;
		// no produce water
	}
}

package foocity.tile;

/** Class: Tile
 * 
 * Defines the general properties of tiles and centralizes tile functionality.
 */
public abstract class Tile {
	
	/* Default all values to zero so that we don't need to blank out unused
	 * fields everywhere else
	 */
	protected int _price = 0;
	protected int _happiness = 0;
	protected int _jobs = 0;
	protected int _pollution = 0;
	protected int _crime = 0;
	protected int _housing = 0;
	protected int _consumePower = 0;
	protected int _producePower = 0;
	protected int _consumeWater = 0;
	protected int _produceWater = 0;

	public int getPrice() {
		return _price;
	}

	public int getHappiness() {
		return _happiness;
	}

	public int getJobs() {
		return _jobs;
	}

	public int getPollution() {
		return _pollution;
	}

	public int getCrime() {
		return _crime;
	}

	public int getHousing() {
		return _housing;
	}

	public int getConsumePower() {
		return _consumePower;
	}

	public int getProducePower() {
		return _producePower;
	}

	public int getConsumeWater() {
		return _consumeWater;
	}

	public int getProduceWater() {
		return _produceWater;
	}
	
	/**
	 * <p>Attempts to load a tile class based on the passed in tile name</p>
	 * 
	 * <p>
	 * <em>Example:</em>
	 * </p>
	 * 
	 * <code>
	 *   Class&lt;Tile&gt; commTile = foocity.tile.Tile.getSubTile("Commercial");
	 * </code>
	 * 
	 * <hr />
	 * @param tileName
	 * @return the requested tile class if it exists, else null
	 */
	@SuppressWarnings("unchecked")
	public static Class<Tile> getSubTile(String tileName) {
		Class<Tile> holder = null;
		
		try {
			/*
			 * Build the full string so calling classes don't need to know
			 * the fill class name of the requested tile. 
			 */
			String tileClassName = "foocity.tile." + tileName + "Tile";
			holder = (Class<Tile>)Class.forName(tileClassName);
		}
		catch(ClassNotFoundException e) {
			/*
			 * XXX Ideally we should log this event, but there's no logging
			 * integrated into the application.
			 * 
			 * If this happens, an invalid tile was requested, so we're allowed
			 * to discard the error and return null.
			 */
		}
		return holder;
	}
}

package foocity.tile;

import java.util.*;

import foocity.population.Person;
import foocity.state.*;


/**
 * <p>
 * Holds the current state of this tile, with relation to the surrounding
 * tiles. This class is transient.
 * </p>
 */
public class TileState {

	private Tile _tile;
	protected Map<TileAttribute, Integer> _attributes = new EnumMap<TileAttribute, Integer>(TileAttribute.class);

	protected List<Person> _workers = new ArrayList<Person>();
	protected List<Person> _residents = new ArrayList<Person>();

	public TileState(Tile tile) {
		_tile = tile;
	}

	/**
	 * <p>
	 * Store the calculated attribute of this tile
	 * </p>
	 *
	 * @param attr The TileAttribute to store
	 * @param value The value of the attribute
	 */
	public void setAttribute(TileAttribute attr, int value) {
		_attributes.put(attr, value);
	}

	/**
	 * <p>
	 * Retrieve the calculated attribute of this tile.
	 * </p>
	 *
	 * @return The value of the requested attribute if it is set; else zero
	 */
	public int getAttribute(TileAttribute attr) {
		Integer val = _attributes.get(attr);
		if(val == null) {
			val = 0;
		}
		return val;
	}

	/**
	 * <p>
	 * Calculate the tilestate for this object
	 * </p>
	 *
	 * @param neighbors An array of neighboring tiles
	 */
	public void calculate(Tile[] neighbors) {
		for(Tile currentTile : neighbors) {
			add(currentTile.getType());
		}
	}

	/**
	 * <p>
	 * Merges the attributes of a tiletype with this state object
	 * </p>
	 */
	public void add(TileType type) {
		for(TileAttribute attr : TileAttribute.values()) {
			int value = getAttribute(attr);
			value += type.getAttribute(attr);
			_attributes.put(attr, value);
		}
	}

	/**
	 * <p>
	 * Wipes all attributes from the state.
	 * </p>
	 */
	public void clear() {
		_attributes.clear();
	}

	public void addResident(Person o) {
		if(_residents.size() < getAttribute(TileAttribute.HOUSING))
			_residents.add(o);
	}

	public void addWorker(Person o) {
		if(_workers.size() < getAttribute(TileAttribute.JOBS))
			_workers.add(o);
	}

	public void removeResident(Person o) {
		if(_residents.contains(o))
			_residents.remove(o);
	}

	public void removeWorker(Person o) {
		if(_workers.contains(o))
			_workers.remove(o);
	}

}

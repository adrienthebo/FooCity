package foocity.population;

import java.util.*;
import foocity.grid.*;
import foocity.tile.*;
public class Population {

	protected List<Person> _population = new ArrayList<Person>();
	protected Grid _grid;

	public void add() {
		Person newPerson = new Person();

		Tile[] homes = _grid.getTileTypes("Residential");
		Tile[] industrial = _grid.getTileTypes("Residential");

		for(Tile current : homes) {
			if(current.getState().addResident(newPerson))
				break;
		}

		for(Tile current : industrial) {
			if(current.getState().addResident(newPerson))
				break;
		}

		_population.add(newPerson);
	}

	public void remove(Person p) {
		p.kill();
		_population.remove(p);
	}
}

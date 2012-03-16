package foocity.population;

import java.util.*;
import foocity.grid.*;
import foocity.tile.*;
public class Population {

	protected List<Person> _population = new ArrayList<Person>();
	protected Grid _grid;

	public Population(Grid grid) {
		_grid = grid;
	}
	
	public void add() {
		Person newPerson = new Person(this);

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

	public void add(int count) {
		for(int i = 0; i < count; i++, add());
	}

	public void remove(Person p) {
		p.kill();
		_population.remove(p);

	}

	public void remove(int removeCount) {
		if(removeCount > _population.size()) {
			_population.clear();
		}
		else {
			while(removeCount-- > 0) {
				Person p = _population.get(removeCount);
				p.kill();
				_population.remove(p);
			}
		}
	}

	public Person[] getPeople() {
		Person[] meatSacks = new Person[_population.size()];
		for(int i = 0; i < _population.size(); meatSacks[i] = _population.get(i), i++);

		return meatSacks;
	}
	
	public int size() {
		return _population.size();
	}
}

package foocity.population;

import foocity.tile.*;

public class Person {

	private Tile _home;
	private Tile _work;
	private Population _population;

	public Person(Population p) {
		_population = p;
	}

	int _health = 100;
	int _income = 100;

	public int getIncome() {
		if(_work == null)
			return 0;
		else
			return _income;
	}

	public void setHome(Tile t) {
		_home = t;
	}

	public void setWork(Tile t) {
		_work = t;
	}

	public Tile getHome() {
		return _home;
	}

	public Tile getWork() {
		return _work;
	}

	public void kill() {
		if(_home != null) {
			_home.getState().removeResident(this);
		}
		if(_work != null) {
			_work.getState().removeWorker(this);
		}

	}
}

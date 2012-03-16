package foocity.population;

import java.awt.Point;
import foocity.tile.*;

public class Person {

	private Tile _home;
	private Tile _work;


	int _health = 100;
	int _income = 100;

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

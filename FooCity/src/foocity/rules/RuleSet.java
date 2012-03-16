package foocity.rules;

import java.util.*;
import foocity.state.*;
import foocity.tile.TileAttribute;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RuleSet implements PropertyChangeListener {

	private static RuleSet _self;

	public static void registerState(GameState g) {
		_self = new RuleSet(g);
	}

	public static RuleSet instance() {
		return _self;
	}

	private List<Rule> _rules = new ArrayList<Rule>();
	private GameState _state;
	Map<TileAttribute, Integer> _report;

	public RuleSet(GameState state) {
		_state = state;
		_state.getCalendar().addPropertyChangeListener(this);
	}

	public void addRule(Rule rule) {
		_rules.add(rule);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		_report = _state.getGrid().report();
		for(Rule rule : _rules) {
			rule.apply(_state);
		}
	}
}

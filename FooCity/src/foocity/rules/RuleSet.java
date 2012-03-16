package foocity.rules;
import foocity.rules.population.*;
import java.util.*;
import foocity.state.*;
import foocity.tile.TileAttribute;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RuleSet implements PropertyChangeListener {

	private static RuleSet _self;

	public static void registerState(GameState g) {
		_self = new RuleSet(g);
		_self.addRule(new Crime(_self));
		_self.addRule(new Pollution(_self));
		_self.addRule(new Immigration(_self));
		_self.addRule(new TaxCollection(_self));
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
		System.out.println("Population:" + _state.getPopulation().size());
	}
	
	public Map<TileAttribute, Integer> getReport() {
		return _report;
	}
}

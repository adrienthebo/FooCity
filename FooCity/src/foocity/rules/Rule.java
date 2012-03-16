package foocity.rules;

import foocity.state.GameState;

public abstract class Rule {

	protected RuleSet _ruleSet;

	public Rule(RuleSet ruleSet) {
		_ruleSet = ruleSet;
	}

	public abstract void apply(GameState g);
}

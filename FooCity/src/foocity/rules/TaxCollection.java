package foocity.rules;

import foocity.population.Person;
import foocity.state.GameState;

public class TaxCollection extends Rule {

	public TaxCollection(RuleSet ruleSet) {
		super(ruleSet);
	}

	@Override
	public void apply(GameState g) {
		int taxes = 0;
		taxes += getIncomeTaxes(g);

	}

	private int getIncomeTaxes(GameState g) {

		Person[] organBags = g.getPopulation().getPeople();

		int total = 0;
		for(Person p : organBags) {
			total += p.getIncome();
		}

		return total;
	}

}

package foocity.rules.population;
import foocity.rules.Rule;
import foocity.rules.RuleSet;
import foocity.state.*;
import foocity.tile.TileAttribute;
import java.lang.Math;
public class Immigration extends Rule {

	public Immigration(RuleSet ruleSet) {
		super(ruleSet);
	}

	public void apply(GameState g) {
		int happiness = _ruleSet.getReport().get(TileAttribute.HAPPINESS);

		int immigrants = (int)Math.log((double)happiness);
	}
}

package foocity.rules;
import foocity.state.*;
import foocity.tile.TileAttribute;
import java.lang.Math;
public class Crime extends Rule {

	public Crime(RuleSet ruleSet) {
		super(ruleSet);
	}

	public void apply(GameState g) {
		int crime = _ruleSet._report.get(TileAttribute.CRIME);

		int dead = (int)Math.log((double)crime);
	}
}


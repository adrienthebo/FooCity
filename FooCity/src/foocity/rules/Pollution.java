package foocity.rules;
import foocity.state.*;
import foocity.tile.TileAttribute;
import java.lang.Math;
public class Pollution extends Rule {

	public Pollution(RuleSet ruleSet) {
		super(ruleSet);
	}

	public void apply(GameState g) {
		int pollution = _ruleSet._report.get(TileAttribute.POLLUTION);

		int dead = (int)Math.log((double)pollution);
	}
}


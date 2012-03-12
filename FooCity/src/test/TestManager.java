package test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import foocity.checkpoint.*;
import foocity.state.*;
import foocity.grid.*;

public class TestManager {

	private GameState _gs;
	private Manager _subject;

	@Before
	public void instantiate() {
		_gs = new GameState(new Grid(2, 3), new TaxRates(), new GameCalendar(), 0);
		_subject = new Manager(_gs);
	}

	@Test
	public void testParseTaxRates() {
		try {
			Reader r = new StringReader("taxes 1 2 3 4");
			_subject.populate(r);

			assertEquals(1, _gs.getTaxRates().getPropertyTax());
			assertEquals(2, _gs.getTaxRates().getSalesTax());
			assertEquals(3, _gs.getTaxRates().getBusinessTax());
			assertEquals(4, _gs.getTaxRates().getIncomeTax());

		}
		catch(Exception e) {
			fail("Threw exception " + e);
		}
	}

	@Test
	public void testParseGameCalendar() {
		try {
			Reader r = new StringReader("gamecalendar 1970 12 31");
			_subject.populate(r);

			Calendar expected = new GregorianCalendar(1970, 12, 31);
			assertEquals(expected.get(Calendar.YEAR), _gs.getCalendar().getCalendar().get(Calendar.YEAR));
			assertEquals(expected.get(Calendar.MONTH), _gs.getCalendar().getCalendar().get(Calendar.MONTH));
			assertEquals(expected.get(Calendar.DAY_OF_MONTH), _gs.getCalendar().getCalendar().get(Calendar.DAY_OF_MONTH));
		}
		catch(Exception e) {
			fail("Threw exception " + e);
		}
	}

}

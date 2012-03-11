package test;

import static org.junit.Assert.*;
import org.junit.Test;

import foocity.state.TaxRates;

public class TestTaxRates {

	TaxRates _subject;

	@Test
	public void testEmptyConstructor() {
		_subject = new TaxRates();

		assertEquals(5, _subject.getPropertyTax());
		assertEquals(5, _subject.getSalesTax());
		assertEquals(5, _subject.getBusinessTax());
		assertEquals(5, _subject.getIncomeTax());
	}

	@Test
	public void testQualifiedConstructor() {
		_subject = new TaxRates(10, 20, 30, 40);

		assertEquals(10, _subject.getPropertyTax());
		assertEquals(20, _subject.getSalesTax());
		assertEquals(30, _subject.getBusinessTax());
		assertEquals(40, _subject.getIncomeTax());
	}

	@Test
	public void setSetValidPropertyTax() {
		_subject = new TaxRates();
		_subject.setPropertyTax(20);

		assertEquals(20, _subject.getPropertyTax());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setInvalidPropertyTax() {
		_subject = new TaxRates();
		_subject.setPropertyTax(-20);
		fail("Failed to throw exception in failure case");
	}

	@Test
	public void setSetValidBusinessTax() {
		_subject = new TaxRates();
		_subject.setBusinessTax(20);

		assertEquals(20, _subject.getBusinessTax());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setInvalidBusinessTax() {
		_subject = new TaxRates();
		_subject.setBusinessTax(-20);
		fail("Failed to throw exception in failure case");
	}

	@Test
	public void setSetValidSalesTax() {
		_subject = new TaxRates();
		_subject.setSalesTax(20);

		assertEquals(20, _subject.getSalesTax());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setInvalidSalesTax() {
		_subject = new TaxRates();
		_subject.setSalesTax(-20);
		fail("Failed to throw exception in failure case");
	}

	@Test
	public void setSetValidIncomeTax() {
		_subject = new TaxRates();
		_subject.setIncomeTax(20);

		assertEquals(20, _subject.getIncomeTax());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setInvalidIncomeTax() {
		_subject = new TaxRates();
		_subject.setIncomeTax(-20);
		fail("Failed to throw exception in failure case");
	}
}

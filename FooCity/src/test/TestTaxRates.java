package test;

import static org.junit.Assert.*;
import org.junit.Test;

import foocity.TaxRates;

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
}

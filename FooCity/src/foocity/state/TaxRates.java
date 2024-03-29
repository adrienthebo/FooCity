package foocity.state;

import foocity.event.*;
import java.beans.PropertyChangeListener;

import javax.swing.event.EventListenerList;

/**
 * Represents the current state of taxes. Requires that all tax rates must be
 * between 0 and 100, inclusive.
 */
public class TaxRates implements PropertyChangeGenerator {

	protected int _propertyTax;
	protected int _salesTax;
	protected int _businessTax;
	protected int _incomeTax;

	private EventListenerList _listeners = new EventListenerList();

	/**
	 * <p>
	 * Generates a default set of tax rates of 5%.
	 * </p>
	 *
	 * <pre>
	 * taxes = new TaxRates();
	 * </pre>
	 */
	public TaxRates() {
		_propertyTax = 5;
		_salesTax = 5;
		_businessTax = 5;
		_incomeTax = 5;
	}

	/**
	 * <p>
	 * Generates a TaxRates object with the specified inputs. All rates must be
	 * between 0 and 100.
	 * </p>
	 *
	 * <pre>
	 * rates = new TaxRates(10, 5, 10, 20);
	 *
	 * try {
	 *     invalidTaxRates = new TaxRates(-1, 2000, 2 ** 20000, 1e-1000);
	 * catch(IllegalArgumentException(e)) {
	 *     System.out.println("Invalid tax rate!");
	 * }
	 * </pre>
	 *
	 * @param propertyTax
	 * @param salesTax
	 * @param businessTax
	 * @param incomeTax
	 */
	public TaxRates(int propertyTax, int salesTax, int businessTax, int incomeTax) {
		setPropertyTax(propertyTax);
		setSalesTax(salesTax);
		setBusinessTax(businessTax);
		setIncomeTax(incomeTax);
	}

	public int getPropertyTax() {
		return _propertyTax;
	}

	public int getSalesTax() {
		return _salesTax;
	}

	public int getBusinessTax() {
		return _businessTax;
	}

	public int getIncomeTax() {
		return _incomeTax;
	}

	public void setPropertyTax(int tax) {
		if(validateRate(tax)) {
			_propertyTax = tax;
			EventGenerator.firePropertyChangeEvent(this, _listeners, "PropertyTax", _propertyTax, tax);
		}
		else
			throw mkException("PropertyTax", tax);
	}

	public void setSalesTax(int tax) {
		if(validateRate(tax)) {
			_salesTax = tax;
			EventGenerator.firePropertyChangeEvent(this, _listeners, "SalesTax", _salesTax, tax);
		}
		else
			throw mkException("SalesTax", tax);
	}

	public void setBusinessTax(int tax) {
		if(validateRate(tax)) {
			_businessTax = tax;
			EventGenerator.firePropertyChangeEvent(this, _listeners, "BusinessTax", _businessTax, tax);
		}
		else
			throw mkException("BusinessTax", tax);
	}

	public void setIncomeTax(int tax) {
		if(validateRate(tax)) {
			_incomeTax = tax;
			EventGenerator.firePropertyChangeEvent(this, _listeners, "IncomeTax", _incomeTax, tax);
		}
		else
			throw mkException("IncomeTax", tax);
	}

	/**
	 * <p>
	 * Update all tax rates at once.
	 * </p>
	 *
	 * @param propertyTax
	 * @param salesTax
	 * @param businessTax
	 * @param incomeTax
	 */
	public void setAll(int propertyTax, int salesTax, int businessTax, int incomeTax) {
		setPropertyTax(propertyTax);
		setSalesTax(salesTax);
		setBusinessTax(businessTax);
		setIncomeTax(incomeTax);
	}

	private boolean validateRate(int tax) {
		return (tax >= 0 && tax <= 100);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_listeners.add(PropertyChangeListener.class, listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_listeners.remove(PropertyChangeListener.class, listener);
	}

	/**
	 * Simplify generation of argument exceptions.
	 *
	 * @param name the name of the invalid tax rate
	 * @param rate The invalid rate
	 * @return
	 */
	private IllegalArgumentException mkException(String name, int rate) {
		return new IllegalArgumentException(name + " tax " + rate + " out of range (0,100) inclusive");
	}
}

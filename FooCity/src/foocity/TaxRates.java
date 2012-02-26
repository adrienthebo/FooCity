package foocity;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.EventListenerList;

public class TaxRates {
	
	protected int _propertyTax;
	protected int _salesTax;
	protected int _businessTax;
	protected int _incomeTax;
	
	private EventListenerList _listeners = new EventListenerList();
	
	public TaxRates() {
		
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
			fireEvent("PropertyTax", _propertyTax, tax);
			_propertyTax = tax;
		}
		else
			throw new IllegalArgumentException();
	}
	
	public void setSalesTax(int tax) {
		if(validateRate(tax)) {
			fireEvent("SalesTax", _salesTax, tax);
			_salesTax = tax;
		}
		else
			throw new IllegalArgumentException();
	}
	
	public void setBusinessTax(int tax) {
		if(validateRate(tax)) {
			fireEvent("BusinessTax", _businessTax, tax);
			_businessTax = tax;
		}
		else
			throw new IllegalArgumentException();
	}
	
	public void setIncomeTax(int tax) {
		if(validateRate(tax)) {
			fireEvent("IncomeTax", _incomeTax, tax);
			_incomeTax = tax;
		}
		else
			throw new IllegalArgumentException();
	}
	
	private boolean validateRate(int tax) {
		return (tax > 0 && tax < 100);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_listeners.add(PropertyChangeListener.class, listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_listeners.remove(PropertyChangeListener.class, listener);
	}
	
	protected void fireEvent(String property, int oldRate, int newRate) {
		PropertyChangeListener[] listeners = (PropertyChangeListener[])_listeners.getListenerList();
		
		PropertyChangeEvent event = new PropertyChangeEvent(this, property, oldRate, newRate);
		
		for(PropertyChangeListener listener : listeners) {
			listener.propertyChange(event);
		}
	}
}

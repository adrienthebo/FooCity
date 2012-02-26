package foocity;

public class TaxRates {
	
	protected int _propertyTax;
	protected int _salesTax;
	protected int _businessTax;
	protected int _incomeTax;
	
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
		if(validateRate(tax))
			_propertyTax = tax;
		else
			throw new IllegalArgumentException();
	}
	
	public void setSalesTax(int tax) {
		if(validateRate(tax))
			_salesTax = tax;
		else
			throw new IllegalArgumentException();
	}
	
	public void setBusinessTax(int tax) {
		if(validateRate(tax))
			_businessTax = tax;
		else
			throw new IllegalArgumentException();
	}
	
	public void setIncomeTax(int tax) {
		if(validateRate(tax)) {
			_incomeTax = tax;
		}
		else
			throw new IllegalArgumentException();
	}
	
	private boolean validateRate(int tax) {
		return (tax > 0 && tax < 100);
	}
}

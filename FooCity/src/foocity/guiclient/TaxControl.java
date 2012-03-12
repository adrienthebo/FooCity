package foocity.guiclient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import foocity.state.TaxRates;

/**
 * <p>
 * Implements a view into the Tax Rates. Subscribes to a TaxRates object,
 * and provides methods for raising and lowering them.
 * </p>
 */
public class TaxControl implements PropertyChangeListener {
	private JPanel taxPanel;
	
	private String[] taxNameLabels = { "Property", "Sales", "Business", "Income" };
	private JLabel[] taxRateLabels;
	
	private TaxRates taxRates;
	
	/**
	 * <p>
	 * Creates a new TaxControl object. Creates a new TaxRates object
	 * and registers itself as a subscriber.
	 * </p>
	 */
	public TaxControl()
	{
		taxRates = new TaxRates();
		taxRates.addPropertyChangeListener(this);
		createPanel();
		createPanelElements();
		updateTaxes();
	}
	
	/**
	 * <p>
	 * Returns the TaxRates object, for state loading, etc.
	 * </p>
	 * 
	 * @return TaxRates
	 */
	public TaxRates getTaxRates()
	{
		return taxRates;
	}
	
	/**
	 * <p>
	 * Returns a JPanel that displays and provides ways of changing taxes.
	 * </p>
	 * 
	 * @return JPanel
	 */
	public JPanel getTaxPanel()
	{
		return taxPanel;
	}
	
	/**
	 * Handler for PropertyChangeEvents--in our case, tax changes.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		updateTaxes();
	}

	// Create the initial JPanel
	private void createPanel()
	{
		taxPanel = new JPanel();
		taxPanel.setLayout(new GridLayout(taxNameLabels.length, 4, 10, 10));
	}
	
	// Populate the JPanel with tax-related elements
	private void createPanelElements()
	{
		taxRateLabels = new JLabel[taxNameLabels.length];
		
		for (int i = 0; i < taxNameLabels.length; i++)
		{
			final int _i = i;
			
			JLabel taxNameLabel = new JLabel(taxNameLabels[i]);
			JButton lowerTax = new JButton("lower");
			taxRateLabels[i] = new JLabel();
			JButton raiseTax = new JButton("raise");
			
			lowerTax.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeTax(taxNameLabels[_i], -1);
				}
			});

			raiseTax.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeTax(taxNameLabels[_i], 1);
				}
			});
			
			taxNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			taxRateLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			taxPanel.add(taxNameLabel);
			taxPanel.add(lowerTax);
			taxPanel.add(taxRateLabels[i]);
			taxPanel.add(raiseTax);
		}
	}
	
	// Given a tax and the increment of change, call the correct TaxRates method
	private void changeTax(String tax, int increment)
	{
		if (tax == "Property")
		{
			int rate = taxRates.getPropertyTax();
			taxRates.setPropertyTax(rate + increment);
		}
		else if (tax == "Sales")
		{
			int rate = taxRates.getSalesTax();
			taxRates.setSalesTax(rate + increment);
		}
		else if (tax == "Business")
		{
			int rate = taxRates.getBusinessTax();
			taxRates.setBusinessTax(rate + increment);
		}
		else if (tax == "Income")
		{
			int rate = taxRates.getIncomeTax();
			taxRates.setIncomeTax(rate + increment);
		}
	}
	
	// Refresh the view for all taxes
	private void updateTaxes()
	{
		for (int i = 0; i < taxNameLabels.length; i++)
			updateTaxLabel(taxNameLabels[i], taxRateLabels[i]);
	}
	
	// Update a specific tax in the view
	private void updateTaxLabel(String tax, JLabel label)
	{	
		int rate;
		
		if (tax == "Property")
			rate = taxRates.getPropertyTax();
		else if (tax == "Sales")
			rate = taxRates.getSalesTax();
		else if (tax == "Business")
			rate = taxRates.getBusinessTax();
		else if (tax == "Income")
			rate = taxRates.getIncomeTax();
		else
			return;
		
		label.setText(rate + "%");
	}
}

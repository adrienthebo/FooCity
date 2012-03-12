package foocity.guiclient;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import foocity.state.GameState;

/**
 * <p>
 * Implements a view into general metrics. Subscribes to a GameState object,
 * and provides methods for raising and lowering them.
 * </p>
 */
public class ReportView implements PropertyChangeListener {
	private JPanel reportPanel;
	
	private String[] reportNameLabels = { "Population", "Value", "Happiness", "Jobs", "Pollution", "Crime", "Housing", "Power Consumption", "Power Production", "Water Consumption", "Water Production" };
	private JLabel[] reportMetricLabels;
	
	private GameState gameState;
	
	/**
	 * <p>
	 * Creates a new ReportControl object. Subscribes to the supplied GameState object.
	 * </p>
	 * 
	 * @param gameState
	 */
	public ReportView(GameState gameState)
	{
		this.gameState = gameState; 
//		gameState.addPropertyChangeListener(this);
		createPanel();
		createPanelElements();
		updateReports();
	}
	
	/**
	 * <p>
	 * Returns a JPanel that displays reports.
	 * </p>
	 * 
	 * @return JPanel
	 */
	public JPanel getReportPanel()
	{
		return reportPanel;
	}
	
	/**
	 * Handler for PropertyChangeEvents--in our case, report changes.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		updateReports();
	}

	// Create the initial JPanel
	private void createPanel()
	{
		reportPanel = new JPanel();
		reportPanel.setLayout(new GridLayout(reportNameLabels.length, 2, 10, 10));
	}
	
	// Populate the JPanel with report-related elements
	private void createPanelElements()
	{
		reportMetricLabels = new JLabel[reportNameLabels.length];
		
		for (int i = 0; i < reportNameLabels.length; i++)
		{
			JLabel reportNameLabel = new JLabel(reportNameLabels[i]);
			reportMetricLabels[i] = new JLabel();
						
			reportNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			reportMetricLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			reportPanel.add(reportNameLabel);
			reportPanel.add(reportMetricLabels[i]);
		}
	}
	
	// Given a report and the increment of change, call the correct GameState method
	private void changeReport(String report, int increment)
	{
/*		if (report == "Property")
		{
			int rate = gameState.getPropertyReport();
			gameState.setPropertyReport(rate + increment);
		}
		else if (report == "Sales")
		{
			int rate = gameState.getSalesReport();
			gameState.setSalesReport(rate + increment);
		}
		else if (report == "Business")
		{
			int rate = gameState.getBusinessReport();
			gameState.setBusinessReport(rate + increment);
		}
		else if (report == "Income")
		{
			int rate = gameState.getIncomeReport();
			gameState.setIncomeReport(rate + increment);
		} */
	}
	
	// Refresh the view for all reports
	private void updateReports()
	{
		for (int i = 0; i < reportNameLabels.length; i++)
			updateReportLabel(reportNameLabels[i], reportMetricLabels[i]);
	}
	
	// Update a specific report in the view
	private void updateReportLabel(String report, JLabel label)
	{	
		int metric;
/*		
		if (report == "Property")
			metric = gameState.getPropertyReport();
		else if (report == "Sales")
			metric = gameState.getSalesReport();
		else if (report == "Business")
			metric = gameState.getBusinessReport();
		else if (report == "Income")
			metric = gameState.getIncomeReport();
		else
			return;
		
		label.setText(metric + "%");
*/
	}
}
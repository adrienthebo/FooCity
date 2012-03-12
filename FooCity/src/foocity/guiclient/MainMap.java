package foocity.guiclient;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;
import java.lang.Exception;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import foocity.state.GameCalendar;
import foocity.state.GameState;
import foocity.state.TaxRates;

import foocity.grid.Grid;
import foocity.checkpoint.GridStateManager;

import foocity.tile.TileCollection;

/**
 * <p>
 * The Main Application--centered around the large Map view.
 * Instantiates dialogs, etc, and game state.
 * The actual Map and Mini Map data lives in a Map object. 
 * </p>
 */
public class MainMap implements PropertyChangeListener
{
	// Initial Money
	private final int STARTING_MONEY = 0;
	
	// Size Constants--map grid size
	private final int WIDTH = 128;
	private final int HEIGHT = 128;
	
	// Size Constants--map grid
	private final int ICONSIZE = 36;
	private final int MINI_ICONSIZE = 3;
	
	// Model Elements
	private Grid grid;
	private GameCalendar gameCalendar;
	private TaxRates taxRates;
	private GameState gameState;
		
	// The main application frame
	private JFrame frmFoocity;
	// The Menu bar
	private JMenuBar menuBar;
	// The Toolbar
	private JToolBar toolBar;
	// Map Control
	private Map mapControl;
	// Tax Control
	private TaxControl taxControl;
	// Reports
	private ReportView reportView;
	
	// The date field at the bottom left of the screen
	private JLabel dateLabel;
	// The money field at the bottom center of the screen
	private JLabel moneyLabel;
	// The status field at the bottom right of the screen
	private JLabel statusLabel;
	
	// The New File... Chooser
	private JFileChooser newFileChooser; 
	// The Load File... Chooser
	private JFileChooser loadFileChooser;
	// The Save File... Chooser
	private JFileChooser saveFileChooser;
	
	// Report view window
	private JDialog reportWindow;
	// Mini map window
	private JDialog miniMapWindow;
	// Tax rate window
	private JDialog taxWindow;
	
	/**
	 * <p>
	 * Entry point for the application. From here we create visual elements and instantiate the model objects.
	 * </p>
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainMap window = new MainMap();
					window.frmFoocity.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Class constructor. Creates model objects and populates visual elements.
	 */
	public MainMap()
	{
		initializeModels();
		
		createMainFrame();

		createMenus();
		createToolBar();
		createMapView();
		createStatusBar();

		createFileDialogs();
		createDialogs();
		
		updateDateLabel();
		updateMoneyLabel();
		updateLabel(statusLabel, "Welcome to FooCity!");
	}
	
	/**
	 * <p>
	 * Implement PropertyChange handling
	 * </p> 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		updateDateLabel();
		updateMoneyLabel();
	}
	
	// Create initial Application frame.
	private void createMainFrame() {
		frmFoocity = new JFrame();
		frmFoocity.setTitle("Team 4 FooCity");
		frmFoocity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Create new, load, and save file chooser dialogs.
	private void createFileDialogs() {
		newFileChooser = new JFileChooser();
		loadFileChooser = new JFileChooser();
		saveFileChooser = new JFileChooser();
	}

	/**
	 * Initialize the model objects. mapData holds the actual map/grid
	 * representation, grid is passed back for loading, saving, and
	 * gameCalendar, taxRates, etc, are passed to gameState and
	 * given to TaxControl and ReportView objects (which populate dialogs).
	 * 
	 * Because this is where models are initialized, this is also were
	 * we subscribe to them.
	 */
	private void initializeModels()
	{
		mapControl = new Map(HEIGHT, WIDTH, ICONSIZE, MINI_ICONSIZE);
		taxControl = new TaxControl();
		gameCalendar = new GameCalendar();

		grid = mapControl.getGrid();
		taxRates = taxControl.getTaxRates();
				
		gameState = new GameState(grid, taxRates, gameCalendar, STARTING_MONEY);
		
		reportView = new ReportView(gameState);
		
		gameCalendar.addPropertyChangeListener(this);
	}
	
	// Creates menubar, and populates the individual menus.
	private void createMenus()
	{
		menuBar = new JMenuBar();
		frmFoocity.setJMenuBar(menuBar);
		
		createGameMenu();
		createChangeMenu();
		createViewMenu();
	}
	
	// The game menu is mostly concerned with game state (loading, etc) and exiting.
	private void createGameMenu()
	{
		JMenu menuGame = new JMenu("Game");
		menuBar.add(menuGame);
		
		JMenuItem menuGameNew = new JMenuItem("New...");		
		JMenuItem menuGameLoad = new JMenuItem("Load...");
		JMenuItem menuGameSave = new JMenuItem("Save...");
		JSeparator menuGameSeparatorA = new JSeparator();
		JMenuItem menuGameAbout = new JMenuItem("About...");
		JSeparator menuGameSeparatorB = new JSeparator();
		JMenuItem menuGameExit = new JMenuItem("Exit");

		menuGameNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int newFile = newFileChooser.showOpenDialog(frmFoocity);

				if (newFile == JFileChooser.APPROVE_OPTION) {
		            loadFile(newFileChooser.getSelectedFile());
		        }
			}
		});
		
		menuGameLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int loadFile = loadFileChooser.showOpenDialog(frmFoocity);

				if (loadFile == JFileChooser.APPROVE_OPTION) {
		            loadFile(loadFileChooser.getSelectedFile());
		        }
			}
		});
		
		menuGameSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int saveFile = saveFileChooser.showSaveDialog(frmFoocity);

				if (saveFile == JFileChooser.APPROVE_OPTION) {
		            saveFile(saveFileChooser.getSelectedFile());
		        }
			}
		});

		menuGameAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyUser("About FooCity", "CS300 Team 4 FooCity\nThebo / Chen\nWinter 2012");
			}
		});
		
		menuGameExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuGame.add(menuGameNew);
		menuGame.add(menuGameLoad);
		menuGame.add(menuGameSave);
		menuGame.add(menuGameSeparatorA);
		menuGame.add(menuGameAbout);
		menuGame.add(menuGameSeparatorB);
		menuGame.add(menuGameExit);
	}
	
	/**
	 *  The change menu is concerned with modifying other aspects of the
	 *  game, like tax rates, passage of time, basically anything that isn't
	 *  strictly grid related.
	 */
	private void createChangeMenu()
	{
		JMenu menuChange = new JMenu("Change");
		menuBar.add(menuChange);
		
		final JCheckBoxMenuItem menuChangeContinuousTime = new JCheckBoxMenuItem("Continuous Time");
		final JMenuItem menuChangeStepTimeForward = new JMenuItem("Step Time Forward");
		JSeparator menuChangeSeparator = new JSeparator();
		final JCheckBoxMenuItem menuChangeTaxRates = new JCheckBoxMenuItem("TaxRates");

		
		// Enable or disable the stepTimeForward menu item depending on
		// the menu item's setting.
		menuChangeContinuousTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menuChangeContinuousTime.getState() == true) {
					menuChangeStepTimeForward.setEnabled(false);
				}
				else {
					menuChangeStepTimeForward.setEnabled(true);
				}
			}
		});

		// Step time forward
		menuChangeStepTimeForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameCalendar.increment();
			}
		});
		
		menuChangeTaxRates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showView(taxWindow, menuChangeTaxRates.getState());
			}
		});
		
		menuChange.add(menuChangeContinuousTime);
		menuChange.add(menuChangeStepTimeForward);
		menuChange.add(menuChangeSeparator);
		menuChange.add(menuChangeTaxRates);
	}
	
	// The view menu handles the mini map and report views--basically read-only elements.
	private void createViewMenu()
	{	
		JMenu menuView = new JMenu("View");
		menuBar.add(menuView);
		
		final JCheckBoxMenuItem menuViewReports = new JCheckBoxMenuItem("Reports...");
		final JCheckBoxMenuItem menuViewMiniMap = new JCheckBoxMenuItem("Mini Map...");

		menuViewReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showView(reportWindow, menuViewReports.getState());
			}
		});
		
		menuViewMiniMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showView(miniMapWindow, menuViewMiniMap.getState());
			}
		});
		
		menuView.add(menuViewReports);
		menuView.add(menuViewMiniMap);
	}
	
	// The toolbar lets the user select tile types for map placement.
	private void createToolBar()
	{
		toolBar = new JToolBar();
		frmFoocity.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		// Get a list of all the tile types from TileCollection
		String[] tileTypes = TileCollection.instance().getNames();
				
		for (int i = 0; i < tileTypes.length ; i++)
			toolBar.add(createToolButton(tileTypes[i]));
	}
		
	// This creates the scroll pane and panel to hold the main map.
	private void createMapView()
	{
		JScrollPane mapPane = new JScrollPane();
		frmFoocity.getContentPane().add(mapPane, BorderLayout.CENTER);
		
		JPanel mapPanel = mapControl.largeMap();
		mapPane.setViewportView(mapPanel);
	}

	// Here we create and populate the mini, report, and tax rates dialogs.
	private void createDialogs()
	{
		// Mini Map
		miniMapWindow = new JDialog(frmFoocity, false);
		miniMapWindow.setTitle("Mini Map");
		miniMapWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JScrollPane mapPane = new JScrollPane();
		miniMapWindow.getContentPane().add(mapPane, BorderLayout.CENTER);
		
		JPanel miniMapPanel = mapControl.miniMap();
		mapPane.setViewportView(miniMapPanel);
		miniMapWindow.pack();

		// Reports
		reportWindow = new JDialog(frmFoocity, false);
		reportWindow.setTitle("Reports");
		reportWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		

		JScrollPane reportPane = new JScrollPane();
		reportWindow.getContentPane().add(reportPane, BorderLayout.CENTER);
		
		JPanel reportPanel = reportView.getReportPanel();
		reportPane.setViewportView(reportPanel);
		reportWindow.pack();
		
		// Tax Rates
		taxWindow = new JDialog(frmFoocity, false);
		taxWindow.setTitle("Tax Rates");
		taxWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		

		JScrollPane taxPane = new JScrollPane();
		taxWindow.getContentPane().add(taxPane, BorderLayout.CENTER);
		
		JPanel taxPanel = taxControl.getTaxPanel();
		taxPane.setViewportView(taxPanel);
		taxWindow.pack();
	}
	
	// The status bar is for notifying the user of non-critical events.
	private void createStatusBar()
	{
		JPanel statusPanel = new JPanel();
		frmFoocity.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		dateLabel = new JLabel();
		moneyLabel = new JLabel();
		statusLabel = new JLabel();
		
		statusPanel.add(dateLabel);
		statusPanel.add(moneyLabel);
		statusPanel.add(statusLabel);
	}
	
	// This creates each tool button for the toolbar.
	private JToggleButton createToolButton(final String tileType)
	{
		JToggleButton newButton = new JToggleButton();
		newButton.setName(tileType);
		newButton.setToolTipText(tileType);
		newButton.setIcon(mapControl.getIcon(tileType));
		newButton.setPreferredSize(new Dimension(ICONSIZE, ICONSIZE));

		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleDesiredTile(tileType);
			}
		});		
		return newButton;
	}
	
	/**
	 * This is a bass-ackwards way of implementing radio buttons with toggle buttons.
	 * Basically, this changes what happens when the user selects a map element. If
	 * none of the toolbar buttons are selected, desiredTile is set to null and the
	 * click becomes a no-op. If a button is selected, any other buttons are
	 * unselected and the tileType is now registered as the desiredTile.
	 */
	private void toggleDesiredTile(String tileType)
	{
		int num_tools = toolBar.getComponentCount();
		for (int i = 0; i < num_tools ; i++) {
			JToggleButton tool = (JToggleButton) toolBar.getComponent(i);
			if (tool.getName() == tileType) { // This is the button just pressed
				if (tool.isSelected() == true) { // Button selected?
					mapControl.setDesiredTile(tileType);
					updateLabel(statusLabel, "Tile placement mode, type: " + tileType);
				}
				else { // Button was just unselected
					mapControl.setDesiredTile(null);
					updateLabel(statusLabel, "Exiting tile placement mode");
				}
			}
			else { // Unset all other buttons
				tool.setSelected(false);
			}
		}
	}
	
	// Used by menu elements to "make visible" the report, minimap, and taxrates dialogs.
	private void showView(JDialog view, boolean setting)
	{
		if (view.isVisible() != setting)
			view.setVisible(setting);
	}
	
	// This updates the text in the status bar.
	private void updateLabel(JLabel label, String message)
	{
		label.setText(message);
	}
	
	// This creates a new alert message dialog for critical events.
	private void alertUser(String title, String message)
	{
		JOptionPane.showMessageDialog(frmFoocity, message, title, JOptionPane.WARNING_MESSAGE);
	}

	// This creates a new notification message dialog for semi-critical events.
	private void notifyUser(String title, String message)
	{
		JOptionPane.showMessageDialog(frmFoocity, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Loads a requested save game file.
	private void loadFile(File fileToLoad)
	{
		GridStateManager manager = new GridStateManager(grid);
		String filePath = fileToLoad.getAbsolutePath();
		
		if (manager.load(filePath)) {
			mapControl.updateMap();
			updateLabel(statusLabel, "Loaded file: " + filePath);
		}
	}
	
	// Saves current game state to a file.
	private void saveFile(File fileToSave)
	{
		GridStateManager manager = new GridStateManager(grid);
		String filePath = fileToSave.getAbsolutePath();
		
		if (manager.save(filePath))
			updateLabel(statusLabel, "Saved to file: " + filePath);
	}
	
	private void updateDateLabel() {
		updateLabel(dateLabel, "Date: " + gameCalendar.toString());
	}
	
	private void updateMoneyLabel() {
		updateLabel(moneyLabel, "Current Funds: $" + gameState.getFunds());
	}
}
package foocity.guiclient;
import java.awt.Dimension;
import java.awt.EventQueue;

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

import foocity.tile.TileCollection;

public class MainMap
{
	// Path Constants--will remove
	private String TERRAINPATH = "/home/chchen/git/FooCity/contrib/terrain/";

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
	// LargeMap Data
	private Map mapData;
	// The map panel
	private JPanel mapPanel;
	// The map panel
	private JPanel miniMapPanel;
	// The status bar at the bottom of the screen
	private JLabel statusBar;
	
	// The New File... Chooser
	private JFileChooser newFileChooser; 
	// The Load File... Chooser
	private JFileChooser loadFileChooser;
	// The Save File... Chooser
	private JFileChooser saveFileChooser;
	
	// Report view window
	private JDialog reportView;
	// Mini map window
	private JDialog miniMapView;
	// Tax rate window
	private JDialog taxRateView;
	
	/**
	 * Launch the application.
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
	 * Create the application.
	 */
	public MainMap()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		initializeModels();
		
		frmFoocity = new JFrame();
		frmFoocity.setTitle("FooCity");
		frmFoocity.setBounds(100, 100, 450, 300);
		frmFoocity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frmFoocity.setJMenuBar(menuBar);
		
		createFileDialogs();
		createMenus();
		createToolbar();
		createMapView();
		createStatusBar();
		createDialogs();
		
		updateStatus("Welcome to FooCity!");
	}
	
	private void createFileDialogs() {
		newFileChooser = new JFileChooser();
		loadFileChooser = new JFileChooser();
		saveFileChooser = new JFileChooser();
	}

	private void initializeModels()
	{
		mapData = new Map(HEIGHT, WIDTH, ICONSIZE, MINI_ICONSIZE);
	}
	
	// Methods to create larger graphical elements
	private void createMenus()
	{
		createGameMenu();
		createChangeMenu();
		createViewMenu();
	}
	
	private void createGameMenu()
	{
		JMenu menuGame = new JMenu("Game");
		menuBar.add(menuGame);
		
		JMenuItem menuGameNew = new JMenuItem("New...");		
		JMenuItem menuGameLoad = new JMenuItem("Load...");
		JMenuItem menuGameSave = new JMenuItem("Save...");
		JSeparator menuGameSeparator = new JSeparator();
		JMenuItem menuGameExit = new JMenuItem("Exit");

		menuGameNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int newFile = newFileChooser.showOpenDialog(frmFoocity);

				if (newFile == JFileChooser.APPROVE_OPTION) {
		            mapData.loadMap(newFileChooser.getSelectedFile());
		        }
			}
		});
		menuGameLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int loadFile = loadFileChooser.showOpenDialog(frmFoocity);

				if (loadFile == JFileChooser.APPROVE_OPTION) {
		            mapData.loadMap(loadFileChooser.getSelectedFile());
		        }
			}
		});
		menuGameSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int saveFile = saveFileChooser.showSaveDialog(frmFoocity);

				if (saveFile == JFileChooser.APPROVE_OPTION) {
		            mapData.saveMap(saveFileChooser.getSelectedFile());
		        }
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
		menuGame.add(menuGameSeparator);
		menuGame.add(menuGameExit);
	}
	
	private void createChangeMenu()
	{
		JMenu menuChange = new JMenu("Change");
		menuBar.add(menuChange);
		
		final JCheckBoxMenuItem menuChangeContinuousTime = new JCheckBoxMenuItem("Continuous Time");
		final JMenuItem menuChangeStepTimeForward = new JMenuItem("Step Time Forward");
		JSeparator menuChangeSeparator = new JSeparator();
		JMenuItem menuChangeTaxRates = new JMenuItem("Tax Rates...");
		
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
		menuChangeTaxRates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showView(taxRateView, true);
			}
		});

		
		menuChange.add(menuChangeContinuousTime);
		menuChange.add(menuChangeStepTimeForward);
		menuChange.add(menuChangeSeparator);
		menuChange.add(menuChangeTaxRates);
	}
	
	private void createViewMenu()
	{	
		JMenu menuView = new JMenu("View");
		menuBar.add(menuView);
		
		final JCheckBoxMenuItem menuViewReports = new JCheckBoxMenuItem("Reports...");
		menuViewReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showView(reportView, menuViewReports.getState());
			}
		});
		
		final JCheckBoxMenuItem menuViewMiniMap = new JCheckBoxMenuItem("Mini Map...");
		menuViewMiniMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showView(miniMapView, menuViewMiniMap.getState());
			}
		});
		
		menuView.add(menuViewReports);
		menuView.add(menuViewMiniMap);
	}
	
	private void createToolbar()
	{
		toolBar = new JToolBar();
		frmFoocity.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		String[] tileTypes = TileCollection.instance().getNames();
				
		for (int i = 0; i < tileTypes.length ; i++)
			toolBar.add(createToolButton(tileTypes[i]));
	}
		
	private void createMapView()
	{
		JScrollPane mapPane = new JScrollPane();
		frmFoocity.getContentPane().add(mapPane, BorderLayout.CENTER);
		
		mapPanel = mapData.largeMap();
		mapPane.setViewportView(mapPanel);
	}

	private void createDialogs()
	{
		// Mini Map
		miniMapView = new JDialog(frmFoocity, false);
		miniMapView.setTitle("Mini Map");
		miniMapView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JScrollPane mapPane = new JScrollPane();
		miniMapView.getContentPane().add(mapPane, BorderLayout.CENTER);
		
		miniMapPanel = mapData.miniMap();
		mapPane.setViewportView(miniMapPanel);
		miniMapView.pack();

		// Reports
		reportView = new JDialog(frmFoocity, false);
		reportView.setTitle("Reports");
		reportView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		reportView.pack();
		
		// Tax Rates
		taxRateView = new JDialog(frmFoocity, false);
		taxRateView.setTitle("Tax Rates");
		taxRateView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
		taxRateView.pack();
	}
	
	private void createStatusBar()
	{
		JPanel statusPanel = new JPanel();
		frmFoocity.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		statusBar = new JLabel();
		statusPanel.add(statusBar);
	}
	
	// Methods to create smaller elements (buttons)
	private JToggleButton createToolButton(final String tileType)
	{
		JToggleButton newButton = new JToggleButton();
		newButton.setName(tileType);
		newButton.setToolTipText(tileType);
		newButton.setIcon(mapData.getIcon(tileType));
		newButton.setPreferredSize(new Dimension(ICONSIZE, ICONSIZE));

		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleDesiredTile(tileType);
			}
		});		
		return newButton;
	}
	
	private void toggleDesiredTile(String tileType)
	{
		int num_tools = toolBar.getComponentCount();
		for (int i = 0; i < num_tools ; i++) {
			JToggleButton tool = (JToggleButton) toolBar.getComponent(i);
			if (tool.getName() == tileType) { // This is the button just pressed
				if (tool.isSelected() == true) { // Button selected?
					mapData.setDesiredTile(tileType);
					updateStatus("Tile placement mode, type: " + tileType);
				}
				else { // Button was just unselected
					mapData.setDesiredTile(null);
					updateStatus("Exiting tile placement mode");
				}
			}
			else { // Unset all other buttons
				tool.setSelected(false);
			}
		}
	}
	
	private void showView(JDialog view, boolean setting)
	{
		if (view.isVisible() != setting)
			view.setVisible(setting);
	}
	
	private void updateStatus(String newStatus)
	{
		statusBar.setText(newStatus);
	}
	
	private void alertUser(String title, String message)
	{
		JOptionPane.showMessageDialog(frmFoocity, message, title, JOptionPane.WARNING_MESSAGE);
	}
}
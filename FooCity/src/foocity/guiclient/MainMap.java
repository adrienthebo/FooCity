package foocity.guiclient;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.lang.Exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

import foocity.GameCalendar;
import foocity.GameState;
import foocity.StateManager;
import foocity.TaxRates;
import foocity.grid.Grid;

public class MainMap
{
	// Path Constants--will remove
	private String TERRAINPATH = "/home/chchen/git/FooCity/contrib/terrain/";

	// Size Constants--map grid size
	private final int WIDTH = 128;
	private final int HEIGHT = 128;
	
	// Model Elements
	private Grid grid;
	private GameCalendar gameCalendar;
	private TaxRates taxRates;
	private GameState gameState;
	
	// How we modify the model
	private Controller controller;
	
	// The main application frame
	private JFrame frmFoocity;
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
	// The report view window
	private JDialog reportView;
	// The mini map window
	private JDialog miniMapView;

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
		
		JMenuBar menuBar = new JMenuBar();
		frmFoocity.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Game");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New...");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Load...");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save...");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Change");
		menuBar.add(mnNewMenu_1);
		
		final JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Continuous Time");
		final JMenuItem mntmNewMenuItem_5 = new JMenuItem("Step Time Forward");
		
		chckbxmntmNewCheckItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxmntmNewCheckItem.getState() == true) {
					mntmNewMenuItem_5.setEnabled(false);
				}
				else {
					mntmNewMenuItem_5.setEnabled(true);
				}
			}
		});

		mnNewMenu_1.add(chckbxmntmNewCheckItem);
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu_1.add(separator_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Tax Rates...");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("View");
		menuBar.add(mnNewMenu_2);
		
		final JCheckBoxMenuItem mntmNewMenuItem_6 = new JCheckBoxMenuItem("Reports...");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showReportView(mntmNewMenuItem_6.getState());
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);

		final JCheckBoxMenuItem mntmNewMenuItem_7 = new JCheckBoxMenuItem("Mini Map...");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMiniMapView(mntmNewMenuItem_7.getState());
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_3 = new JMenu("Testing");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Load Map 1");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				loadMapFile(TERRAINPATH + "001.txt");
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Load Map 2");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				loadMapFile(TERRAINPATH + "002.txt");
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);
		
		createToolbar();
		createMapView();
		createStatusBar();
		createReportView();
		createMiniMapView();
		
		updateStatus("Welcome to FooCity!");
	}
	
	private void initializeModels()
	{
		mapData = new Map(HEIGHT, WIDTH);
	}
	
	private void createToolbar()
	{
		toolBar = new JToolBar();
		frmFoocity.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(createToolButton("Dirt", "Dirt"));
		toolBar.add(createToolButton("Tree", "Forest"));
		toolBar.add(createToolButton("Water", "Water"));
	}
	
	private JToggleButton createToolButton(final String toolName, final String tileType)
	{
		JToggleButton newButton = new JToggleButton(toolName);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectDesiredTile(toolName, tileType);
			}
		});		
		return newButton;
	}
	
	private void selectDesiredTile(String toolName, String tileType)
	{
		int num_tools = toolBar.getComponentCount();
		for (int i = 0; i < num_tools ; i++) {
			JToggleButton tool = (JToggleButton) toolBar.getComponent(i);
			if (tool.getText() == toolName) {
				if (tool.isSelected() == true) {
					mapData.setDesiredTile(tileType);
					updateStatus("Tile placement mode, type: " + toolName);
				}
				else {
					mapData.setDesiredTile(null);
					updateStatus("Exiting tile placement mode");
				}
			}
			else {
				tool.setSelected(false);
			}
		}
	}
	
	private void createMapView()
	{
		JScrollPane mapPane = new JScrollPane();
		frmFoocity.getContentPane().add(mapPane, BorderLayout.CENTER);
		
		mapPanel = mapData.largeMap();
		mapPane.setViewportView(mapPanel);
	}

	private void createMiniMapView()
	{
		miniMapView = new JDialog(frmFoocity, false);
		miniMapView.setTitle("Mini Map");
		miniMapView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JScrollPane mapPane = new JScrollPane();
		miniMapView.getContentPane().add(mapPane, BorderLayout.CENTER);
		
		miniMapPanel = mapData.miniMap();
		mapPane.setViewportView(miniMapPanel);
		miniMapView.pack();
	}
	
	private void showMiniMapView(boolean setting)
	{
		if (miniMapView.isVisible() != setting)
			miniMapView.setVisible(setting);
	}
	
	private void createReportView()
	{
		reportView = new JDialog(frmFoocity, false);
		reportView.setTitle("Reports");
		reportView.setBounds(100, 100, 450, 300);
		reportView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	private void showReportView(boolean setting)
	{
		if (reportView.isVisible() != setting)
			reportView.setVisible(setting);
	}
	
	private void createStatusBar()
	{
		JPanel statusPanel = new JPanel();
		frmFoocity.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		statusBar = new JLabel();
		statusPanel.add(statusBar);
	}
	
	private void updateStatus(String newStatus)
	{
		statusBar.setText(newStatus);
	}
	
	private void alertUser(String message)
	{
		JOptionPane.showMessageDialog(frmFoocity, message, "Whoops", JOptionPane.WARNING_MESSAGE);
	}
}

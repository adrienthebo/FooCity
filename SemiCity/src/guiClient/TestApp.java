package guiClient;
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

public class TestApp
{
	// Path Constants--will remove
	private String TERRAINPATH = "/home/cchen/git/FooCity/contrib/terrain/";
	private String TILEPATH = "/home/cchen/git/FooCity/contrib/foo-tiles/";

	// Size Constants--map grid, icon size
	private final int WIDTH = 128;
	private final int HEIGHT = 128;
	private final int ICONSIZE = 36;
	private final int ICONSIZE_MINI = 8;
	
	// Map is held as a 2-d array of char
	private char[][] mapGrid;
	// The actual buttons displayed to the user
	private JButton[][] mapButtons;	
	// The main application frame
	private JFrame frmFoocity;
	// The Toolbar
	private JToolBar toolBar;
	// The map panel
	private JPanel mapPanel;
	// The status bar at the bottom of the screen
	private JLabel statusBar;
	// The currently chosen tool (for tile placement)
	private char chosenTool;

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
					TestApp window = new TestApp();
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
	public TestApp()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
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
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Reports...");
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Mini Map...");
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_3 = new JMenu("Testing");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Load Map 1");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadMapFile(TERRAINPATH + "001.txt");
				refreshMap();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Load Map 2");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadMapFile(TERRAINPATH + "002.txt");
				refreshMap();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);
		
		createToolbar();
		createMapView();
		createStatusBar();
		
		updateStatus("Welcome to FooCity!");
	}
	
	private void createToolbar()
	{
		toolBar = new JToolBar();
		frmFoocity.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(createToolButton("Commercial", 'C'));
		toolBar.add(createToolButton("Industrial", 'I'));
		toolBar.add(createToolButton("Residential", 'R'));
	}
	
	private JToggleButton createToolButton(final String toolName, final char tileType)
	{
		JToggleButton newButton = new JToggleButton(toolName);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectTool(toolName, tileType);
			}
		});		
		return newButton;
	}
	
	private void selectTool(String toolName, char tileType)
	{
		int num_tools = toolBar.getComponentCount();
		for (int i = 0; i < num_tools ; i++) {
			JToggleButton tool = (JToggleButton) toolBar.getComponent(i);
			if (tool.getText() == toolName) {
				if (tool.isSelected() == true) {
					setChosenTool(tileType);
					updateStatus("Tile placement mode, type: " + toolName);
				}
				else {
					setChosenTool('\0');
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

		mapPanel = new JPanel();
		mapPane.setViewportView(mapPanel);
		mapPanel.setLayout(new GridLayout(128, 128, 0, 0));

		mapGrid = new char[HEIGHT][WIDTH];
		mapButtons = new JButton[HEIGHT][WIDTH];

		initializeMap();
		refreshMap();
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
 	
	private ImageIcon tileImage(char tileType)
	{
		String tileFile;
		
		switch(tileType) {
			case 'B':	tileFile = "beach.png";
						break;
			case 'D':	tileFile = "dirt.png";
						break;
			case 'G':	tileFile = "grass.png";
						break;
			case 'T':	tileFile = "tree.png";
						break;
			case 'W':	tileFile = "water.png";
						break;
			default:	tileFile = "dirt.png";
		}
		
		return new ImageIcon(TILEPATH + tileFile);
	}

	private void initializeMap()
	{
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				final int _row = row;
				final int _col = col;
				mapButtons[row][col] = new JButton();
				mapButtons[row][col].setPreferredSize(new Dimension(ICONSIZE, ICONSIZE));
				mapButtons[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						replaceTile(_row, _col);
					}
				});
				mapPanel.add(mapButtons[row][col]);
			}
		}
	}
	
	private void refreshMap()
	{
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				ImageIcon tileImage = tileImage(mapGrid[row][col]);
				mapButtons[row][col].setIcon(tileImage);
			}
		}
	}
	
	private void loadMapFile(String in_file)
	{
		File handle = new File(in_file);
		char[][] newMapData = new char[HEIGHT][WIDTH];  
		
		try {
			FileReader reader = new FileReader(handle);
			BufferedReader stream = new BufferedReader(reader);

			for (int row = 0; row < HEIGHT; row++) {
				String in_line = stream.readLine().replace("\n", "");
				if (in_line.length() == WIDTH) {
					newMapData[row] = in_line.toCharArray();
				}
				else {
					throw new IOException();
				}
			}
		}

		catch (FileNotFoundException e) {
			return;
		}
		catch (IOException e) {
			return;
		}

		mapGrid = newMapData;
		updateStatus("Loaded new map: " + in_file);
	}

	public void replaceTile(int row, int col)
	{
		char desiredTile = getChosenTool();
		if (desiredTile != '\0') {
			mapGrid[row][col] = desiredTile;
			updateStatus("Placed tile at: " + row + "," + col);
		}
		refreshMap();
	}
	
	public char getChosenTool() {
		return chosenTool;
	}

	public void setChosenTool(char chosenTool) {
		this.chosenTool = chosenTool;
	}
}

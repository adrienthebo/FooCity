package foocity.guiclient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;


public class Map {
	// Path Constants--will remove
	private String TILEPATH = "/home/cchen/git/FooCity/contrib/foo-tiles/";
	
	// Size Constants--map grid
	private final int ICONSIZE = 36;
	private final int ICONSIZE_MINI = 8;

	// How big is the map?
	private int mapWidth = 128;
	private int mapHeight = 128;

	// The map panel
	private JPanel mapPanel;
	
	// Map is held as a 2-D array of char
	private char[][] mapGrid;
	// The actual buttons displayed to the user
	private JButton[][] mapButtons;	

	public Map(int height, int width)
	{
		mapHeight = height;
		mapWidth = width;
	}
	
	public JPanel largeMap(MainMap parentApp)
	{
		createMapView();
		initializeMap(parentApp, ICONSIZE);
		refreshMap();	
		return mapPanel;
	}
	
	public JPanel miniMap()
	{
		createMapView();
		initializeMap(ICONSIZE_MINI);
		refreshMap();
		return mapPanel;
	}

	private void createMapView()
	{
		mapPanel = new JPanel();
		mapPanel.setLayout(new GridLayout(mapHeight, mapWidth, 0, 0));

		mapGrid = new char[mapHeight][mapWidth];
		mapButtons = new JButton[mapHeight][mapWidth];
	}

	private void initializeMap(final MainMap parentApp, int iconSize)
	{
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				final int _row = row;
				final int _col = col;
				mapButtons[row][col] = new JButton();
				mapButtons[row][col].setPreferredSize(new Dimension(iconSize, iconSize));
				mapButtons[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						parentApp.replaceTile(_row, _col);
					}
				});
				mapPanel.add(mapButtons[row][col]);
			}
		}
	}

	private void initializeMap(int iconSize)
	{
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				mapButtons[row][col] = new JButton();
				mapButtons[row][col].setPreferredSize(new Dimension(iconSize, iconSize));
				mapPanel.add(mapButtons[row][col]);
			}
		}
	}

	
	private void refreshMap()
	{
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				ImageIcon tileImage = tileImage(mapGrid[row][col]);
				mapButtons[row][col].setIcon(tileImage);
			}
		}
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
			default:	tileFile = "water.png";
		}
		
		return new ImageIcon(TILEPATH + tileFile);
	}
	
	public void updateMap(char[][] tileTypes)
	{
		for (int _row = 0; _row < mapHeight ; _row++)
		{
			updateRow(_row, tileTypes[_row]);
		}
	}

	private void updateRow(int row, char[] tileTypes)
	{
		for (int _col = 0; _col < mapWidth ; _col++)
		{
			mapButtons[row][_col].setIcon(tileImage(tileTypes[_col]));
		}
	}
	
	public void updateTile(int row, int col, char tileType)
	{
		mapButtons[row][col].setIcon(tileImage(tileType));
	}
	
}
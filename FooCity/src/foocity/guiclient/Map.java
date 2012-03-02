package foocity.guiclient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.util.Hashtable;


public class Map {
	// Path Constants--will remove
	private String TILEPATH = "/home/chchen/git/FooCity/contrib/foo-tiles/16px/";
	
	// Size Constants--map grid
	private final int ICONSIZE = 36;
	private final int MINI_ICONSIZE = 4;

	// Tile Types
	private char[] tileLabels = {'B', 'D', 'G', 'T', 'W', '\0'};
	
	// How big is the map?
	private int mapWidth;
	private int mapHeight;

	// The map panel
	private JPanel largePanel;
	private JPanel miniPanel;
	
	// A Hashtable of Icons
	private Hashtable<Character, ImageIcon> tileIcons;
	
	// The actual buttons displayed to the user (large view)
	private JButton[][] largeButtons;	

	// The actual icons displayed to the user (mini view)
	private JButton[][] miniButtons;	
	
	public Map(MainMap client, int height, int width)
	{
		mapHeight = height;
		mapWidth = width;
		loadIcons();
		createMapElements();
		initializeMap(client, largePanel, largeButtons, ICONSIZE, true);
		initializeMap(client, miniPanel, miniButtons, MINI_ICONSIZE, false);
	}
	
	public JPanel largeMap()
	{
		return largePanel;
	}
	
	public JPanel miniMap()
	{
		return miniPanel;
	}

	public void updateTile(int row, int col, char tileType)
	{
		ImageIcon icon = getIcon(tileType);
		largeButtons[row][col].setIcon(icon);
		miniButtons[row][col].setIcon(icon);
	}

	public void updateMap(char[][] tileTypes)
	{
		for (int _row = 0; _row < mapHeight ; _row++)
		{
			updateRow(_row, tileTypes[_row]);
		}
	}
	
	private void createMapElements()
	{
		largePanel = new JPanel();
		largePanel.setLayout(new GridLayout(mapHeight, mapWidth, 0, 0));
		largeButtons = new JButton[mapHeight][mapWidth];

		miniPanel = new JPanel();
		miniPanel.setLayout(new GridLayout(mapHeight, mapWidth, 0, 0));
		miniButtons = new JButton[mapHeight][mapWidth];		
	}

	private void initializeMap(final MainMap client, JPanel panel, JButton[][] buttonGrid, int iconSize, boolean border)
	{
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				final int _row = row;
				final int _col = col;
				buttonGrid[row][col] = new JButton();
				
				if (border == false) // Useful for the minimap
					buttonGrid[row][col].setBorder(BorderFactory.createEmptyBorder());
	
				buttonGrid[row][col].setPreferredSize(new Dimension(iconSize, iconSize));
				buttonGrid[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						client.replaceTile(_row, _col);
					}
				});
				panel.add(buttonGrid[row][col]);
			}
		}
	}
	
	private void loadIcons()
	{
		tileIcons = new Hashtable<Character, ImageIcon>();
		for (int i = 0; i < tileLabels.length; i++)
		{
			char key = tileLabels[i];
			tileIcons.put(key, loadImage(key));
		}
	}
	
	private ImageIcon loadImage(char tileType)
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
	
	private ImageIcon getIcon(char tileType)
	{
		if (tileIcons.containsKey(tileType))
			return tileIcons.get(tileType);
		else
			return tileIcons.get('\0');
	}
	
	private void updateRow(int row, char[] tileTypes)
	{
		for (int _col = 0; _col < mapWidth ; _col++)
		{
			ImageIcon icon = getIcon(tileTypes[_col]);
			largeButtons[row][_col].setIcon(icon);
			miniButtons[row][_col].setIcon(icon);
		}
	}
	
	
}
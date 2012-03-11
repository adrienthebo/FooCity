package foocity.guiclient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import foocity.grid.Grid;
import foocity.grid.GridEvent;
import foocity.grid.GridListener;

import foocity.tile.*;

import java.net.URL;
import java.util.Hashtable;


public final class Map implements GridListener {
	// The Grid Object
	private Grid grid;
	
	// Size Constants--map grid
	private final int ICONSIZE = 36;
	private final int MINI_ICONSIZE = 3;

	// The tile type we wish to place
	private String desiredTile = null;
	
	// How big is the map?
	private int mapWidth;
	private int mapHeight;

	// The map panel
	private JPanel largePanel;
	private JPanel miniPanel;
	
	// A Hashtable of Icons
	private Hashtable<String, ImageIcon> tileIcons;
	
	// The actual buttons displayed to the user (large view)
	private JButton[][] largeButtons;	

	// The actual icons displayed to the user (mini view)
	private JButton[][] miniButtons;	
	
	public Map(int height, int width)
	{
		mapHeight = height;
		mapWidth = width;
		initializeGrid();
		loadIcons();
		createMapElements();
		initializeMap(largePanel, largeButtons, ICONSIZE, true);
		initializeMap(miniPanel, miniButtons, MINI_ICONSIZE, false);
	}
	
	public JPanel largeMap()
	{
		return largePanel;
	}
	
	public JPanel miniMap()
	{
		return miniPanel;
	}

	public void updateTile(int row, int col)
	{
		ImageIcon icon = getIcon(grid.getTile(col, row));
		largeButtons[row][col].setIcon(icon);
		miniButtons[row][col].setIcon(icon);
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
	
	private void initializeGrid()
	{
		String initialGrid[][] = new String[mapWidth][mapHeight];
		for (int col = 0; col < mapWidth ; col++)
			for (int row = 0; row < mapHeight; row++)
				initialGrid[col][row] = "Water";
		
		grid = new Grid(initialGrid);
		grid.addGridListener(this);
	}

	private void initializeMap(JPanel panel, JButton[][] buttonGrid, int iconSize, boolean border)
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
						placeTile(_row, _col);
					}
				});
				panel.add(buttonGrid[row][col]);
			}
		}
	}
	
	private void loadIcons()
	{
		String[] tileTypes = TileCollection.instance().getNames();
		
		tileIcons = new Hashtable<String, ImageIcon>();
		
		for (int i = 0; i < tileTypes.length; i++)
		{
			String key = tileTypes[i];
			tileIcons.put(key, loadImage(key));
		}
	}
	
	private ImageIcon loadImage(String tileType)
	{
		String imagePath = "tile_images/" + tileType + ".png";
		return new ImageIcon(this.getClass().getResource(imagePath));
	}
	
	private ImageIcon getIcon(String tileType)
	{
		System.out.println("Tile Type: " + tileType);
		if (tileIcons.containsKey(tileType))
			return tileIcons.get(tileType);
		else
			return tileIcons.get("");
	}

	@Override
	public void gridUpdated(GridEvent e) {
		updateTile(e.getYAxis(), e.getXAxis());
	}
	
	public void placeTile(int row, int col)
	{
		String tileType = getDesiredTile();
		if (tileType != null)
			grid.setTile(col, row, tileType);
	}

	public String getDesiredTile() {
		return desiredTile;
	}

	public void setDesiredTile(String desiredTile) {
		this.desiredTile = desiredTile;
	}	
	
}
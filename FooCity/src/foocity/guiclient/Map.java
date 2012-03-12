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

import foocity.tile.TileCollection;

import java.util.Hashtable;


/**
 * <p>
 * Provides a view of the Grid model.
 * </p>
 */
public final class Map implements GridListener {
	// The Grid Object
	private Grid grid;

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
	
	/**
	 * <p>
	 * This class creates a swing graphical representation of data in a Grid object.
	 * An array of height x width buttons is created at iconSize pixels a piece, and
	 * an alternative mini map is created with the same dimensions, but with a smaller
	 * button size, miniIconSize.
	 * </p>
	 * 
	 * @param height
	 * @param width
	 * @param iconSize
	 * @param miniIconSize
	 * 
	 * @return Map
	 */
	public Map(int height, int width,  int iconSize, int miniIconSize)
	{
		mapHeight = height;
		mapWidth = width;
		initializeGrid();
		loadIcons();
		createMapElements();
		initializeMap(largePanel, largeButtons, iconSize, true);
		initializeMap(miniPanel, miniButtons, miniIconSize, false);
	}

	/**
	 * <p>
	 * Returns the underlying Grid object, for use by the state management classes.
	 * </p>
	 * 
	 * @return Grid
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * <p>
	 * Returns the JPanel of large map icons.
	 * </p>
	 * 
	 * @return JPanel
	 */
	public JPanel largeMap()
	{
		return largePanel;
	}
	
	/**
	 * <p>
	 * Returns the JPanel of mini map icons.
	 * </p>
	 * 
	 * @return JPanel
	 */
	public JPanel miniMap()
	{
		return miniPanel;
	}

	/**
	 * <p>
	 * Redraw the map--useful after a mass change like a file load.
	 * </p>
	 */
	public void updateMap()
	{
		for (int row = 0; row < mapHeight ; row++)
			for (int col = 0; col < mapWidth ; col++)
				updateTile(row, col);
	}
	
	/**
	 * <p>
	 * Return a ImageIcon that corresponds to the specified tileType. Useful
	 * for populating the MainMap's toolbar.
	 * </p>
	 * 
	 * @param tileType
	 * @return ImageIcon
	 */
	public ImageIcon getIcon(String tileType)
	{
		if (tileIcons.containsKey(tileType))
			return tileIcons.get(tileType);
		else
			return tileIcons.get("Water");
	}

	/**
	 * <p>
	 * This implements the GridListener class, providing a handler for asynchronous
	 * GridEvents (updates) sent by the Grid.
	 * </p>
	 */
	@Override
	public void gridUpdated(GridEvent e) {
		updateTile(e.getYAxis(), e.getXAxis());
	}
	
	/**
	 * <p>
	 * When the user clicks on a map tile, this method is called with the row and
	 * column of the tile. It checks to see if a tile is selected in the toolbar--
	 * if there is, the grid's setTile method is called.
	 * </p>
	 * 
	 * @param row
	 * @param col
	 */
	public void placeTile(int row, int col)
	{
		String tileType = getDesiredTile();
		if (tileType != null)
			grid.setTile(col, row, tileType);
	}

	/**
	 * Returns the currently desired tile.
	 * 
	 * @return String
	 */
	public String getDesiredTile() {
		return desiredTile;
	}

	/**
	 * Sets the currently desired tile.
	 * 
	 * @param desiredTile
	 */
	public void setDesiredTile(String desiredTile) {
		this.desiredTile = desiredTile;
	}	
	
	// Create the JPanels and defines (but does not allocate) the button arrays.
	private void createMapElements()
	{
		largePanel = new JPanel();
		largePanel.setLayout(new GridLayout(mapHeight, mapWidth, 0, 0));
		largeButtons = new JButton[mapHeight][mapWidth];

		miniPanel = new JPanel();
		miniPanel.setLayout(new GridLayout(mapHeight, mapWidth, 0, 0));
		miniButtons = new JButton[mapHeight][mapWidth];		
	}
	
	/**
	 * The Grid constructor requires an initial 2-d array of tileType Strings.
	 * So, um, of course, in the beginning, there was all Water, right?
	 * Yeah, and it was REAL dark.
	 * 
	 * In addition, we register with the Grid as a GridEvent listener.
	 */
	private void initializeGrid()
	{
		String initialGrid[][] = new String[mapWidth][mapHeight];
		for (int col = 0; col < mapWidth ; col++)
			for (int row = 0; row < mapHeight; row++)
				initialGrid[col][row] = "Water";
		
		grid = new Grid(initialGrid);
		grid.addGridListener(this);
	}

	/**
	 * <p>
	 * Actually create the array of buttons, set their size, and register the
	 * action that occurs when a tile is selected.
	 * 
	 * The large map sets border to true because it seems to help with tile
	 * placement, but on the minimap, the borders make the view muddy.
	 * </p>
	 * 
	 * @param panel
	 * @param buttonGrid
	 * @param iconSize
	 * @param border
	 */
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
	
	// Run through all the TileCollection's images and preload the image files in a hash.
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
	
	// Given a tileType, load the image from our resources.
	private ImageIcon loadImage(String tileType)
	{
		String imagePath = "tile_images/" + tileType + ".png";
		return new ImageIcon(this.getClass().getResource(imagePath));
	}
	
	// Given a row and column, query the Grid, and update both button arrays.
	private void updateTile(int row, int col)
	{
		ImageIcon icon = getIcon(grid.getTile(col, row));
		largeButtons[row][col].setIcon(icon);
		miniButtons[row][col].setIcon(icon);
	}
}
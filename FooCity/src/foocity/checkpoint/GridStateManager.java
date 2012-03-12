package foocity.checkpoint;

import java.io.*;
import java.util.*;

import foocity.grid.Grid;

/**
 * <p>
 * This class provides a manager class to store and restore the state of a grid.
 * </p>
 */
public class GridStateManager {

	protected Grid _grid;

	/**
	 * <p>
	 * Generate a new GridStateManager referencing an existing grid.
	 * </p>
	 *
	 * @param newGrid The grid to manage.
	 */
	public GridStateManager(Grid newGrid) {
		_grid = newGrid;
	}

	/**
	 * <p>
	 * Load a grid state from a specific file
	 * </p>
	 *
	 * @param gridSaveFile The path to the file holding the state
	 */
	public boolean load(String gridSaveFile) {
		try {
			FileReader reader = new FileReader(gridSaveFile);

			char[][] rows = loadFromStream((Reader)reader);
			populateGrid(rows);

			return true;
		}
		catch(IllegalArgumentException e) {
			System.err.println("Unable to load grid file " + gridSaveFile + ": " + e);
			System.err.println("Grid resizing is not currently supported; refusing to load this map.");
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			System.err.println("Unable to load grid file " + gridSaveFile + ": " + e);
			e.printStackTrace();
		}
		catch(IOException e) {
			System.err.println("Error while loading " + gridSaveFile + ": " + e);
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * <p>
	 * Read the contents of a file into a 2D array.
	 * </p>
	 *
	 * <p>
	 * This is directly exposed for testing purposes, and you probably don't
	 * need to use it.
	 * </p>
	 *
	 * @param reader
	 */
	public char[][] loadFromStream(Reader reader) throws IOException {

		BufferedReader stream = new BufferedReader(reader);
		List<char[]> currentRows = new LinkedList<char[]>();

		while(stream.ready()) {
			String line = stream.readLine();

			if(line != null) {
				//readLine() returns null at the end of the stream

				char[] row = line.toCharArray();
				currentRows.add(row);
			}
			else {
				// The stream is ready, and also empty.
				break;
			}
		}

		/* Convert linked list of char arrays to a 2D char array.
		 * Java is not my favorite language.
		 */
		char rows[][] = new char[currentRows.size()][];
		for(int i = 0; i < currentRows.size(); i++) rows[i] = currentRows.get(i);
		
		return rows;
	}

	/**
	 * <p>
	 * Performs an in-place update of the game grid.
	 * </p>
	 *
	 * <p>
	 * This is directly exposed for testing purposes, and you probably don't
	 * need to use it.
	 * </p>
	 *
	 * @param map The 2D tile
	 */
	public void populateGrid(char[][] map) {

		/* Ensure that the current grid and loaded map are the same
		 * dimensions; if not then refuse to load.
		 *
		 * TODO Allow resizing of the grid and trigger
		 * PropertyChangedEvents upon resizing.
		 */
		int newXSize = map.length;
		int newYSize = map[0].length;

		if(newXSize != _grid.getXSize() || newYSize != _grid.getYSize()) {
			throw new IllegalArgumentException("Tried to load grid from array with mismatched size, expected " + _grid.getXSize() + ", " + _grid.getYSize() + ", got " + newXSize + ", " + newYSize);
		}


		// XXX trigger PropertyChangeEvent?
		_grid.gridFromChars(map);
	}

	/**
	 * <p>
	 * Produces a file representing the current grid state.
	 * </p>
	 *
	 *
	 * @param fileName the path to the game save file.
	 */
	public boolean save(String fileName) {

		try {
			FileWriter writer = new FileWriter(fileName);
			writeToStream(writer);
			return true;
		}
		catch(IOException e) {
			System.err.println("Error while saving " + fileName + ": " + e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * <p>
	 * Writes the current tile grid to a stream in the save file format
	 * </p>
	 *
	 * <p>
	 * This is directly exposed for testing purposes, and you probably don't
	 * need to use it.
	 * </p>
	 *
	 *
	 * @param fileName the path to the game save file.
	 */
	public void writeToStream(Writer writer) throws IOException {
		BufferedWriter stream = new BufferedWriter(writer);

		char[][] rows = _grid.toCharGrid();

		for(char[] row : rows) {
			stream.write(row);
			stream.newLine();
		}
		stream.flush();
	}
}

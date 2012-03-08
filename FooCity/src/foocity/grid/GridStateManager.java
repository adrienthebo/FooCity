package foocity.grid;

import java.io.*;
import java.util.*;

/**
 * <p>
 * This class provides methods for loading/saving a grid.
 * </p>
 */
public class GridStateManager {

	/**
	 * <p>
	 * Performs an in-place update of a game grid.
	 * </p>
	 *
	 * @param grid The grid to populate from the file
	 * @param fileName the path to the game save file.
	 */
	public static boolean loadGrid(Grid grid, String fileName) {

		char[][] map = loadMapFile(fileName);
		if(map == null) {
			// The loading method already failed and logged errors, MAYDAY MAYDAY WE'RE GOING DOWN EJECT
			return false;
		}

		/* Ensure that the current grid and loaded map are the same
		 * dimensions; if not then refuse to load.
		 *
		 * TODO Allow resizing of the grid and trigger
		 * PropertyChangedEvents upon resizing.
		 */
		int newXSize = map.length;
		int newYSize = map[0].length;

		if(newXSize != grid.getXSize() || newYSize != grid.getYSize()) {
			System.err.println("ERROR: Tried to load map file with dimensions " + newXSize + ", " + newYSize);
			System.err.println("Grid resizing is not currently supported; refusing to load this map.");
			return false;
		}


		// XXX trigger PropertyChangeEvent?
		grid.gridFromChars(map);
		return true;
	}

	/**
	 * <p>
	 * Load a map grid into a 2D array of chars
	 * </p>
	 *
	 * <p>
	 * XXX This method only catches and logs errors. Should it just outright
	 * fail and throw exceptions?
	 * </p>
	 *
	 * @param fileName The file to load
	 * @return The file split into a 2D array of chars
	 */
	static char[][] loadMapFile(String fileName) {
		char[][] rows = null;
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader stream = new BufferedReader(reader);

			List<char[]> currentRows = new LinkedList<char[]>();

			while(stream.ready()) {
				char[] row = stream.readLine().toCharArray();
				currentRows.add(row);
				stream.skip(1); // Skip over the newline. XXX Is this necessary?
			}
			rows = (char[][])currentRows.toArray();

		}
		catch(FileNotFoundException e) {
			System.err.println("Unable to load grid file " + fileName + ": " + e);
			e.printStackTrace();
		}
		catch(IOException e) {
			System.err.println("Error while loading " + fileName + ": " + e);
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * <p>
	 * Produces a file representing the current grid state.
	 * </p>
	 *
	 * <p>
	 * XXX This method only catches and logs errors. Should it just outright
	 * fail and throw exceptions?
	 * </p>
	 *
	 * @param grid The grid to use to populate the file.
	 * @param fileName the path to the game save file.
	 */
	public static boolean saveGrid(Grid grid, String fileName) {

		try {
			FileWriter writer = new FileWriter(fileName);
			BufferedWriter stream = new BufferedWriter(writer);

			char[][] rows = grid.toCharGrid();

			for(char[] row : rows) {
				stream.write(row, 0, row.length);
				stream.newLine();
			}
			return true;
		}
		catch(IOException e) {
			System.err.println("Error while saving " + fileName + ": " + e);
			e.printStackTrace();
			return false;
		}
	}
}

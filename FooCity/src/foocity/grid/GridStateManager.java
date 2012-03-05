package foocity.grid;

import java.io.*;
import java.util.*;

public class GridStateManager {

	/**
	 * <p>
	 * Performs an in-place update of a game grid.
	 * </p>
	 *
	 * <p>
	 * XXX This method only catches and logs errors. Should it just outright
	 * fail and throw exceptions?
	 * </p>
	 *
	 * @param grid The grid to populate from the file
	 * @param fileName the path to the game save file.
	 */
	public static boolean loadGrid(Grid grid, String fileName) {

		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader stream = new BufferedReader(reader);

			List<char[]> currentRows = new LinkedList<char[]>();

			while(stream.ready()) {
				char[] row = stream.readLine().toCharArray();
				currentRows.add(row);
				stream.skip(1); // Skip over the newline. XXX Is this necessary?
			}
			char[][] rows = (char[][])currentRows.toArray();

			/* Generate a new grid from the file, swap out listeners, and
			 * replace the old grid with the new grid.
			 *
			 * REVIEW This is somewhat evil.
			 */
			Grid newGrid = new Grid(rows);
			newGrid._listeners = grid._listeners;
			grid = newGrid; // XXX This needs to be tested; I'm not sure it'll do what I expect
			return true;
		}
		catch(FileNotFoundException e) {
			System.err.println("Unable to load grid file " + fileName + ": " + e);
			e.printStackTrace();
			return false;
		}
		catch(IOException e) {
			System.err.println("Error while loading " + fileName + ": " + e);
			e.printStackTrace();
			return false;
		}
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

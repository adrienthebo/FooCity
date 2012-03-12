package foocity.checkpoint;

import java.io.*;
import java.util.*;

import foocity.state.*;
import foocity.grid.Grid;
/**
 * <p>
 * This class provides a general purpose adapter for loading and saving games.
 * </p>
 *
 * <p>
 * Loading and saving an actual grid is delegated to the GridStateManager
 * class, since that's fairly involved.
 * </p>
 */
public class Manager {

	protected GameState _state;

	/**
	 * <p>
	 * Generate a new Manager based on an existing state.
	 * </p>
	 *
	 * @param newState The existing state object
	 */
	public Manager(GameState newState) {
		_state = newState;
	}

	/**
	 * <p>
	 * Load a checkpoint file into the given GameState
	 * </p>
	 *
	 * @param checkpointFile The file to read game state from
	 */
	public void load(String checkpointFile) {
		try {
			Reader f = new FileReader(checkpointFile);
			populate(f);
		}
		catch(FileNotFoundException e) {
			System.err.println("Unable to load checkpoint file " + checkpointFile + ": " + e);
			e.printStackTrace();
		}
		catch(IOException e) {
			System.err.println("Error while loading " + checkpointFile + ": " + e);
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Parse checkpoint data from a stream and populate the GameState
	 * </p>
	 *
	 * <p>
	 * This is directly exposed for testing purposes, and you probably don't
	 * need to use it.
	 * </p>
	 *
	 * @param r The reader to read from
	 */
	public void populate(Reader r) throws IOException {
		String[][] lines = parseStream(r);
		for(String[] tokens : lines) {
			if(tokens[0].equals("grid")) loadGrid(tokens);
			else if(tokens[0].equals("gamecalendar")) loadGameCalendar(tokens);
			else if(tokens[0].equals("funds")) loadFunds(tokens);
			else if(tokens[0].equals("taxes")) loadTaxes(tokens);
		}
	}

	/**
	 * <p>
	 * Translate a grid config line into grid state
	 * </p>
	 *
	 * <p>
	 * This method expects xSize and ySize but doesn't currently use them.
	 * We can't resize the grid after it's been generated, so this may
	 * be useful for later but isn't used right now.
	 * </p>
	 *
	 * <p>
	 * Expected input: grid xSize ySize path/to/gridfile
	 * </p>
	 */
	private void loadGrid(String[] tokens) {
		//int xSize = Integer.parseInt(tokens[1]);
		//int ySize = Integer.parseInt(tokens[2]);
		String mapFileName = tokens[3];

		GridStateManager gUnit = new GridStateManager(_state.getGrid());
		gUnit.load(mapFileName);
	}

	/**
	 * <p>
	 * Translate a grid config line into a date
	 * </p>
	 *
	 * <p>
	 * Expected input: gamecalendar year month dayofmonth
	 * </p>
	 */
	private void loadGameCalendar(String[] tokens) {
		int year = Integer.parseInt(tokens[1]);
		int month = Integer.parseInt(tokens[2]);
		int dayOfMonth = Integer.parseInt(tokens[3]);

		_state.getCalendar().set(new GregorianCalendar(year, month, dayOfMonth));
	}

	/**
	 * <p>
	 * Translate a grid config line into current funds
	 * </p>
	 *
	 * <p>
	 * Expected input: funds 0000
	 * </p>
	 */
	private void loadFunds(String[] tokens) {
		int funds = Integer.parseInt(tokens[1]);
	}

	/**
	 * <p>
	 * Translate a grid config line into current funds
	 * </p>
	 *
	 * <p>
	 * Expected input: funds 0000
	 * </p>
	 */
	private void loadTaxes(String[] tokens) {
		int propertyTax = Integer.parseInt(tokens[1]);
		int salesTax = Integer.parseInt(tokens[2]);
		int businessTax = Integer.parseInt(tokens[3]);
		int incomeTax = Integer.parseInt(tokens[4]);

		_state.getTaxRates().setAll(propertyTax, salesTax, businessTax, incomeTax);
	}

	/**
	 * <p>
	 * Read in a checkpoint from a stream and split it into tokens.
	 * </p>
	 *
	 * <pre>
	 * myManager.parseStream(new StringReader("one two\nthree four")); //returns {{"one", "two"}, {"three", "four}}
	 * </pre>
	 *
	 * @param r The reader to parse
	 * @throws IOException lol error handling
	 */
	public String[][] parseStream(Reader r) throws IOException {
		BufferedReader stream = new BufferedReader(r);

		List<String[]> currentRows = new LinkedList<String[]>();
		while(stream.ready()) {
			//Extract content from the stream until it returns null, IE is empty

			String line = stream.readLine();
			if(line != null) {
				String[] tokens = line.split(" ");
				currentRows.add(tokens);
			}
			else {
				break;
			}
		}

		/* Convert linked list of char arrays to a 2D char array.
		 * Java is not my favorite language.
		 */
		String[][] rows = new String[currentRows.size()][];
		for(int i = 0; i < currentRows.size(); i++) rows[i] = currentRows.get(i);

		return rows;
	}

}

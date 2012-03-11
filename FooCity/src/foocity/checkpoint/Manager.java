package foocity.checkpoint;

import java.io.*;

import java.util.GregorianCalendar;

import foocity.state.*;
import foocity.grid.Grid;
public class Manager {

	public static void loadCheckPoint(GameState state, String filename) throws NumberFormatException, IOException {

		Reader f = new FileReader(filename);
		BufferedReader stream = new BufferedReader(f);
		while(stream.ready()) {
			String line = stream.readLine();

			String[] tokens = line.split(" ");
			if(tokens[0].equals("grid")) {
				int xSize = Integer.parseInt(tokens[1]);
				int ySize = Integer.parseInt(tokens[2]);
				String mapFileName = tokens[3];

				Grid thisGrid = new Grid(xSize, ySize);
				GridStateManager gUnit = new GridStateManager(thisGrid);
				gUnit.load(mapFileName);
			}
			else if(tokens[0].equals("gamecalendar")) {
				int year = Integer.parseInt(tokens[1]);
				int month = Integer.parseInt(tokens[2]);
				int dayOfMonth = Integer.parseInt(tokens[3]);

				state.getCalendar().set(new GregorianCalendar(year, month, dayOfMonth));
			}
			else if(tokens[0].equals("funds")) {
				int funds = Integer.parseInt(tokens[1]);
			}
			else if(tokens[0].equals("taxes")) {
				int propertyTax = Integer.parseInt(tokens[1]);
				int salesTax = Integer.parseInt(tokens[2]);
				int businessTax = Integer.parseInt(tokens[3]);
				int incomeTax = Integer.parseInt(tokens[4]);

				state.getTaxRates().setAll(propertyTax, salesTax, businessTax, incomeTax);
			}
		}
	}
}



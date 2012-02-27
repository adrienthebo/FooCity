package foocity.grid;

import java.awt.Point;
import java.util.ArrayList;

import foocity.tile.Tile;

public class NeighborSelector {
	
	/**
	 * <p>
	 * Retrieve all surrounding tiles of a given tile within a given radius.
	 * </p>
	 * @param grid The grid to extract neighboring tiles from
	 * @param xAxis The x axis of the tile for which we shall find neighbors
	 * @param yAxis The y axis of the tile for which we shall find neighbors
	 * @param radius The radius of tiles to select within
	 */
	public static Tile[] get(Grid grid, int xAxis, int yAxis, int radius) {
		/*
		 * XXX This is probably going to be a piss poor algorithm, but it's the
		 * first thing that popped into my mind at 23:00 today.
		 * 
		 * Less horrible algorithm, but still horrible:
		 * Scan all tiles within 2 * radius, X, and Y, if the distance < r
		 * then add it.
		 * 
		 * The "I want to use my iterator class, efficiency be damned" approach
		 * is as follows:
		 * 
		 * Scan the entire grid and add anything within a distance of radius.
		 * It's a POC, and it's not a critical component. Leave me alone.
		 * 
		 * I talk to myself when I'm coding. Myep.
		 */
		
		Point targetPoint = new Point(xAxis, yAxis);
		
		ArrayList<Tile> members = new ArrayList<Tile>();
		
		GridMemberIterator iter = new GridMemberIterator(grid);
		
		while(iter.hasNext()) {
			Tile current = iter.next();
			
			Point currentPoint = new Point(iter.currentXAxis(), iter.currentYAxis()); 
			
			double distance = targetPoint.distance(currentPoint);
			
			if(distance < radius) {
				members.add(current);
			}
		}
		
		return (Tile[]) members.toArray();
	}
}

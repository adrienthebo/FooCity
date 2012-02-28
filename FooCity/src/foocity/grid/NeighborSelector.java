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
		 * Scan all tiles within 2 * radius, X, and Y, if the distance <= r
		 * then add it.
		 * 
		 * It's inefficient but easy.
		 */
		
		Point targetPoint = new Point(xAxis, yAxis);
		
		ArrayList<Tile> members = new ArrayList<Tile>();
		
		for(int xIter = (xAxis - radius); xIter <= (xAxis + radius); xIter++) {
			for(int yIter = (yAxis - radius); yIter <= (yAxis + radius); yIter++) {
				Point currentPoint = new Point(xIter, yIter);
				
				double distance = currentPoint.distance(targetPoint);
				if(distance <= radius && distance < 1) {
					members.add(grid._tiles[xIter][yIter]);
				}
			}
		}
		
		return (Tile[]) members.toArray();
	}
}

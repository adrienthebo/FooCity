package test.fixtures;

public class GridFixtures {
	static public String[][] tinyGrid = {
			{ "GrassTile", "DirtTile", },
			{ "CommercialTile", "IndustrialTile", },
			{ "WaterTile", "ForestTile", },
		};
	
	static public String[][] smallGrid = {
		{"GrassTile", "DirtTile", "BeachTile", "WaterTile", "WaterTile",},
		{"GrassTile", "GrassTile", "DirtTile", "BeachTile", "BeachTile",},
		{"ForestTile", "ResidentialTile", "CommercialTile", "SolarPowerTile", "DirtTile",},
		{"WaterPlantTile", "PoliceStationTile", "ParkTile", "ParkTile", "DirtTile",},
		{"IndustrialTile", "DirtTile", "GrassTile", "ForestTile", "DirtTile", },
	};
}

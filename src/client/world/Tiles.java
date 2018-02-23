package client.world;

import java.awt.image.BufferedImage;

import client.gfx.Assets;

public class Tiles {
	public static class BlankTile extends Tile {		
		public BlankTile(int id) { super(id, Assets.blank);	}	
	}
	
	public static class WallTile extends Tile {
		public WallTile(int id) { super(id, Assets.wall); }
		
		@Override
		public boolean isSolid() { return true; }
	}
	
	public static class DiscoveredTile extends Tile {
		public DiscoveredTile(int id) { super(id, Assets.blank); }		
	}
	
	public static  Tile[] allTiles;
	public static Tile blankTile, wallTile, discoveredTile;
	
		
	public static void init() {			
		allTiles  = new Tile[8];
		blankTile = new BlankTile(0);
		wallTile = new WallTile(1);
		discoveredTile = new DiscoveredTile(2);
	}
}


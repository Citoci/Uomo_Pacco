package world;

import gfx.Assets;

/**
 * Classe statica dei tiles
 * Contiene tutte le diverse estensioni della classe Tile
 * e un'istanza statica di ognuna di esse 
 */
public class Tiles {
	
	// Tile vuoto
	public static class BlankTile extends Tile {
		public BlankTile(int id) { super(id, null); }
	}

	// Tile muro
	public static class WallTile extends Tile {
		public WallTile(int id) { super(id, Assets.wall); }

		@Override
		public boolean isSolid() { return true; }
	}

	// Tile moneta
	public static class CoinTile extends Tile {
		public CoinTile(int id) { super(id, Assets.ball); }
	}
	
	public static class BIgCoinTile extends Tile {
		public BIgCoinTile(int id) { super(id, Assets.bigCoin); } 
	}
	
	// Tile frutta
	public static class AppleTile extends Tile {
		public AppleTile(int id) { super(id, Assets.fruits[3]); }
	}
	
	public static class CherryTile extends Tile {
		public CherryTile(int id) { super(id, Assets.fruits[0]); }
	}
	
	public static class StrawTile extends Tile {
		public StrawTile(int id) { super(id, Assets.fruits[2]); }
	}
	
	public static class OrangeTile extends Tile {
		public OrangeTile(int id) { super(id, Assets.fruits[1]); }
	}
	
	
	public static Tile[] allTiles; // array statico con un'istanza per ogni tipo di tile, con posizione in base all'id
	public static Tile coinTile, wallTile, blankTile, cherryTile, appleTile, orangeTile, strawTile, bigCoinTile;

	public static void init(double zoom) {
		allTiles = new Tile[8];
		coinTile = new CoinTile(0);
		wallTile = new WallTile(1);
		blankTile = new BlankTile(2);
		
		cherryTile = new CherryTile(3);
		strawTile = new StrawTile(4);
		orangeTile = new OrangeTile(5);
		appleTile = new AppleTile(6);
		
		bigCoinTile = new BIgCoinTile(7);
		
		Tile.TILE_SIZE *= zoom; // ridimensiona le tile in base allo ZOOM
	}
}

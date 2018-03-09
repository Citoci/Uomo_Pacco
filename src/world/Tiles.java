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
	
	// Tile frutto
	public static class FruitTile extends Tile {
		public FruitTile(int id) { super(id, Assets.heart); }
	}
	
	public static Tile[] allTiles; // array statico con un'istanza per ogni tipo di tile, con posizione in base all'id
	public static Tile coinTile, wallTile, blankTile, fruitTile;

	public static void init(double zoom) {
		allTiles = new Tile[8];
		coinTile = new CoinTile(0);
		wallTile = new WallTile(1);
		blankTile = new BlankTile(2);
		fruitTile = new FruitTile(3);
		
		Tile.TILE_SIZE *= zoom; // ridimensiona le tile in base allo ZOOM
	}
}

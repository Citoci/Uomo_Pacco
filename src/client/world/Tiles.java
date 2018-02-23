package client.world;

import client.gfx.Assets;

public class Tiles {
	public static class BlankTile extends Tile {
		public BlankTile(int id) {
			super(id, null);
		}
	}

	public static class WallTile extends Tile {
		public WallTile(int id) {
			super(id, Assets.wall);
		}

		@Override
		public boolean isSolid() {
			return true;
		}
	}

	public static class CoinTile extends Tile {
		public CoinTile(int id) {
			super(id, Assets.ball);
		}
	}

	public static Tile[] allTiles;
	public static Tile coinTile, wallTile, blankTile;

	public static void init() {
		allTiles = new Tile[8];
		coinTile = new CoinTile(0);
		wallTile = new WallTile(1);
		blankTile = new BlankTile(2);
	}
}

package client.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import client.Game;

public abstract class Tile {

	public static int TILE_SIZE = (int) (64 * Game.game.zoom);

	protected final int id;
	protected BufferedImage texture;

	public Tile(int id, BufferedImage texture) {
		this.texture = texture;
		this.id = id;

		Tiles.allTiles[id] = this;
	}

	public void tick() {
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_SIZE, TILE_SIZE, null);
	}

	public boolean isSolid() {
		return false;
	}
}

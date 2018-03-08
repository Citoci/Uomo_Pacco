package client.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import client.Game;
import client.GameServer;
import client.gfx.Animation;
import client.world.Tile;

public abstract class Entity {

	public static final int DEFAULT_SIZE = Tile.TILE_SIZE,  DEFAULT_SPEED = DEFAULT_SIZE / 16;

	protected Game game;
	
	protected int xPos, yPos, width, height, speed, xMove, yMove;
	protected final int xSpawn, ySpawn;
	protected boolean xBlock, yBlock;
	
	// Animations
	protected Animation animUp, animDw, animLf, animRg;

	public Entity(Game game, int xPos, int yPos) {
		this.game = game;
		this.xPos = xPos;
		this.yPos = yPos;
		xSpawn = xPos;
		ySpawn = yPos;
		width = height = DEFAULT_SIZE;
		speed = DEFAULT_SPEED;
		xMove = yMove = 0;
	}

	public void move() {
		xBlock = yBlock = false;
		if (xMove > 0) {
			int tx = xPos + xMove + width - 1;
			if (!willCollide(tx, yPos) && !willCollide(tx, yPos + height - 1)) {
				xPos += xMove;
				xBlock = true;//
			}
			else {
				xMove = 0;
			}
		} else if (xMove < 0) {
			int tx = xPos + xMove;
			if (!willCollide(tx, yPos) && !willCollide(tx, yPos + height - 1)) {
				xPos += xMove;
				xBlock = true;//
			}
			else {
				xMove = 0;
			}
		}

		if (yMove > 0) {
			int ty = yPos + yMove + height - 1;
			if (!willCollide(xPos, ty) && !willCollide(xPos + width - 1, ty)) {
				yPos += yMove;
				yBlock = true;//
			}

		} else if (yMove < 0) {
			int ty = yPos + yMove;
			if (!willCollide(xPos, ty) && !willCollide(xPos + width - 1, ty)) {
				yPos += yMove;
				yBlock = true;//
			}
		}
	}

	private boolean willCollide(int x, int y) {
		if(game.getWorld() == null)
			System.out.println("porca la pupazza");
		return game.getWorld().getTileAt(x / Tile.TILE_SIZE, y / Tile.TILE_SIZE).isSolid();
	}

	public Rectangle getCollisionBounds() {
		return new Rectangle(xPos, yPos, width, height);
	}

	protected void resetPos() {
		xPos = xSpawn;
		yPos = ySpawn;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

}

package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.server.GameServer;
import gfx.Animation;
import world.Tile;

public abstract class Entity {

	public static final int DEFAULT_SIZE = Tile.TILE_SIZE,  
			DEFAULT_SPEED = DEFAULT_SIZE/16;
	
	private GameServer game;

	protected int xPos, yPos, width, height, speed, xMove, yMove;
	protected final int xSpawn, ySpawn;
	protected boolean xBlock, yBlock;
	
	// Animations
	protected Animation animUp, animDw, animLf, animRg, animDied;

	public Entity(GameServer game, int xSpawn, int ySpawn) {
		this.game = game;
		this.xPos = xSpawn;
		this.yPos = ySpawn;
		this.xSpawn = xSpawn;
		this.ySpawn = ySpawn;
		width = height = DEFAULT_SIZE;
		speed = DEFAULT_SPEED;
		xMove = yMove = 0;
	}

	public void move() {
		xBlock = yBlock = false;
		
		int tx = xPos + xMove; // posizione x futura
		if(xMove > 0) 
			tx += width - 1; // se ci muoviamo a destra, controlliamo il lato destro
		if (!willCollide(tx, yPos) && !willCollide(tx, yPos + height - 1)) // controlliamo che il lato superiore e inferiore non collidano
			xPos += xMove;
		else 
			xBlock = true; // se collidono lo annotiamo in xBlock
		
		int ty = yPos + yMove; // posizione y futura
		if(yMove > 0) 
			ty += height - 1; // se ci muoviamo in basso, controlliamo il lato in basso
		if (!willCollide(xPos, ty) && !willCollide(xPos + width - 1, ty)) // controlliamo che il lato sinistro e destro non collidano
			yPos += yMove;
		else
			yBlock = true; // se collidono lo annotiamo in yBlock
	}

	/**
	 * Controlla se la Tile corrispondente alle coordinate specificate è solida
	 * @param x coordinata x
	 * @param y coordinata y
	 * @return true se la Tile è solida
	 */
	private boolean willCollide(int x, int y) {
		return game.getWorld().getTileAt(x / Tile.TILE_SIZE, y / Tile.TILE_SIZE).isSolid();
	}

	/**
	 * @return un Rectangle con la posizione e le dimensioni corrispondenti a quelli della entity
	 */
	public Rectangle getCollisionBounds() {
		return new Rectangle(xPos, yPos, width, height);
	}

	/**
	 * Reimposta la posizione a quella di partenza
	 */
	protected void resetPos() {
		xPos = xSpawn;
		yPos = ySpawn;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public int getxPos() { return xPos; }
	public int getyPos() { return yPos; }

}

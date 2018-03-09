package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.server.GameServer;
import gfx.Animation;
import gfx.Assets;

public class Player extends Entity {

	public static int DEFAULT_HEALTH = 3;

	private String name;
	private int id, health, points, invulnerableTime;

	BufferedImage stopPos; // Immagine del giocatore fermo

	public Player(GameServer game, int id, String name, int xPos, int yPos) {
		super(game, xPos, yPos);

		this.id = id;
		this.name = name;
		health = DEFAULT_HEALTH;
		points = 0;
		invulnerableTime = 0;

		// Animations
		animLf = new Animation(Assets.pacman[0]);
		animRg = new Animation(Assets.pacman[1]);
		animUp = new Animation(Assets.pacman[2]);
		animDw = new Animation(Assets.pacman[3]);
		
		stopPos = Assets.pacman[0][2];
	}
	
	/**
	 * Prende le boolean di spostamento e le traduce in xMove e yMove in base alla speed del giocatore
	 */
	public void getInput(boolean up, boolean down, boolean left, boolean right) {
		xMove = yMove = 0;
		if (up)
			yMove = -speed;
		if (down)
			yMove = speed;
		if (left)
			xMove = -speed;
		if (right)
			xMove = speed;
	}

	/**
	 * Il giocatore viene colpito
	 * Se � invulnerabile non succede nulla, altrimenti toglie una vita e resetta la posizione
	 */
	public void hurt() {
		if(invulnerableTime > 0)
			return;
		health--;
		resetPos();
	}
	
	/**
	 * Assegna un punto al giocatore
	 */
	public void makePoint() {
		points++;
	}
	
	/**
	 * Riporta il giocatore alla sua posizione di Spawn e lo rende invulnerabile per 200 ticks
	 */
	@Override
	protected void resetPos() {
		super.resetPos();
		invulnerableTime = 200;
	}

	@Override
	public void tick() {
		// Tick delle animazioni
		animUp.tick();
		animDw.tick();
		animLf.tick();
		animRg.tick();
		
		// Se siamo invulnerabili decrementa di uno
		if(invulnerableTime > 0)
			invulnerableTime --;
		
		// Muove il giocatore
		move();
	}

	@Override
	public void render(Graphics g) {
		if(invulnerableTime/15%2 == 0) {
			g.drawImage(getCurrentAnimationFrame(), xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);
			g.setFont(new Font ("Arial", 1, 15)); 
			g.setColor(Color.WHITE);
			g.drawString(name, xPos, yPos - 5); // Nome del giocatore
		}
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0 && !xBlock)
			return animLf.getCurrentFrame();
		else if (xMove > 0 && !xBlock)
			return animRg.getCurrentFrame();
		else if (yMove < 0 && !yBlock)
			return animUp.getCurrentFrame();
		else if (yMove > 0 && !yBlock)
			return animDw.getCurrentFrame();
		else
			return stopPos;
	}

	public int getId() { return id; }
	public int getPoints() { return points; }
	public int getHealth() { return health; }
	public boolean isAlive() { return health>0; }
	
	public void setHealth(int health) { this.health = health; }	
}
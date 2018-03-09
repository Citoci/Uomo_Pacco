package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.server.GameServer;
import gfx.Animation;
import gfx.Assets;

public class Player extends Entity {

	public static int DEFAULT_HEALTH = 3;

	private int id, health, points, invulnerableTime;

	BufferedImage stopPos;

	public Player(GameServer game, int id, int xPos, int yPos) {
		super(game, xPos, yPos);

		this.id = id;
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

	public void hurt() {
		if(invulnerableTime > 0)
			return;
		health--;
		resetPos();
	}
	
	@Override
	protected void resetPos() {
		super.resetPos();
		invulnerableTime = 200;
	}

	@Override
	public void tick() {
		//Animation
		animUp.tick();
		animDw.tick();
		animLf.tick();
		animRg.tick();
		
		if(invulnerableTime > 0)
			invulnerableTime --;
		
		move();
	}

	@Override
	public void render(Graphics g) {
		// drawing health
		for (int i = 0; i < health; i++) 
			g.drawImage(Assets.heart, i*DEFAULT_SIZE, 0, DEFAULT_SIZE, DEFAULT_SIZE, null);

		if(invulnerableTime/15%2 == 0) 
			g.drawImage(getCurrentAnimationFrame(), xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);	
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0 && !xBlock)
			return animLf.getCurrentFrame();
		else if (xMove > 0 && !xBlock)
			return animRg.getCurrentFrame();
		else if (yMove < 0)
			return animUp.getCurrentFrame();
		else if (yMove > 0)
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

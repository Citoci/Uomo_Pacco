package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.server.GameServer;
import gfx.Animation;
import gfx.Assets;

public class Ghost extends Entity {
	
	private boolean eatMe = true;
	private Animation eat;
	public Ghost(GameServer game, int xPos, int yPos, int c) {
		super(game, xPos, yPos);

		// Animations
		animUp = new Animation(Assets.ghosts[c][0]);
		animDw = new Animation(Assets.ghosts[c][1]);
		animLf = new Animation(Assets.ghosts[c][2]);
		animRg = new Animation(Assets.ghosts[c][3]);
		
		eat = new Animation(Assets.eatGhost); //modifica nome
	}

	long last, now;
	double delta = 0;

	@Override
	public void tick() {

		// Animation tick
		animUp.tick();
		animDw.tick();
		animLf.tick();
		animRg.tick();
		eat.tick();

		now = System.nanoTime();
		delta += (now - last);
		last = now;
		if (delta >= 2e9 || (xBlock && yBlock)) {
			if ((new Random()).nextInt(2) == 0)
				xMove = speed;
			else
				xMove = -speed;
			if ((new Random()).nextInt(2) == 0)
				yMove = speed;
			else
				yMove = -speed;

			delta = 0;
		}

		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if(!eatMe) {
			if (xMove < 0 && !xBlock)
				return animLf.getCurrentFrame();
			else if (xMove > 0 && !xBlock)
				return animRg.getCurrentFrame();
			else if (yMove < 0 && !yBlock)
				return animUp.getCurrentFrame();
			else
				return animDw.getCurrentFrame();
		}
		else 
			return eat.getCurrentFrame();
	}
	
	public void setEatMe(boolean boole) {
		this.eatMe = boole;
	}
	
	public boolean getEatMe() {
		return eatMe;
	}

}

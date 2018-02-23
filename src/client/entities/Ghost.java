package client.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import client.gfx.Assets;
import client.gfx.Animation;

public class Ghost extends Entity {

	public Ghost(int xPos, int yPos, int c) {
		super(xPos, yPos);

		// Animations
		animUp = new Animation(Assets.ghosts[c][0]);
		animDw = new Animation(Assets.ghosts[c][1]);
		animLf = new Animation(Assets.ghosts[c][2]);
		animRg = new Animation(Assets.ghosts[c][3]);
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

		now = System.nanoTime();
		delta += (now - last);
		last = now;
		if (delta >= 2e9 || (!xBlock && !yBlock)) {
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
		if (xMove < 0 && xBlock)
			return animLf.getCurrentFrame();
		else if (xMove > 0 && xBlock)
			return animRg.getCurrentFrame();
		else if (yMove < 0 && yBlock)
			return animUp.getCurrentFrame();
		else
			return animDw.getCurrentFrame();
	}

}

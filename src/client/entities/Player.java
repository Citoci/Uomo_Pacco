package client.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import client.Game;
import client.gfx.Animation;
import client.gfx.Assets;

public class Player extends Entity {
	
	public static int DEFAULT_HEALTH = 3;
	
	private String name;
	private int points, health;
	
	BufferedImage stopPos;

	public Player(int xPos, int yPos) {
		super(xPos, yPos);
		
		health = DEFAULT_HEALTH;

		// Animations
		animLf = new Animation(Assets.pacman[0]);
		animRg = new Animation(Assets.pacman[1]);
		animUp = new Animation(Assets.pacman[2]);
		animDw = new Animation(Assets.pacman[3]);
		
		stopPos = Assets.pacman[0][2];
	}

	private void getInput() {
		xMove = yMove = 0;

		if (Game.game.getKeyManager().up)
			yMove = -speed;
		if (Game.game.getKeyManager().down)
			yMove = speed;
		if (Game.game.getKeyManager().left)
			xMove = -speed;
		if (Game.game.getKeyManager().right)
			xMove = speed;
	}

	public void hurt() {
		health--;
		xPos = xSpawn;
		yPos = ySpawn;
		if (health == 0) {
			JOptionPane.showMessageDialog(null, "GAME OVER");
			Game.game.stop();
		}
	}

	@Override
	public void tick() {
		//Animation
		animUp.tick();
		animDw.tick();
		animLf.tick();
		animRg.tick();

		
		getInput();
		move();
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(getCurrentAnimationFrame(), xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);
		for (int i = 0, k = 0; i < health; i++, k += DEFAULT_SIZE + 5) {
			g.drawImage(Assets.heart, k, 0, DEFAULT_SIZE + 5, DEFAULT_SIZE, null);
		}

		
		if((Game.game.getKeyManager().right) || (Game.game.getKeyManager().left)|| (Game.game.getKeyManager().down) || (Game.game.getKeyManager().up)) {
			g.drawImage(getCurrentAnimationFrame(), xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);
		}
		else {
			g.drawImage(stopPos,  xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);
		}
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0 )
			return animLf.getCurrentFrame();
		else if (xMove > 0 )
			return animRg.getCurrentFrame();
		else if (yMove < 0)
			return animUp.getCurrentFrame();
		else
			return animDw.getCurrentFrame();
	}
	

	public String getName() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHealth() {
		return health;
	}
	

	
	
	

}

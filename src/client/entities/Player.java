package client.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import client.Game;
import client.GameClient;
import client.GameServer;
import client.gfx.Animation;
import client.gfx.Assets;

public class Player extends Entity {
	
	public static int DEFAULT_HEALTH = 3;
	
	
	private String name;
	private int points, health;
	
	BufferedImage stopPos;

	public Player(Game game, int xPos, int yPos) {
		super(game, xPos, yPos);
		
		name = "New_Player";
		health = DEFAULT_HEALTH;
		points = 0;

		// Animations
		animLf = new Animation(Assets.pacman[0]);
		animRg = new Animation(Assets.pacman[1]);
		animUp = new Animation(Assets.pacman[2]);
		animDw = new Animation(Assets.pacman[3]);
		
		stopPos = Assets.pacman[0][2];
	}
	
	public Player(Game game, String name, int xPos, int yPos, int health, int points) {
		this(game, xPos, yPos);
		this.name = name;
		this.health = health;
		this.points = points;
	}

	private void getInput() {
		xMove = yMove = 0;
		
		GameClient game = (GameClient) super.game;

		if (game.getKeyManager().up)
			yMove = -speed;
		if (game.getKeyManager().down)
			yMove = speed;
		if (game.getKeyManager().left)
			xMove = -speed;
		if (game.getKeyManager().right)
			xMove = speed;
	}

	public void hurt() {
		health--;
		xPos = xSpawn;
		yPos = ySpawn;
//		if (health == 0) {
//			JOptionPane.showMessageDialog(null, "GAME OVER");
//			game.stop();
//		}
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

		GameClient game = (GameClient) super.game;
		
		if((game.getKeyManager().right) || (game.getKeyManager().left)|| (game.getKeyManager().down) || (game.getKeyManager().up)) {
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

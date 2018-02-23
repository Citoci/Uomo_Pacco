package client.entities;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import client.Game;
import client.gfx.Assets;

public class Player extends Entity {
	
	public Player(int xPos, int yPos) {
		super(xPos, yPos);
		
		//Animations
		
		
	}
	
	
	private void getInput() {
		xMove = yMove = 0;

		if(Game.game.getKeyManager().up)
			yMove = -speed;
		if(Game.game.getKeyManager().down)
			yMove = speed;
		if(Game.game.getKeyManager().left)
			xMove = -speed;
		if(Game.game.getKeyManager().right)
			xMove = speed;		
	}
	
	public void hurt() {
		health--;
		xPos = xSpawn;
		yPos = ySpawn;
		if(health==0) {
			JOptionPane.showMessageDialog(null, "GAME OVER");
			Game.game.stop();
		}
	}

	@Override
	public void tick() {
		getInput();
		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.pacman, xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);
		for(int i=0, k=0; i<health; i++, k+=DEFAULT_SIZE+5) {
			g.drawImage(Assets.heart,k,0,DEFAULT_SIZE+5,DEFAULT_SIZE,null);
		}
		
	}

}

package client.entities;

import java.awt.Graphics;
import java.util.Random;

import client.gfx.Assets;

public class Ghost extends Entity{

	public Ghost(int xPos, int yPos) {
		super(xPos, yPos);
	}
	
	long last, now;
	double delta=0;
	
	@Override
	public void tick() {
		now = System.nanoTime();
		delta += (now-last);
		last = now;
		if(delta>=2e9) {
			if((new Random()).nextInt(2)==0) 
				xMove=speed;
			else 
				xMove=-speed;			
			if((new Random()).nextInt(2)==0)
				yMove=speed;
			else 
				yMove=-speed;

			delta=0;
		}
		
		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.ghost, xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);
	}

}

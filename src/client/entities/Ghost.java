package client.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import client.gfx.Assets;
import client.gfx.Animation;

public class Ghost extends Entity{

	//Animations
	private Animation animDw, animUp, animLf, animRg;


	public Ghost(int xPos, int yPos, int c) {
		super(xPos, yPos);

		//Animations
		switch(c) {
		case 1:{
			setRed();
			break;
		}
		case 2:{
			setBlu();
			break;
		}
		case 3:{
			setYellow();
			break;
		}
		case 4:{
			setPink();
			break;
		}
		
		}

	}

	long last, now;
	double delta=0;
	
	@Override
	public void tick() {
		
		//Animation tick
		animUp.tick();
		animDw.tick();
		animLf.tick();
		animRg.tick();
		
		now = System.nanoTime();
		delta += (now-last);
		last = now;
		if(delta>=2e9 || (!yBlock && !xBlock)) {
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
		g.drawImage(getCurrentAnimationFrame(), xPos, yPos, DEFAULT_SIZE, DEFAULT_SIZE, null);	
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove<0 && xBlock) 
			return animLf.getCurrentFrame();
		else if(xMove>0 && xBlock) 
			return animRg.getCurrentFrame();
		else if(yMove<0 && yBlock) 
			return animUp.getCurrentFrame();
		else
			return animDw.getCurrentFrame();
	}

	//Animations set colors
	public void setRed() {
		animUp = new Animation(Assets.redGhost_UP);
		animDw = new Animation(Assets.redGhost_DW);
		animLf = new Animation(Assets.redGhost_LF);
		animRg = new Animation(Assets.redGhost_RG);
	}
	
	public void setBlu() {
		animUp = new Animation(Assets.blueGhost_UP);
		animDw = new Animation(Assets.blueGhost_DW);
		animLf = new Animation(Assets.blueGhost_LF);
		animRg = new Animation(Assets.blueGhost_RG);
	}
	
	public void setYellow() {
		animUp = new Animation(Assets.yellowGhost_UP);
		animDw = new Animation(Assets.yellowGhost_DW);
		animLf = new Animation(Assets.yellowGhost_LF);
		animRg = new Animation(Assets.yellowGhost_RG);
	}
	
	public void setPink() {
		animUp = new Animation(Assets.pinkGhost_UP);
		animDw = new Animation(Assets.pinkGhost_DW);
		animLf = new Animation(Assets.pinkGhost_LF);
		animRg = new Animation(Assets.pinkGhost_RG);
	}

}

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
		//
		
		now = System.nanoTime();
		delta += (now-last);
		last = now;
		if(delta>=2e9 || (!XBlock && !YBlock)) {
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
		if(xMove<0 && YBlock) {
			return animLf.getCurrentFrame();
		}
		else if(xMove>0 && YBlock) {
			return animRg.getCurrentFrame();
		}
		else if(yMove<0 && XBlock) {
			return animUp.getCurrentFrame();
		}
		else {
			return animDw.getCurrentFrame();
		}
	}

	//Animations set colors
	public void setRed() {
		animUp = new Animation(200, Assets.ghost_REDup);
		animDw = new Animation(200, Assets.ghost_REDdw);
		animLf = new Animation(200, Assets.ghost_REDlf);
		animRg = new Animation(200, Assets.ghost_REDrg);
	}
	
	public void setBlu() {
		animUp = new Animation(200, Assets.ghost_BLUup);
		animDw = new Animation(200, Assets.ghost_BLUdw);
		animLf = new Animation(200, Assets.ghost_BLUlf);
		animRg = new Animation(200, Assets.ghost_BLUrg);
	}
	
	public void setYellow() {
		animUp = new Animation(200, Assets.ghost_YELup);
		animDw = new Animation(200, Assets.ghost_YELdw);
		animLf = new Animation(200, Assets.ghost_YELlf);
		animRg = new Animation(200, Assets.ghost_YELrg);
	}
	
	public void setPink() {
		animUp = new Animation(200, Assets.ghost_PINup);
		animDw = new Animation(200, Assets.ghost_PINdw);
		animLf = new Animation(200, Assets.ghost_PINlf);
		animRg = new Animation(200, Assets.ghost_PINrg);
	}

}

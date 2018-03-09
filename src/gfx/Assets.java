package gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage pacman[][], wall, ball, heart, ghosts[][][], ghostSheet, pacmanSheet;

	public static void init() {
		//pacman = ImageUtils.loadImage("pacman.png");
		wall = ImageUtils.loadImage("wall.png");
		ball = ImageUtils.loadImage("coin.png");
		heart = ImageUtils.loadImage("heart.png");

		ghosts = new BufferedImage[4][4][2];
		ghostSheet = ImageUtils.loadImage("ghosts.png");

		for (int g = 0; g < 4; g++)
			for (int m = 0; m < 4; m++)
				for (int f = 0; f < 2; f++)
					ghosts[g][m][f] = ImageUtils.crop(ghostSheet,  (20 * f) + (40 * m), g*20, 16, 16);
		
		pacmanSheet = ImageUtils.loadImage("pacman.png");
		pacman = new BufferedImage[4][3];
		
		for(int m=0; m<4; m++) 
			for(int f=0; f<3; f++)
				pacman[m][f] = ImageUtils.crop(pacmanSheet, (20*f)+(60*m), 0, 13, 13);
				
					
		
	

	}
	
	

}

package gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage pacman[][][], wall, ball, heart, ghosts[][][], ghostSheet, pacmanSheet,pacDiedSheet, pacDied[];

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
		
		
		pacman = new BufferedImage[2][4][3];
		pacmanSheet = ImageUtils.loadImage("pacman.png");
		
		for(int p=0; p<2; p++) {
			for(int m=0; m<4; m++) 
				for(int f=0; f<3; f++)
					pacman[p][m][f] = ImageUtils.crop(pacmanSheet, (20*f)+(60*m), p*16, 14+p, 14+p);
			//il +p è temporaneo, causa dimensioni diverse pacman
					
		}
		
		pacDied = new BufferedImage[12];
		pacDiedSheet = ImageUtils.loadImage("pacDied.png");
				
		for(int f=0; f<12; f++) {
			pacDied[f]=ImageUtils.crop(pacDiedSheet, 20*f, 0, 14, 14);
		}
	

	}
	
	

}

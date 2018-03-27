package gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage pacmans[][][], fruitSheet,fruits[],wall, bigCoin, ball, heart, sheetEatGhost,eatGhost[], ghosts[][][], ghostSheet, pacmanSheet,pacDiedSheet, pacDied[];

	public static void init() {
		//pacman = ImageUtils.loadImage("pacman.png");
		wall = ImageUtils.loadImage("wall.png");
		ball = ImageUtils.loadImage("coin.png");
		bigCoin = ImageUtils.loadImage("bigCoin.png");
		heart = ImageUtils.loadImage("heart.png");
		
		fruitSheet = ImageUtils.loadImage("fruits.png");
		fruits = new BufferedImage[4];
		for(int i=0; i<4; i++) {
			fruits[i]=ImageUtils.crop(fruitSheet, 0, 20*i, 14, 14);
		}
		
		ghosts = new BufferedImage[4][4][2];
		ghostSheet = ImageUtils.loadImage("ghosts.png");

		for (int g = 0; g < 4; g++)
			for (int m = 0; m < 4; m++)
				for (int f = 0; f < 2; f++)
					ghosts[g][m][f] = ImageUtils.crop(ghostSheet,  (20 * f) + (40 * m), g*20, 16, 16);
		
		eatGhost = new BufferedImage[4];
		sheetEatGhost = ImageUtils.loadImage("eatGhost.png");
		for (int f = 0; f < 4; f++)
			eatGhost[f] = ImageUtils.crop(sheetEatGhost, 20 * f, 0 , 16, 16);
		
		pacmans = new BufferedImage[4][4][3];
		pacmanSheet = ImageUtils.loadImage("pacman.png");
		
		
		for(int p=0; p<4; p++) {
			for(int m=0; m<4; m++) {
				for(int f=0; f<3; f++) {
					
						pacmans[p][m][f] = ImageUtils.crop(pacmanSheet, (20*f)+(60*m), p*16, 14+p, 14+p);
				}
			}
			
			//il +p è temporaneo, causa dimensioni diverse pacman
					
		}
		
		pacDied = new BufferedImage[12];
		pacDiedSheet = ImageUtils.loadImage("pacDied.png");
				
		for(int f=0; f<12; f++) {
			pacDied[f]=ImageUtils.crop(pacDiedSheet, 20*f, 0, 14, 14);
		}
	

	}
	
	

}

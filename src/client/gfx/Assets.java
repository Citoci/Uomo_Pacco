package client.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage pacman, wall, ball, heart, ghosts[][][], ghostSheets[];

	public static void init() {
		pacman = ImageUtils.loadImage("pacman.png");
		wall = ImageUtils.loadImage("wall.png");
		ball = ImageUtils.loadImage("coin.png");
		heart = ImageUtils.loadImage("heart.png");

		ghosts = new BufferedImage[4][4][2];
		ghostSheets = new BufferedImage[4];
		ghostSheets[0] = ImageUtils.loadImage("Ghost_RED.png");
		ghostSheets[1] = ImageUtils.loadImage("Ghost_BLU.png");
		ghostSheets[2] = ImageUtils.loadImage("Ghost_YELLOW.png");
		ghostSheets[3] = ImageUtils.loadImage("Ghost_PINK.png");

		for (int g = 0; g < 4; g++)
			for (int m = 0; m < 4; m++)
				for (int f = 0; f < 2; f++)
					ghosts[g][m][f] = ImageUtils.crop(ghostSheets[g], 1 + (20 * f) + (40 * m), 1, 14, 14);

	}

}

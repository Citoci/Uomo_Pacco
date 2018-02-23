package client.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage pacman, wall, ball, heart, ghosts[][][], ghostSheet;

	public static void init() {
		pacman = ImageUtils.loadImage("pacman.png");
		wall = ImageUtils.loadImage("wall.png");
		ball = ImageUtils.loadImage("coin.png");
		heart = ImageUtils.loadImage("heart.png");

		ghosts = new BufferedImage[4][4][2];
		ghostSheet = ImageUtils.loadImage("ghosts.png");

		for (int g = 0; g < 4; g++)
			for (int m = 0; m < 4; m++)
				for (int f = 0; f < 2; f++)
					ghosts[g][m][f] = ImageUtils.crop(ghostSheet,  (20 * f) + (40 * m), g*20, 16, 16);

	}

}

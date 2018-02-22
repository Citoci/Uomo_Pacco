package client.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage pacman, ghost, blank, wall;
	
	public static void init() {		
		pacman = ImageUtils.loadImage("pacman.png");
		ghost = ImageUtils.loadImage("ghost.png");
		blank = null;//ImageUtils.loadImage("blank_64px.png");
		wall = ImageUtils.loadImage("wall.png");		
	}

}

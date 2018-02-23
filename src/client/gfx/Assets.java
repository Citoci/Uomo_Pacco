package client.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage pacman, wall, ball, heart;

	public static BufferedImage redGhost_sheet, blueGhost_sheet, yellowGhost_sheet, pinkGhost_sheet;
	public static BufferedImage[] redGhost_UP, redGhost_DW, redGhost_LF, redGhost_RG,
								  blueGhost_UP, blueGhost_DW, blueGhost_LF, blueGhost_RG,
								  yellowGhost_UP, yellowGhost_DW, yellowGhost_LF, yellowGhost_RG,
								  pinkGhost_UP, pinkGhost_DW, pinkGhost_LF, pinkGhost_RG;
		
	
	public static void init() {		
		pacman = ImageUtils.loadImage("pacman.png");
 		wall = ImageUtils.loadImage("wall.png");	
		ball = ImageUtils.loadImage("coin.png");
		heart =  ImageUtils.loadImage("heart.png");
		
		//Carico il fantasmino rosso
		redGhost_sheet = ImageUtils.loadImage("Ghost_RED.png");
		
		redGhost_UP = new BufferedImage[2];
		redGhost_UP[0] = ImageUtils.crop(redGhost_sheet, 0, 0, 14, 14);
		redGhost_UP[1] = ImageUtils.crop(redGhost_sheet, 21, 1, 14, 14);
				
		redGhost_DW = new BufferedImage[2];
		redGhost_DW[0] = ImageUtils.crop(redGhost_sheet, 41, 1, 14, 14);
		redGhost_DW[1] = ImageUtils.crop(redGhost_sheet, 61, 1, 14, 14);
		
		redGhost_LF = new BufferedImage[2];
		redGhost_LF[0] = ImageUtils.crop(redGhost_sheet, 81, 1, 14, 14);
		redGhost_LF[1] = ImageUtils.crop(redGhost_sheet, 101, 1, 14, 14);

		redGhost_RG = new BufferedImage[2];
		redGhost_RG[0] = ImageUtils.crop(redGhost_sheet, 121, 1, 14, 14);
		redGhost_RG[1] = ImageUtils.crop(redGhost_sheet, 141, 1, 14, 14);

		//Carico il fantasmino blu
		blueGhost_sheet = ImageUtils.loadImage("Ghost_BLU.png");

		blueGhost_UP = new BufferedImage[2];
		blueGhost_UP[0] = ImageUtils.crop(blueGhost_sheet, 1, 1, 14, 14);
		blueGhost_UP[1] = ImageUtils.crop(blueGhost_sheet, 21, 1, 14, 14);

		blueGhost_DW = new BufferedImage[2];
		blueGhost_DW[0] = ImageUtils.crop(blueGhost_sheet, 41, 1, 14, 14);
		blueGhost_DW[1] = ImageUtils.crop(blueGhost_sheet, 61, 1, 14, 14);

		blueGhost_LF = new BufferedImage[2];
		blueGhost_LF[0] = ImageUtils.crop(blueGhost_sheet, 81, 1, 14, 14);
		blueGhost_LF[1] = ImageUtils.crop(blueGhost_sheet, 101, 1, 14, 14);

		blueGhost_RG = new BufferedImage[2];
		blueGhost_RG[0] = ImageUtils.crop(blueGhost_sheet, 121, 1, 14, 14);
		blueGhost_RG[1] = ImageUtils.crop(blueGhost_sheet, 141, 1, 14, 14);

		//Carico il fantasmino rosa
		pinkGhost_sheet = ImageUtils.loadImage("Ghost_PINK.png");

		pinkGhost_UP = new BufferedImage[2];
		pinkGhost_UP[0] = ImageUtils.crop(pinkGhost_sheet, 1, 1, 14, 14);
		pinkGhost_UP[1] = ImageUtils.crop(pinkGhost_sheet, 21, 1, 14, 14);

		pinkGhost_DW = new BufferedImage[2];
		pinkGhost_DW[0] = ImageUtils.crop(pinkGhost_sheet, 41, 1, 14, 14);
		pinkGhost_DW[1] = ImageUtils.crop(pinkGhost_sheet, 61, 1, 14, 14);

		pinkGhost_LF = new BufferedImage[2];
		pinkGhost_LF[0] = ImageUtils.crop(pinkGhost_sheet, 81, 1, 14, 14);
		pinkGhost_LF[1] = ImageUtils.crop(pinkGhost_sheet, 101, 1, 14, 14);

		pinkGhost_RG = new BufferedImage[2];
		pinkGhost_RG[0] = ImageUtils.crop(pinkGhost_sheet, 121, 1, 14, 14);
		pinkGhost_RG[1] = ImageUtils.crop(pinkGhost_sheet, 141, 1, 14, 14);

		//Carico il fantasmino giallo
		yellowGhost_sheet = ImageUtils.loadImage("Ghost_YELLOW.png");

		yellowGhost_UP = new BufferedImage[2];
		yellowGhost_UP[0] = ImageUtils.crop(yellowGhost_sheet, 1, 1, 14, 14);
		yellowGhost_UP[1] = ImageUtils.crop(yellowGhost_sheet, 21, 1, 14, 14);

		yellowGhost_DW = new BufferedImage[2];
		yellowGhost_DW[0] = ImageUtils.crop(yellowGhost_sheet, 41, 1, 14, 14);
		yellowGhost_DW[1] = ImageUtils.crop(yellowGhost_sheet, 61, 1, 14, 14);

		yellowGhost_LF = new BufferedImage[2];
		yellowGhost_LF[0] = ImageUtils.crop(yellowGhost_sheet, 81, 1, 14, 14);
		yellowGhost_LF[1] = ImageUtils.crop(yellowGhost_sheet, 101, 1, 14, 14);

		yellowGhost_RG = new BufferedImage[2];
		yellowGhost_RG[0] = ImageUtils.crop(yellowGhost_sheet, 121, 1, 14, 14);
		yellowGhost_RG[1] = ImageUtils.crop(yellowGhost_sheet, 141, 1, 14, 14);

	}




}

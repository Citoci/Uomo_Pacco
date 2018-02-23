package client.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage pacman, ghost, blank, wall;
	
	//BufferdImage per l'animazione del fantasma rosso
	public static BufferedImage[] ghost_REDup,ghost_REDdw,ghost_REDlf,ghost_REDrg,
								  ghost_BLUup,ghost_BLUdw,ghost_BLUlf,ghost_BLUrg,
								  ghost_YELup,ghost_YELdw,ghost_YELlf,ghost_YELrg,
								  ghost_PINup,ghost_PINdw,ghost_PINlf,ghost_PINrg;
	
	public static BufferedImage sheet_redG, sheet_yelG, sheet_pinkG, sheet_bluG;
	
	public static BufferedImage heart;
	
	
	public static void init() {		
		pacman = ImageUtils.loadImage("pacman.png");
		//ghost = ImageUtils.loadImage("ghost.png");
		blank = null;//ImageUtils.loadImage("blank_64px.png");
		wall = ImageUtils.loadImage("wall.png");		
		
		//Carico il fantasmino rosso
		sheet_redG = ImageUtils.loadImage("Ghost_RED.png");
		
		ghost_REDup = new BufferedImage[2];
		ghost_REDup[0] = ImageUtils.crop(sheet_redG, 1, 1, 14, 14);
		ghost_REDup[1] = ImageUtils.crop(sheet_redG, 21, 1, 14, 14);
				
		ghost_REDdw = new BufferedImage[2];
		ghost_REDdw[0] = ImageUtils.crop(sheet_redG, 41, 1, 14, 14);
		ghost_REDdw[1] = ImageUtils.crop(sheet_redG, 61, 1, 14, 14);
		
		ghost_REDlf = new BufferedImage[2];
		ghost_REDlf[0] = ImageUtils.crop(sheet_redG, 81, 1, 14, 14);
		ghost_REDlf[1] = ImageUtils.crop(sheet_redG, 101, 1, 14, 14);

		ghost_REDrg = new BufferedImage[2];
		ghost_REDrg[0] = ImageUtils.crop(sheet_redG, 121, 1, 14, 14);
		ghost_REDrg[1] = ImageUtils.crop(sheet_redG, 141, 1, 14, 14);

		//Carico il fantasmino blu
		sheet_bluG = ImageUtils.loadImage("Ghost_BLU.png");

		ghost_BLUup = new BufferedImage[2];
		ghost_BLUup[0] = ImageUtils.crop(sheet_bluG, 1, 1, 14, 14);
		ghost_BLUup[1] = ImageUtils.crop(sheet_bluG, 21, 1, 14, 14);

		ghost_BLUdw = new BufferedImage[2];
		ghost_BLUdw[0] = ImageUtils.crop(sheet_bluG, 41, 1, 14, 14);
		ghost_BLUdw[1] = ImageUtils.crop(sheet_bluG, 61, 1, 14, 14);

		ghost_BLUlf = new BufferedImage[2];
		ghost_BLUlf[0] = ImageUtils.crop(sheet_bluG, 81, 1, 14, 14);
		ghost_BLUlf[1] = ImageUtils.crop(sheet_bluG, 101, 1, 14, 14);

		ghost_BLUrg = new BufferedImage[2];
		ghost_BLUrg[0] = ImageUtils.crop(sheet_bluG, 121, 1, 14, 14);
		ghost_BLUrg[1] = ImageUtils.crop(sheet_bluG, 141, 1, 14, 14);

		//Carico il fantasmino rosa
		sheet_pinkG = ImageUtils.loadImage("Ghost_PINK.png");

		ghost_PINup = new BufferedImage[2];
		ghost_PINup[0] = ImageUtils.crop(sheet_pinkG, 1, 1, 14, 14);
		ghost_PINup[1] = ImageUtils.crop(sheet_pinkG, 21, 1, 14, 14);

		ghost_PINdw = new BufferedImage[2];
		ghost_PINdw[0] = ImageUtils.crop(sheet_pinkG, 41, 1, 14, 14);
		ghost_PINdw[1] = ImageUtils.crop(sheet_pinkG, 61, 1, 14, 14);

		ghost_PINlf = new BufferedImage[2];
		ghost_PINlf[0] = ImageUtils.crop(sheet_pinkG, 81, 1, 14, 14);
		ghost_PINlf[1] = ImageUtils.crop(sheet_pinkG, 101, 1, 14, 14);

		ghost_PINrg = new BufferedImage[2];
		ghost_PINrg[0] = ImageUtils.crop(sheet_pinkG, 121, 1, 14, 14);
		ghost_PINrg[1] = ImageUtils.crop(sheet_pinkG, 141, 1, 14, 14);

		//Carico il fantasmino giallo
		sheet_yelG = ImageUtils.loadImage("Ghost_YELLOW.png");

		ghost_YELup = new BufferedImage[2];
		ghost_YELup[0] = ImageUtils.crop(sheet_yelG, 1, 1, 14, 14);
		ghost_YELup[1] = ImageUtils.crop(sheet_yelG, 21, 1, 14, 14);

		ghost_YELdw = new BufferedImage[2];
		ghost_YELdw[0] = ImageUtils.crop(sheet_yelG, 41, 1, 14, 14);
		ghost_YELdw[1] = ImageUtils.crop(sheet_yelG, 61, 1, 14, 14);

		ghost_YELlf = new BufferedImage[2];
		ghost_YELlf[0] = ImageUtils.crop(sheet_yelG, 81, 1, 14, 14);
		ghost_YELlf[1] = ImageUtils.crop(sheet_yelG, 101, 1, 14, 14);

		ghost_YELrg = new BufferedImage[2];
		ghost_YELrg[0] = ImageUtils.crop(sheet_yelG, 121, 1, 14, 14);
		ghost_YELrg[1] = ImageUtils.crop(sheet_yelG, 141, 1, 14, 14);

		
		//Carico la vita
		heart =  ImageUtils.loadImage("heart.png");

	}




}

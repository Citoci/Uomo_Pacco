package gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	/**
	 * Carica un immagine da file
	 * @param path il percorso del file dell'immagine ("res/images/" è già incluso)
	 * @return l'immagine caicata
	 */
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File("res/images/" + path));
		} catch (IOException e) { e.printStackTrace(); }
		try {
			return ImageIO.read(new File("res/images/test_8px.png")); // Se non la trova prova a caricare l'immagine di test
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}

	/**
	 * Taglia un'immagine
	 * @param image immagine da tagliare
	 * @return immagine tagliata
	 */
	public static BufferedImage crop(BufferedImage image, int x, int y, int width, int height) {
		return image.getSubimage(x, y, width, height);
	}

}

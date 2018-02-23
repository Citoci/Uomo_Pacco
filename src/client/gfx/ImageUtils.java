package client.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File("res/images/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return ImageIO.read(new File("res/images/test_8px.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage crop(BufferedImage image, int x, int y, int width, int height) {
		return image.getSubimage(x, y, width, height);
	}
	
	public static BufferedImage rotate(BufferedImage imge) {	
		return null;
	}

}

package gfx;

import java.awt.image.BufferedImage;

public class Animation {

	public static final int DEFAULT_SPEED = 200;

	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;

	public Animation(BufferedImage[] frames) {
		this.speed = DEFAULT_SPEED;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}

	public Animation(int speed, BufferedImage[] frames) {
		this(frames);
		this.speed = speed;
	}

	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++;
			timer = 0;
			if (index >= frames.length)
				index = 0;
		}
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

}

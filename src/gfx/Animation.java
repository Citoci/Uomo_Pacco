package gfx;

import java.awt.image.BufferedImage;

public class Animation {

	public static final int DEFAULT_DELAY = 200; 

	private int delay, animationIndex;
	private long lastTime, timer;
	private BufferedImage[] frames;

	public Animation(BufferedImage[] frames) {
		this.delay = DEFAULT_DELAY;
		this.frames = frames;
		animationIndex = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}

	public Animation(int speed, BufferedImage[] frames) {
		this(frames);
		this.delay = speed;
	}

	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > delay) {
			animationIndex++;
			timer = 0;
			if (animationIndex >= frames.length)
				animationIndex = 0;
		}
	}

	public BufferedImage getCurrentFrame() {
		return frames[animationIndex];
	}

}

package gfx;

import java.awt.image.BufferedImage;

public class Animation {

	public static final int DEFAULT_DELAY = 200; 

	protected int delay, animationIndex;
	protected long lastTime, timer;
	protected BufferedImage[] frames;

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
			animationIndex %= frames.length;
		}
	}

	public BufferedImage getCurrentFrame() {
		if(animationIndex >= frames.length)
			return null;
		return frames[animationIndex];
	}

}

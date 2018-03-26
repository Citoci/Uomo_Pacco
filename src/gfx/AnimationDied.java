package gfx;

import java.awt.image.BufferedImage;

public class AnimationDied extends Animation {

	public AnimationDied(BufferedImage[] frames) {
		super(frames);
	}
	
	public AnimationDied(int speed, BufferedImage[] frames) {
		super(speed, frames);
	}
	
	@Override
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > delay) {
			animationIndex++;
			timer = 0;
		}
	}
	
}

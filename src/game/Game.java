package game;

import gfx.Display;

public abstract class Game implements Runnable{

	// Config
	private boolean running;
	public final int FPS = 60;

	// Thread
	private Thread gameThread;

	// GFX
	protected final String title;
	protected Display display;

	public Game(String title) {
		this.title = title;
	}
	
	protected abstract void init();
	
	@Override
	public void run() {
		// Initialize the game first of all
		init();
		
		// Starting the controlled loop
		double delta = 0;
		long now, lastTime = System.nanoTime();
		while (running) {
			now = System.nanoTime();
			delta += (now-lastTime) * FPS / 10e8;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
	}
	
	public abstract void tick();
	
	public abstract void render();

	public synchronized void start() {
		if (running)
			return;
		running = true;
		gameThread = new Thread(this, "gameThread");
		gameThread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		gameThread.interrupt();
	}

	public boolean isRunning() { return running; }
}

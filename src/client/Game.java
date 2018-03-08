package client;

import client.world.World;

public abstract class Game implements Runnable {
	protected boolean running;
	protected final int fps = 60;
	
	// Thread
	private Thread gameThread;
	
	// World
	protected World world;
	protected final String worldPath = "res/worlds/world2.txt";

	
	public abstract void init();
	
	public synchronized void start() {
		if (running)
			return;
//		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		gameThread.interrupt();
	}
	
	public World getWorld() {
		return world;
	}
	
	public boolean isRunning() {
		return running;
	}

}
